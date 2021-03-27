# JavaIO模型 

## Socket

所有java网络通信的端点。

### Unix中的Socket是什么? 

- Unix系统中一切皆是文件
- 文件描述符表是已打开文件的索引
- 每个进程都会维护一个文件描述符表

## 网络 同步 异步 阻塞 非阻塞

老王 烧水。

同步：老王在烧水的过程中 有人给他发消息，它没有处理 

异步 ：老王在烧水的过程中 有人给他发消息， 它处理了

阻塞：老王在烧水的过程中 一直等着水烧开

非阻塞：老王在烧水的过程中 可以干别的事。

同步和异步：指得是通信过程中的机制两种不同的区别

阻塞和非阻塞：指的是等待 调用结果返回结果之前。

## Scoket和ServerSocket

![](images/Socket%E5%92%8CServerSocket.png)

~~~java
Socket();
Sokcet(Integer address,int port );
//创建一个socket
void bind(SokcetAddress bindpoint);
//将套接字绑定到本地端口
InputStream getInputStream();
//获取套接字的输入流
OutputStream getOutputStream();
//获取套接字的输出流
IntAddress getInetAddress();
//返回套接字的所连接的地址
int getPort();
//返回次套接字连接到的远程端口号
boolean isBound();
//返回套接字的绑定状态。  
boolean isClosed(); 
//返回套接字的关闭状态。  
boolean isConnected(); 
//返回套接字的连接状态。  
boolean isInputShutdown(); 
//返回套接字连接的读入是否关闭。  
boolean isOutputShutdown(); 
//返回套接字连接的写是否关闭。  

				//serverSocket
ServerSokcet();
//创未绑定的服务器套接字
ServerSocket(int port);
//创建绑定到指定端口的服务器套接字。
Socket accept();
//以阻塞式的方式 侦听要连接到此套接字并接受它。  
~~~

## 非阻塞式IO

- 使用Channel代替Stream
- 使用Selector监听多条Channel
- 可以在一个线程里处理多个Channel I/O

### Channel与Buffer

#### Buffer

- Capacity表明Buffer的最大容量，一旦写入超过这个容量时，需要清空后才能使用。
- Position:当byte写入到Buffer之中时，position会移动到向后一个可移动的数据单元，最大容量为容量-1。当读取数据时，buffer会从写模式转换为读模式，此时position会重置为0
- limit 在写模式下buffer的limit为capactiry，当切换为读模式时，limit会被设置成position下的写模式到达的位置。

~~~java
void filp();
//将写模式转换为读模式，将limit设置到position最大的位置，position设置为0
void clear();
//清除缓冲区，将limit设置为capacity的位置-1，position为0
ByteBuffer compact();
//压缩空间，将未读的数据把它copy到buffer最开始的位置，并将position位置指向copy的数据下一一个位置。通常与filp()使用。意思就是：写入了一些数据，filp()读取了一部分数据，但是这时候又想写入数据 这时候调用compact() 会将未读的数据copy到buffer最开始的位置 
~~~

#### Channel

![](images/Channel%E7%B1%BB%E5%9B%BE.png)

![](images/SocketChannel%E7%B1%BB%E5%9B%BE.png)

![](images/ServerSocketChannel%E7%B1%BB%E5%9B%BE.png)

~~~java
//ServerSokcetChannle和SocketChannel都继承自AbstractSelectableChannel
			//AbstractSelectableChannel
boolean isBlocking();
//告诉这个通道上的每个I/O操作是否会阻塞知道完成
boolean isRegistered();
//告诉这个频道当前是否在任何选择器上注册。
SelectionKey keyFor(Selector sel);
//检索表示频道注册的键与给定的选择器
SelectionKey register(Selector sel,int ops,Object att);
//使用给定的选择器注册次频道，返回一个选择键 

					//ServerSokcetChannel
static ServerSocketChannel opne();
//打开服务器插槽通道
abstract ScoketChannel accept();
//接受与此 服务器套接字的连接
SelectionKey  keyFor(Selector sel);
//检索表示 通道 注册的 键与给定的选择器
SelectionKey register(Selector sel,int ops,Object att);
//使用给定的选择器注册此 通道，返回一个选择键

					//SocketChannel
abstract SocketChannel bind(SocketAddress local);
//将通道的套接字绑定到本地地址
abstract SocketAddress getLocalAddress();
//返回此通道的套接字所绑定的套接字地址
abstract boolean isConnected();
//告诉本套接字通道 是否还连接
abstract int read(ByteBuffer dst);
//从该通道读取到给定缓冲区的字节序列
long read(ByteBuffer[] dsts);
//从该通道读取给定缓冲区的字节序列
abstract int write(ByteBuffer src);
//从给定缓冲区向该通道写入一个字节序列
long write(ByteBuffer srcs);
//从给定的缓冲区向该通道写入一系列字节
					//Selector
abstract void close();
//关闭此选择器
abstract boolean isOpen();
//告诉这个选择器是否打开
abstract Set<SelectionKey> keys();
//告诉此选择器的键集
static Selector opne();
//打开选择器
abstract SelectorProvider provider();
//返回创阿金此通道的提供程序
abstract int select();
//选择一组其相应的通道准备好进行I/O操作的键
abstract int select(long timeout);
//选择一组和器其相应通道准备好进行I/O操作的键
abstract Set<SelectionKey> selectedKeys();
//返回此选择器的选择键集
abstract Selector wakeup();
//导致尚未返回的第一个选择操作立即返回

				//SelectionKey
Object attch(Object obj);
//将给定对象附加到此键。
Object attachment();
//检索当前附件
abstract void cancel();
//要去取消该密钥的通道与其选择器的注册
abstract Selector selector();
//返回创建此键的选择器
boolean isAcceptable() 
//测试此密钥的通道是否已准备好接受新的套接字连接。  
boolean isConnectable() 
//测试此密钥的通道是否已完成或未完成其套接字连接操作。  
boolean isReadable() 
//测试此密钥的频道是否可以阅读。  
boolean isWritable() 
//测试此密钥的通道是否准备好进行写入。  
~~~

