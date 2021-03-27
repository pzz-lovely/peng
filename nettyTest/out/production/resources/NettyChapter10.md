## 10.1 什么是编解码器

每个网络应用都必须定义如何解析在两个节点之间来回传输的原始字节，以及如何将其和目标应用的程序的数据格式做相互转换。这种转换逻辑 由 `编解码器 `处理。

编解码器 有编码器和解码器组成，它们每种都可以将字节流从一种数据格式转换成为另一种格式

如果将消息看作是对于特定的应用程序具有具体含义的结构化的 字节序列——它的数据。那么`编码器`是将消息转换为适合于传输的格式（最有可能的就是字节流）。而对应的`解码器`则是将网络字节流转换回应用程序的消息格式。因此，编码器操作出站数据，而解码器处理入站数据。

## 10.2 解码器

* 将字节解码为消息——ByteToMessageDecoder 和 ReplayingDecoder。
* 将一种消息类型解码为另一种——MessageToMessageDecoder/

### 10.2.1 抽象类 ByteToMessageDecoder

将字节解码为消息（或者另一个字节序列)

| 方法                                                         | 描述                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| decode(ChannelHnadlerContext ctx,ByteBuf in,List\<Object> out) | 这是你必须实现的唯一抽象方法。decode()方法被调用时将会传入一个包含了传入数据的ByteBuf，以及一个用来添加解码消息的List。对这个方法的调用将会重复进行，直到确定没有新的元素被添加到该List，或者该ByteBuf中没有更多可读取的字节时为止，然后，如果List不为空，那么它的内容将会被传递给ChannelPipeline中的下一个ChannelInboundHandler |
| decodeLast(ChannelHandlerContext,ByteBuf in ,List\<Object> out) | Netty提供的默认实现只是简单地调用了decode()方法。当Channel的状态变为非活动时，这个方法将会被调用一次。可以重写该方法以提供特殊的处理。 |

~~~java
public class ToIntegerDecoder extends ByteToMessageDecoder{
    public void decode(ChannelHandlerContext ctx,ByteBuf in,List<Object> out){
        if(in.readableBytes() >= 4)
            out.add(in.readInt());	// 将入站 ByteBuf中读取1一个int，并将其添加到解码消息的List中
    }
}
~~~

> 引用计数需要特别的注意。对于编码器和解码器来说，其过程也是相当的简单；一旦消息被编码或者解码，它就会被ReferenceCountUtil.release(message)调用自动释放。如果你需要保留引用以便稍后使用，那么你可以调用ReferenceCountUtil.retain(message)方法。这将会增加引用计数，从而防止该消息被释放。

### 10.2.2 抽象类 ReplayingDecoder

ReplayingDecoder扩展了ByteToMessageDecoder类。

~~~java
public abstract class ReplayingDecoder<S> extends ByteToMessageDecoder{
    // 类型参数S指定了用于状态管理的类型，其中 Void代表不需要状态管理
}

public class ToIntegerDecoder extends ReplayingDecoder<Void> {
    public void decode(ChannelHandlerContext ctx,ByteBuf in,List<Object> out){
        out.add(in.read());
    }
}
~~~

如果没有足够的字节可，这个readInt()方法的实现将会抛出一个io.netty.util.Signal，其将在基类捕获并处理，当有更多的数据可供读取时，该decode()方法将会被再次调用

* 并不是所有的ByteBuf操作都被支持，如果调用了一个不被支持的方法，将会抛出一个UnsupportedOperationException
* ReplayingDecoder稍慢于ByteToMessageDecoder。

如果使用ByteToMessageDecoder不会引入太多的复杂性，那么请使用它；否则，请使用ReplayingDecoder。

> 下面的这些类处理更加复杂的用用例：
>
> * io.netty.handler.codec.LineBasedFrameDecoder——这个类在Netty内部也有使用，它使用了行尾控制符（\n或者\r\n）来解析消息数据
> * io.netty.handler.http.HttpObjectDecoder——一个HTTP数据的解码器。
>
> 在io.netty.handler.codec子包下面，有更多的用于特定的编码器和解码器实现。

