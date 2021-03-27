## Scalable IO in Java(java可扩展的IO nio)

Doug Lea 大神

## Outline（轮廓）

* Scalable network service

> 可扩展的网络服务

* Event-driven processing

> 事件驱动 运算

* Reactor pattern

* > Reactor 模式

  * Basic version （基本 版本）
  * Multithreaded version （多线程 版本）
  * Other variants （其它 变体）

* Walkthrough of java.nio nonblocking IO APIs

* > 演练 java.nio 非阻塞 IO API

## NetWork Service （网络服务）

Web Services, Distributed Objects, etc

> web 服务，分布式对象，etc

Most have same basic structure:

> 大部分以下基本结构

1. Read request （读取请求）
2. Decode request （解码请求）
3. Process service （服务过程）
4. Encode reply （编码回复）
5. Send reply （发送回复）

But differ in nature and cost of each step

> 但每一步的性质和成本不同

XML parsing,File transfer, Web page generation, computational service,...

**classic Service Designs 经典服务设计**

![](images/ClassicServiceDesigns.png)

Each handler may be started in its own thread

> 每个处理程序可以在自己的线程中启动

**Classic ServerSocket Loop**

~~~java
class Server implements Runnable {
	public void run() {
		try {
		ServerSocket ss = new ServerSocket(PORT);
		while (!Thread.interrupted())
			new Thread(new Handler(ss.accept())).start();
			// or, single-threaded, or a thread pool
		} catch (IOException ex) 
		{ /* ... */ }
}
static class Handler implements Runnable {
	final Socket socket;
	Handler(Socket s) { socket = s; }
	public void run() {
		try {
			byte[] input = new byte[MAX_INPUT];
			socket.getInputStream().read(input);
			byte[] output = process(input);
			socket.getOutputStream().write(output);
		} catch (IOException ex) { /* ... */ }
}
	private byte[] process(byte[] cmd) { /* ... */ }
	}
}
~~~

