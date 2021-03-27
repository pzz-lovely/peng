# Java内存

## 底层原理 

重要性:Java面试的必考点。

java代码到CPU的过程:

1. 最开始，编写*.java文件
2. 在编译 javac文件后，将刚才的*.java文件会变出一个新的Java字节码文件
3. JVM会执行刚才生成的字节码文件，并将字节码文件转换为 机器指令
4. 机器指令可以直接在 CPU上运行，也就是说最终程序执行。

## JVM内存结构

JVM内存结构，和Java虚拟机的 <span style="color:red">运行时区域</span> 有关

|   运行时数据区(Runtime Data Area)    |                            描述                             |
| :----------------------------------: | :---------------------------------------------------------: |
|         方法区(Method Area)          | 所有线程间共享(Runtime Data Areas Shared Aming All Threads) |
|               堆(Heap)               | 所有线程间共享(Runtime Data Areas Shared Aming All Threads) |
|          Java栈(Java Stack)          |          线程私有(Thread Specific Runimtime Areas)          |
|   本地方法栈(Native Method Stack )   |          线程私有(Thread Specific Runimtime Areas)          |
| 程序计数器(Program Counter Register) |          线程私有(Thread Specific Runimtime Areas)          |

1. Class文件 进入 类加载器 Class Loader
2. 将 Class文件 加载进 运行时数据区

![](images\JVM内存结构.png)

堆(Heap): 运行时区域最大的一块，主要 保存 new，或者其他指令所创建的对象实例。并且这些实例对象不在引用，就会被垃圾回收。是在运行的时候动态 分配

虚拟机栈(VM stack):保存了各个的基本类型。主要保存 对象的引用。在编译期间就确定了大小

方法区(method):主要是存储了各个static的静态变量，或者类信息，常量信息，或者也有永久引用

本地方法栈:保存和本地方法相关的。

程序计数器: 主要是保存当前线程所执行到的行号数。在上下文切换的时候，这个数据也会被保存下来，也有分支 ，跳转

## Java内存模型

Java内存模型，和Java的 <span style="color:red">并发</span> 编程有关

## Java对象模型 

Java对象模型，是Java对象在 <span style="color:red">虚拟机中的表现形式</span> 有关

![](images\java对象模型.png)

Java对象在 内存 中的 存储模型 

1. 首先JVM会 给这个类创建一个instanceKlass，保存在方法区，用来在JVM层表示该Java类
2. 当我们在Java代码中，使用new创建一个对象的时候，JVM会创建一个InstanceOopDesc对象，这个对象中包含了对象头以及实例数据

## JMM

Java Memory Model java内存模型 

JMM实际上是一个规范。是一组规范，需要各个JVM的实现来遵守JMM规范，以便于开发者可以利用这些规范，更方便地开发多线程程序。

volatile、synchronized、lock等 实现原理都是JMM。没有JMM就需要我们指定内存栅栏(工作内存和主内存之间的copy和同步)。

### 重排序

重排序好处: 提高处理速度
会对重排序前后的指令优化。

![](D:\BaiduNetdiskDownload\线程八大核心+Java并发底层原理精讲（Java并发核心知识体系精讲）\362\其它学习资料\技术图片\重排序前指令.png)重排序前

![](D:\BaiduNetdiskDownload\线程八大核心+Java并发底层原理精讲（Java并发核心知识体系精讲）\362\其它学习资料\技术图片\重排序后指令.png)重排序后

重排序:

~~~java
a=0,b=0,x=0,y=0;
//线程1 a=1;x=b;
//线程2 b=1;y=a;
~~~

在线程1内部的两行代码的实际执行顺序的代码 在Java文件中的顺序不一致。代码指令 并不是 严格的 按照 代码语句顺序执行，他们的顺序被改变了，这就是重排序，这里被颠倒的是y=a和b=1这个两行语句。而对于有依赖关系的 则不会重排序

重排序的三种优化: 编译器优化 Cpu指令重排 内存的"重排序 "

1. 编译器优化 :    JVM,JIT编译器等。
2. Cpu指令重排 : 就算编译器不发生重排，CPU也可能对指令进行重排
3. 内存的"重排序" :线程1的修改 线程2看不到。(在JMM中 线程1对主存的中数据A进行修改，首先先将这个值 保存在本地内存，然后再写回到主存中，但是这时线程2读取A，他会首先看本地内存有没有这个A的值，它就读取，则不会到主存中读取了)

### 可见性

1. 什么是可见性问题: (针对于JMM)每个线程都有自己的本地内存(localCache)，线程首先会读取自身的本地内存

2. Cpu可见性问题: 
   cpu有多级缓存,会导致读的数据过期
   高速缓存的容量比主内存小，但是速度仅次于寄存器，所有CPU和主内存之间就多了Cache层。
   线程间的对于共享变量的可见性问题 不是直接由 多核引起的，而是由多 缓存引起的。
   如果所有的核心都只用一个缓存，那么也就不存在内存可见性问题
   每个核心都会将自己的需要的数据读到独占缓存中 ，数据修改后 也是写入到缓存中，然后等待刷入到主存中。所有会导致有些核心读取的值是一个过期的值。

