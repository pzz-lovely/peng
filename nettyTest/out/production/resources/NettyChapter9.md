## 9.1 EmbeddedChannel 概述

Netty 提供了它所谓的`Embedd`传输，用于测试ChannelHandler。这个传输是一种特殊的Channels实现——EmbeddedChannel——的功能，这个实现提供了通过ChannePipeline传播事件的简便方法。

特殊的 EmbeddedChannel方法

| 名称                        | 职责                                                         |
| --------------------------- | ------------------------------------------------------------ |
| writeInbound(Object...msg)  | 将入站消息写到EmbeddedChannel中。如果可以通过readInbound()方法从EmbeddedChannel中读取数据，则返回true |
| readInbound()               | 从EmbeddedChannel中读取一个入站的消息。任何返回的东西都穿越了整个ChannelPipeline。如果没有任何可供读取的，则返回null。 |
| writeOutbound(Object...msg) | 将出站消息写到EmbeddedChannel中。如果可以通过readOutbound()方法从EmbeddedChannel中读取数据，则返回true |
| readOutbound()              | 从EmbeddedChannel中读取一个出站的消息。任何返回的东西都穿越了整个ChannelPipeline。如果没有任何可供读取的，则返回null。 |
| finish(）                   | 将EmbeddedChannel标记为完成，并且如果有可被读取的入站数据或者出站数据，则返回true。这个方法还将调用EmbeddedChannel上的close()方法。 |

