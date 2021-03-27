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

