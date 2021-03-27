## Channel方法

| 方法名        | 描述                                                         |
| ------------- | ------------------------------------------------------------ |
| eventLoop     | 分配给Channel的EventLoop                                     |
| pipeline      | 返回分配给Channel的ChannelPipeline                           |
| isActive       | 如果Channel是活动的，则返回true，活动的一样可能依赖于底层的 传输。例如，一个Socket传输一旦连接到远程节点便是活动的，而一个Datagram传输一旦被打开便是活动的 |
| localAddress  | 返本地的SocketAddress                                        |
| remoteAddress | 返回远程的SocketAddress                                      |
| write         | 将数据写到远程节点，这个数据将被传递给ChannelPipeline，并且排队直到它被冲刷 |
| flush         | 将之前写的数据冲刷到底层传输，如一个Socket                   |
| writeAndFlush | write和flush的化身                                           |

## 内置的传输

| 名称     | 包                            | 描述                                                         |
| -------- | ----------------------------- | ------------------------------------------------------------ |
| NIO      | io.netty.channel.socket.nio   | 使用java.nio.channels包作为基础——基于选择器模式              |
| Epoll    | io.netty.channel.epoll        | 有JNI驱动的epoll()和非阻塞IO。这个传输支持只有在Linux上可用的多种特性，如：SO_REUSEPORT比NIO传输更快，而且是完全非阻塞的。 |
| OIO      | io.netty.channel.socket.oio   | 使用java.net包作为基础——使用阻塞流                           |
| Local    | io.netty.channel.socket.local | 可以在VM内部通过管道通信的本地传输                           |
| Embedded | io.netty.channel.embedded     | Embedded传输，允许使用ChannelHandler 而又不需要一个真正的基于网络的传输。这在测试你的ChannelHandler实现时非常有用 |

### NIO——非阻塞I/O

NIO提供了一个所有I/O操作的全异步的实现，它利用自NIO自系统被引入JDK1.4时可用的基于选择器的API。

选择器背后的基本概念是充当一个注册表，可能的 状态比变化有：

* 新的Channel已被接收并且就绪
* Channel连接已经完成
* Channel有已经就绪的可供读取的数据
* Channel有用于写数据

### Epoll——用于Linux的本地非阻塞传输

Linux作为高性能网络编程的平台。其中包括epoll——一个高可扩展的I/O事件通知特性。

EpollServerSocketChannel.class

### 用于JVM内部通信的Local传输

