# 网络

~~~java
		java.net.Socket
Sokcet()
//构建一个还未被连接的套接字
Socket(String host,int port)
//构建一个套接字
InputStream getInputStream()
OutputStream getOutputSteam()
//获取可以从套接字中读取数据的流，以及可以向套接字写出数据的流
void connect(SokcetAddress address)
//将该套接字的连接到给定的地址
void connect(SocketAdrress address,int timeoutInMilliseconds)
//将套接字连接到给定的地址，如何在给定的时间没有响应则返回
void setSoTimeout(int timeoutMilliseconds)
//设置该套接字上读气你去的阻塞时间。如果 超过给定时间，则抛出一个interruptedIOExecption异常
boolean isConnected()
//如果该套接字已被连接，则返回true
boolean isClosed()
//如果套接字已经被关闭了，则返回true
void shutdownOutput()
//将输出流设为 "流结束"
void shutdownInput()
//将输入流设为"流结束"
boolean isOutputShutdown()
boolean isInputShutdown()
//如果输出流 或 输入流 被关闭 返回 true
~~~



~~~java
		java.net.ServerSocket
ServetSocket(int port)
//创建一个监听端口的服务器套接字
Socket accept()
//等待连接。该方法阻塞(即，空闲)当前线程知道建立连接为止。该方法返回一个Socket对象，程序可以 通过这个对象 与连接中的 客户端进行通信
void close()
//关闭服务器套接字
~~~





~~~java
		java.net.InetAddress
static InetAddress getByName(Strin host)
static InetAddress getAllByName(String host)
//为给定的主机名创建一个 InetAddress对象，或者一个包含了该主机名所对应因特网地址的数组
static InetAddress getLocalHost()
//为本地主机创建一个InetAddess对象
byte[] getAddress()
//返回一个包含数字型地址的字节数组
String getHostAddress()
//返回一个由十进制数组成的字符串
String getHostName()
//返回主机名

		java.net.InetSocetAddress
InetSocketAddress(String hostname,int port)
//用给定的主机和端口参数创建一个地址对象，并在创建的过程中解析主机名，如果主机名不能被解析，那么该地址对象的unresolved属性将被设为true
boolean isUnresolved()
//如果不能解析改地址对象，则返回true
~~~

~~~java
		java.nio.channels.SocketChannel
static SocketChannel open(SocetAddress address)
//打开一个套接字通道，并将其连接到远程地址

static InputStream newInputStream(ReadbleByteChannel channel)
//创建一个输入流，用以从指定的通道读取数据
static OutputStream newOutputStream(WritableByteChannel channel)
//创建一个输出流，用以指定的通道写入数据
~~~



## WEB

### URL

​	URL Uniform Resource Locator 统一资源定位符

​	URL类可以打开一个到达资源的流

​	URL和URLConnection类封装了大量复杂的实现细节，这些细节涉及到如何从远程站点获取信息。

~~~java
			java.net.URL
InputStream openStream()
//打开一个用于读取资源数据的输入流
URLConnection openConnection()
//返回一个URLConnection对象，该对象负责管理与资源之间的连接
~~~

~~~java
			java.net.URLConnection
void setDoInput(boolean doInput)
boolean getDoInput()
//如果doInput为true，那么用户可以接受来自该URLConnection的输入
                
void setDoOutput(boolean doOutput)
boolean getOutput()
//如果doOutput为true，那么用户可以将输出发送到该URLConnection

void setIfModifiedSince(long time)
long getIfModifiedSince()
//属性ifModifiedSince用于配置 该 URLConnection对象，使它只获取哪些自从 某个给定时间以来 被修改的数据。调用方法时需要传入的time参数值指的是 从 格林尼治时间 1970年1月1日午夜开始计算的秒数

void setUseCaches(boolean useCaches)
boolean getUseCaches()
//如果useCaches为true,那么数据可以从本地缓存中得到。请注意，URLConnection本身不维护这样的一个缓存，缓存必须由浏览器之类的外部程序提供

void setAllowUserInteraction(boolean allowUserInteration)
boolean getAllowUserInteraction()
//如果allowUserInteraction为true，那么可以查询用户的口令。请注意，URLConnection本身不提供这种查询功能，查询功能必须由浏览器或浏览器插件之类外部程序提供

void setConnectTimeout(int timeout)
int getConnectTimeout()
//设置或得到连接超时时限(单位:毫秒)。如果连接建立之前已经达到了超时的时限，那么相关连的输入流的connect方法会抛出一个SocketTimeoutException异常

void setReadTimeout(int timeout)
int getReadTimeout()
//设置读取数据的超时时限，如果在一个读操作成功之前就已经达到了超时的时限，那么read方法就会抛出SocketTimeoutException异常

void setRequestProperty(String key,String value)
//设置请求头的一个字段

Map<String,List<String>> getReqeustProperties()
//请求头属性的一个映射表，相同的键对应的所有值都被放置在同一个列表中

void connect() **
//连接远程资源并获取响应头信息 

Map<String,List<String>> getHeaderFields()
//返回响应的一个映射表。相同的键对应的索引值都被放置在同一个列表中

String getHeaderFieldKey(int n)
//得到响应头第n个字段的键，如果n小于等于0或大于响应头字段的总数，则该方法返回null值

String getHeaderField(int n)
//得到响应头第n个字段的值，如果小于等于0或大于响应头字段的总数，则返回null

int getContentLentgh()
//如果内容长度可获得，则返回该长度值，否则返回-1

int getContentType()
//获取内容的类型，比如text/plain 或 image/gif

String getContentEncoding()
//获取内容的编码机制，比如gzip。这个值不太常用，因为默认的identity编码机制并不是用Content-Encoding头来设定的

long getDate()
long getExpiration()
long getLastModified()
//获取创建日期，过期日以及最后一次被修改的日期。这些日期指的是从...

InputStream getInputStream()
OutputStream getOutputStream()
//返回从资源读取信息或向资源写入信息的流

Object getContent()
//选择适当的内容处理器，以便读取资源数据并将它转换成对象。该方法对于读取注入text/plain或 image/gif之列的标准内容类型并没有什么用处，除非你安装了自己的内容处理器
~~~

|       键名       |       方法名       | 返回类型 |
| :--------------: | :----------------: | :------: |
|       Date       |      getDate       |   long   |
|     Expires      |   getExpiration    |   long   |
|    Last-Modie    |   getLastModifed   |   long   |
|  Content-Length  |  getContentLength  |   int    |
|   Content-Type   |   getContentType   |  String  |
| Content-Encoding | getContentEncoding |  String  |

~~~java
			java.net.HttpURLConnection
InputStream getErrorStream()
//返回一个流，通过这个流可以读取web服务器的错误信息

			java.net.URLEncoder
static String encode(String s,String encoding)
//采用指定的字符编码模式 自字符串进行编码，并返回他的URL的编码形式。在URL编码中，'A'-'Z','a'-'z','0'-'9','-','_','.','*'等字符串保持不变，空格被编码成'+',所有其他字符被编码成 "%XY"形式的字节序列，其中0xXY为该字节十六进制数。

			java.net.URLDecoder
static String decode(String s,String encoding)
//采用指定编码模式对 已编码字符串s进行解码，并返回结果
~~~



### URI

​	URI Unifor Resource Identifier 统一资源表示符
​	URI类不包括任何用于访问资源的方法。它的唯一作用就是解析，还有另外一个作用是处理绝对标识符和相对标识符