### 10.2.3 抽象类 MessageToMessageDecoder

~~~java
public abstract class MessageToMessageDecoder<I> extends ChannelInboundHandlerAdapter{
    // 参数I 指定了decode()方法输入参数msg类型，是必须实现的 方法
}
~~~

| 方法                                                      | 描述                                                         |
| --------------------------------------------------------- | ------------------------------------------------------------ |
| decode(ChannelHandlerContext ctx,I msg,List\<Object> out) | 对于每个需要被解码为另一种格式的入站消息来说，该方法豆浆会被调用。解码消息随后会被传递给ChannelPipeline中的下一个ChannelInboundHandler |

~~~java
public class IntegerToStringDecodeer extends MessageToMessageDecoder<Integer> {
    public void decode(ChannelHanderContext  ctx,List<Object> out){
        out.add(String.valueOf(msg));
    }
}
~~~

> HttpObjectAggregator
>
> ​	有关更加复杂的例子，请研究io.netty.handler.codec.http.HttpObjectAggregator类，它扩展了MessageToMessageDecoder\<HttpObject>。

### 10.2.4 TooLongFrameException类

TooLongFrameExceptiont类，其将由解码器在帧超出指定的大小限制时抛出。

Netty是一个异步框架，需要在字节解码之前 在内存中 缓冲它们。因此，不能让解码器缓冲 大量的数据 以至于耗尽可用的内存。

可以设置一个最大字节数的阈值，如果超出阈值，则会导致抛出一个TooLongFrameException（随后会被ChannelHandler.exceptionCaught()方法捕获）。然后，处理异常完全取决于该解码器的用户。某些协议（如HTTP）可能会返回一个特殊响应，而在其它的情况下，唯一的选择 可能就是关闭对应的连接。

~~~java
public class SafeByteToMessageDecoder extends ByteToMessageDecoder {
    private static final int MAX_FRAME_SIZE = 1024;
    public void decode(ChannelHandlerContext ctx,ByteBuf in,List<Object> out){
        int readable = in.readableBytes();
        if(readable > MAX_FRAME_SIZE){
            in.skipBytes(readable);
            throw new TooLongFrameException("Frame too big!");
        }
        // do someting
        ...
    }
}
~~~

## 10.3 编码器

编码器实现了ChannelOutboundHandler，并将出站数据从一种格式转换为另一种格式。

* 将消息编码为字节
* 将消息编码为另一种消息

### 10.3.1 抽象类 MessageToByteEncoder

| 方法                                                | 描述                                                         |
| --------------------------------------------------- | ------------------------------------------------------------ |
| encode(ChannelHandlerContext ctx,I msg,ByteBuf out) | encode()是你需要实现的唯一抽象方法，它被调用时将会传入要被该类编码为ByteBuf的（类型为I的）出站消息。该ByteBuf随后将会被转发给ChannelPipeline的下一个ChannelHandler。 |

~~~java
public class ShortToByteEncoder extends MessageToByteEncoder<Short>{
    public void encode(ChannelHnadlerContext ctx,Short msg,ByteBuf buf){
        out.writeShort(msg);
    }
}
~~~

Netty提供 一些专门化的MessageToByteEncoder，你可以基于它们实现自己的编码器。

WebSocket08FrameEncoder类提供了一个很好的实例，可以在io.netty.handler.codec.http.websocketx包下。

### 10.3.2 抽象类MessageToMessageEncode

| 名称                                                      | 描述                                                         |
| --------------------------------------------------------- | ------------------------------------------------------------ |
| encode(ChannelHandlerContext ctx,I msg,List\<Object> out) | 这是你需要实现的唯一方法。每个通过write()方法写入的消息都将会被传递给encode()方法，已编码为一个或者多个出站的消息。随后，这些出站消息将会被转发给ChannelPipeline中的下一个ChannelHandler |

~~~java
public class IntegerToStringEncoder extends MessageToMessageEncoder<Integer> {
    public void encode(ChannelHandlerContext ctx,Integer msg,List<Object> out){
        out.add(String.valueOf(msg));
    }
}
~~~

