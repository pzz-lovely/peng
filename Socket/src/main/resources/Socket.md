# Socket

### Socket与TCP、UDP

- Socket简单来说是IP地址与端口的结合协议(RFC 793)
- 一种地址与端口的结合描述协议
- TCP/IP 协议的相关API的总称；是网络Api的集合实现
- 涵盖了：Stream Socket/Datagram Socket

### Socket的作用与组成

- 在网络传输中用于唯一标识 两个断点之间的 连接
- **端口：**包括(IP + Port)
- 4个要素：客户端地址、客户端端口、服务器地址、服务器端口

### Socket传输原理

IP Address +Port number = Socket;

### Socket之TCP

- TCP是面向连接的通信协议
- 通过三次握手建立连接，通讯完成时要拆除连接
- 由于TCP是面向连接的所有只能用于端(口)到端(口)的通讯

### Socket之UDP

- UDP是面向无连接的通讯协议
- UDP数据包括 目(标)的 端口号和源(自己)端口号信息
- 由于通讯是不需要连接，所有可以实现广播发送，并不局限于端(口)到端(口)

###  UDP和TCP的区别

UDP是面向全部的，TCP是一个对一个

### Client - Server Application

TCP/IP协议中，两个进程间通信的主要模型为：CS模型 客户端 服务器

主要目的：协同网络中的计算机资源。服务模式、进程间数据共享

常见的：FTP、SMTP、HTTP

### 报文段

- 报文段是指TCP/IP协议网络传输过程中，起着路由导航作用
- 用以查询各个网络路由段、IP地址、交换协议等IP数据包 
- 报文段充当整个TCP/IP协议数据包的导航路由功能
- 报文在传输过程中会不断封装成分组、包、帧来传输
- 封装方式就是添加一些控制信息组成的首部，即报文头

## 传输协议

- 协议顾名思义，一种规定，约束
- 约定大于配置，在网络传输中依然适用；网络的传输流程是健壮的稳定的，得益于基础的协议构成。
- 简单来说：A->B的传输数据，B能识别，反之B->A的传输数据A也能识别，这就是协议

### Mac地址

Media Access Control 或者 Medium Access Control。意译为媒体访问控制、或称为物理地址。硬件地址

用来定义网络设备的位置

形如:44-45-53-54-00-00;与身份证类似。

### IP地址

IP地址有32为二进制数组成，常以 XXX.XXX.XXX.XXX形式表现，每组XXX代表小于或等于255的十进制数

分为A、B、C、D、E、五大类，其中E类属于特殊保留地址 

#### IP地址 -IPV6

总共有128位长，IPV6地址的表达形式，一般采用32个十六进制数。也可以想象为1623个。

由两个逻辑部分组成：一个64位的网络前缀和一个64位的主机地址，主机地址通常根据物理地址自动生成，叫做EUI-64(或者64-位扩展唯一标识 )

IPv4转换为IPv6一定可行，IPv6转换为IPv4不一定可行

### 端口

如果把IP地址比作一个房子，端口就是出入这间房子的门或者 窗户

### 数据传输层

![](images/%E6%95%B0%E6%8D%AE%E4%BC%A0%E8%BE%93%E5%B1%82%E6%AC%A1.png)

### UDP是什么

- 英语：User Datagram Protocl，缩写为UDP
- 一种用户数据报协议，又称用户数据报文协议。
- 是一种简单的面向数据报的传输层协议，正式规范为RFC 768
- 用户数据协议、非连接协议 

#### 为什么不可靠

- 它一旦把应用程序发给网络层的数据发送出去，就不保留数据备份。
- UDP在IP数据报的头部仅仅加了复用和数据效验(字段)
- 发送端生产数据，接收端从网络中抓取数据。
- 结构节点、无效验、速度快、容易丢包、可广播

#### UDP能做什么

DNS(解析)、TFTP(跟FTP差不太多，用于文件传输)、SNMP(网络数据协议传输 监控的一个协议)

视频(直播，无限电视)、音频、普通数据(无关紧要数据)