3. JMM的抽象: 主内存和本地内存 
   Java作为高级语言，屏蔽了这些底层细节，用JMM定义了一套读写内存数据的规范，虽然我们不在需要关心 一级缓存和二级缓存的问题，但是，JMM抽象了 主内存和本地内存的概念。
   这里说的本地内存 并不是真的是 一块给 每个线程 分配的 内存，而是JMM的一种抽象，是对于寄存器，一级缓存，二级缓存等的抽象

   ![](D:\BaiduNetdiskDownload\线程八大核心+Java并发底层原理精讲（Java并发核心知识体系精讲）\362\其它学习资料\技术图片\主内存和工作内存1.png)

   ![主内存和工作内存2](D:\BaiduNetdiskDownload\线程八大核心+Java并发底层原理精讲（Java并发核心知识体系精讲）\362\其它学习资料\技术图片\主内存和工作内存2.png)
   JMM有以下 规定：

   ​	1.所有的变量都存储在主内存中，同时每个线程也有自己独立的工作内存，工作内存中的变量内容 是主内存中的拷贝
   ​	2.线程不能直接读写主存中的变量而是只能操作 自己工作内存中的变量，然后在同步到主内存中。
   ​	3.主内存是多个线程共享的，但线程间不共享工作内存，如果线程间需要通信，必须借助主内存 中转来完成。

   1. Happpens-Before原则
      可见性: 使其他线程都能看到操作。

   <ul>
       <li>单线程原则: 如果你发生了重排序，被排在后面的语句依然能看到前面的</li>
       <li>锁操作(synchronzied和lock) : 如果一个线程对一个锁进行解锁了，而另外一个线程加锁了。加锁之后一定能看到解锁之前的所有操作</li>
       <li>volatile变量 : 一定从内存中读取或修改</li>
       <li>线程的启动 : 子线程所 执行的 所有语句都能看到主线程之前的发生结果</li>
       <li>线程join : 一旦join了，那后面的语句一定能看到所有我 等待那个线程所执行的所有语句</li>
       <li>传递性 : 如果hb(A,B) 而且hb(B,C)那么就推出 hb(A,C)</li>
       <li>中断 :一个线程被其他线程interrupt时，那么检测中断(IsInterrupted)或者抛出InterruptedException一定能看到 </li>
       <li>构造方法:对象构造方法的最后一行指令 happens-before于 finalize()方法的第一行指令 </li>
       <li>工具类的Happends-Befor原则
           <p>比如:线程安全的容器get一定能看到在此之前的put等存入动作</p>
           <p>CountDownLatch</p>
           <p>Semapthore</p>
           <p>Future</p>
           <p>线程池</p>
           <p>CyclicBarrier</p>
       </li>
   </ul>

   
4. volatile关键字: 每次线程 写数据的时候 都会 flush到主内存，而不是先写回到本地内存。

5. 能保证可见性的措施  voltile 

6. synchronized可见性的正确理解
   如果一个线程对一个锁进行解锁了，而另外一个线程加锁了。加锁之后一定能看到解锁之前的所有操作。

## volatile关键字

1. volatile是一种同步机制，比synchronized或者Lock相关类更轻量，因为使用volatile并不会发生 上下文切换等开销大的行为。
2. 如果一个变量修改成volatile，那么JVM就知道了这个变量是可能会被 并发修改的
3. 虽然开销小，但是相应的能力也小，虽说volatile是用来同步的保证线程安全，但是volatile做不到 synchronzied那样的 原子保护，volatile仅在很有限的场景下 才能发挥作用 

#### 作用: 可见性，禁止重排序

1. 可见性: 读取一个volatile变量之前，需要先使相对应的本地缓存失效 ， 这样就必须 到 主内存 读取 最新值 ，写一个 volatile属性会立即刷入到主内存。
2. 禁止指令重排序优化: 解决单例双重锁乱序问题 

#### 适用场合

booelan flag，如果一个共变量自始至终只被各个线程 直接 赋值，而没有其他操作，那么就可以使用 volatile代替synchronzied或者代替原子变量。因为赋值自身是由原子性的，而volatile又保证了可见性，所以就足以保证线程安全。

作为刷新之前的变量触发器 

~~~java
Map configOptions;
char[] configText;
volatile boolean ;

//Thread A 
configOptions = new HashMap();
configText = readConfigFile(fileName)
processConfigOptions(configText,configOptions)
initalized=true;

//Thread B
while(!initialized)
	sleep();
//initialized在更新之前 的前面三个变量 一定能被 Thread B读取到
~~~

#### 总结 volatile和synchronized的关系

volatile在这方面可以看做是轻量版的 `synchronized` ：如果一个共享变量自始至终 只被各个线程 直接赋值，而没有其他的操作，那么就可以用volatile来代替synchronzied或者代替原子变量，因为赋值自身是有原子性的，而voaltile又保证了可见性，所以就足以保证线程安全。

![](images\volatile小结1.png)

![](images\volatile小结2.png)

#### synchronized

synchronized不仅保证了原子性，还保证了可见性 

synchronized不仅让保护的代码安全，还可让下一次 进入锁时 可以看到 上一次拿到锁 的操作。

### 原子性

#### 什么是原子性

一系列的操作，要么全部执行成功，要么全部不执行，不会出现执行一半的情况，是不可分割的。

#### Java中的原子操作有哪些

1. 除long和double之外的基本类型()int,byte,booelan,short,char,float)的赋值操作
2. 所有引用 reference的赋值操作， 不管是32位的机器还是64位的机器。
3. java.concurrent.atomic.* 包中所有类的原子操作

#### long和double的原子性

#### 原子操作+原子操作 != 原子操作