>关于有趣的MessageToMessageEncoder的专业用法，请查看io.netty.handler.codec.protobuf.ProtobufEncode类，它处理了由Google的Protocol Buffers 规范所定义的数据格式。

## 10.4 抽象的编解码器类

### 10.4.1 抽象类ByteToMessageCodec

| 方法                                                         | 描述                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| decode(ChannelHandlerContext ctx,ByteBuf in,List\<Object> out) | 只要有字节可以被消费，这个方法就将会被调用。它将入站ByteBuf转换为指定的消息格式，并将其转发给ChannelPipeline中的下一个ChannelInboundHandler |
| decodeLast(ChannelHandlerContext ctx,ByteBuf in,List\<Object> out) | 这个方法的默认实现委托给了decode()方法。它只会在Channel的状态变为非活动时被调用一次。它可以被重写以实现特殊的处理。 |
| encode(ChannelHnadlerContext ctx,ByteBuf in,List\<Object> out) | 对于每个将被编码并写入出站ByteBuf的（类型为I的）消息来说，这个方法都将会被调用。 |

### 10.4.2 抽象类 MessageToMessageCodec

| 方法                                                         | 描述                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| protected abstract void decode(ChannelHnadlerContext ctx,INBOUND_IN msg,List\<Object> out) | 这个方法被调用时会被出传入INBOUND_IN类型的消息。它把它们解码为OUTBOUND_IN类型的消息，这些消息将被转发给ChannelPipeline中的下一个ChannelIboundHandler |
| abstract void encode(ChannelHandlerContext ctx,OUTBOUND_IN msg,List\<Object> out) | 对于每个OUTBOUND_IN类型的消息，这个方法都将会被 调用，这些消息将会被编码为INBOUND_IN类型的消息，然后被转发给ChannelPipeline中的下一个ChannelOutboundHandler。 |

decode()方法是将INBOUND_IN类型的消息转换为OUTBOUDN_IN类型的消息，而encode()方法则进行它的逆向操作。将INBOUND_IN类型的消息看作是通过网络发送的类型，而将OUTBOUND_IN类型的消息看作是应用程序所处理的类型。

~~~java
public class WebSocketConverHandler extends MessageToMessageCode<WebSocketFrame,WebSocketConvertHandler.MyWebSocketFrame>{
    protected void encode(ChannelHandlerContext ctx,WebScoektConverHandler,MyWebSocketFrame,List<Object> out){
        ByteBuf payload = msg.getData().duplicate().retain();
        switch(msg.getType()){
            case BINARY:
                out.add(new BinaryWebSocketFrame(payload));
                break;
            case TEXT:
                out.add(new TextWebSocketFrame(payload));
                break;
            case CLOSE:
                out.add(new CloseWebSocketFrame(true,0,payload));
                break;
            case PONG:
                out.add(new ContinuationWebSocketFrame(payload));
               	break;
            case PING:
                out.add(new PingWebSocketFrame(payload));
                break;
            default:
                throw new IllegalStateException("Unsupported websocket msg "+ msg);
        }
    }
    protected void decode(ChannelHandlerContext ctx,WebSocketFrame msg,List<Object> out){
     	ByteBuf payload = msg.content().duplicate().retain();
        if(msg instanceof BinaryWebSocketFrame){
            out.add(new MyWebSocketFrame(MyWebSocketFrame.FrameType.BINARY,payload));
        }
    }
    
    public static final class MyWebSocketFrame{
        public enum FrameType{
            BINARY,CLOSE,PING,PONG,TEXT,CONTINUATION
        }
        private final FrameType type;
        private final ByteBuf data;
        pulic MyWebSocketFrame(FrameType type,ByteBuf buf){
            this.type = type;
            this.data = buf;
        }
    }
}
~~~

### 10.4.3 CombinedChannelDuplexHandler

~~~java
public class CombinedChannelDuplexHandler<I extends ChannelInboundHandler,O extends ChannelOutboundHandler>{}
~~~

~~~java

~~~