![](images/UDP%E6%8A%A5%E6%96%87.png)

#### UDP包最大长度

16位->2字节 存储长度信息

2^16 -1 = 64K -1 = 65535-1 = 65535字节 - 8 65507 byte

### Java API DatagramSocket

用于接收与发送UDP的类

负责发送某一个UDP包，或者接收UDP包

不同于TCP，UDP并没有合并到Socket Api中 

~~~java
				//DatagramSocket 
DatagramSocket();//创建简单实例，不指定端口与ip
DatagramSocket(int port);//创建监听固定端口的实例 
DatagramSocket(int port,InetAddress localAddr);//创建固定端口指定IP的实例

receive(DatagramPacket d);
//用于接收
send(DatagramPacket d);
//发送
setSoTimeout(int timeout);
//设置超时 毫秒
close();
//关闭、释放资源

			//Api DatagramPacket 仅仅用于发送时有效，在收到数据时，系统会自动
DatagramPacket(byte[] buf,int offset,int length,InetAddress address,int port);
DatagramPacket(byte[] buf,int length,SokcetAddress address);
//SocketAddress相当于与InetAddress+Port
setData(byte[] buf)
setData(byte[] buf,int offset,int length);
//设置数据
setLenght(int length);
//设置数据的长度
setAddress(InetAddress iaddr);
setPort(int port);
//设置地址 或 端口
setSocketAddress(SocketAddress address);
getter...
~~~

- 用于处理报文。

- 将byte数组。目标地址、目标端口等数据包装成报文或者将报文拆卸成byte数组。
- 是UDP的发送体，也是接收实体。

###  UDP单播、广播、多波

![](images/UDP%E5%8D%95%E6%92%AD%E3%80%81%E5%B9%BF%E6%92%AD%E3%80%81%E5%A4%9A%E6%92%AD.png)

仅仅只能在我们在自己的局域网 才能发。

### IP地址类别

![](images/IP%E5%9C%B0%E5%9D%80%E7%B1%BB%E5%88%AB.png)

### IP地址构成

![](images/IP%E5%9C%B0%E5%9D%80%E6%9E%84%E6%88%90.png)

### TCP

- 英语：Transmission Control Protocol
- TCP是传输层控制协议；是一种<b style="color:red">面向连接的、可靠的、基于字节流</b>的传输通信协议 ，由IETF的RFC 793定义
- 与UDP一样完成第四层传输层 所指定的功能与职责

#### TCP机制

- 三次握手、四次挥手
- 具有校验机制、可靠、数据传输稳定。

#### TCP能做什么

- 聊天消息传输、推送
- 单人语言、视频聊天
- 几乎UDP能做的都能做，但需要考虑复杂性、性能问题。

#### TCP核心API

~~~java
socket();
//创建一个 无状态 socket1

bind();
//绑定 一个Socket到本地地址和端口上
connect();
//连接到远程套接字
accept();
accept(long timeout);
//接受一个新的连接
			//Socket
void setSoTimeout(long timeout);
//设置读取超时时间为2秒
void setReuseAddress(boolean flag);
//是否复用未完全关闭的Socket地址，对于指定Bind操作后的套接字 有效
void setTcpNoDelay(boolean flag);
//是否开始Nagle算法 
void setKeepAlive(boolean flag);
//是否需要在长时无数据响应时发送确认数据(类似心跳包)，时间大约为2小时
void setSoLinger(boolean flag,long linger);
//对于cl ose关闭操作行为进行这样的处理；默认为false,0
//false,0：默认情况，关闭时立即返回，底层系统接管输出，将缓冲区内的数据发送完成
//true、0：关闭时立即返回，缓冲区数据抛弃，直接发送RST结束命令到对方，并无需经过2MSL等待
//true、200：关闭时最长阻塞200毫秒，随后按第二情况处理

void setReceiveBufferSize(long size);
void setSendBufferSize(long size );
//设置接收发送缓冲区大小
void setPerformancePreferences(int connectionTime, int latency,int bandwidth);
//设置性能参数：短连接、延迟、带宽的相对重要性
~~~



 

