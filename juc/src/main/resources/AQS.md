# AQS

### 为什么需要AQS

不仅是ReentrantLock和Semaphore，包括CountDownLatch，ReentrantReadWriteLockd等都有这样类似的“协作”（或者叫同步）功能，其实，它们底层都用了一个共同的基类，这就是AQS。

### Semaphore和AQS的关系

Semaphore有个Sync内部类Sync里面继承了AQS 

### 如果没有AQS

就需要每个协作工具自己实现：

1. 同步状态的原子性管理
2. 线程的阻塞与解除阻塞
3. 队列管理

### AQS的作用

AQS是一个用于构建锁、同步器、协作工具类的工具类（框架）。有了AQS以后，更多的协作工具类都可以很方便得被写出来。Doug Lea java.util.concurrent 这个大佬写的

![](D:%5Ctask%5Cpeng_imooc%5Cjuc%5Csrc%5Cmain%5Cresources%5Cimages%5CAQS%202png.png)

### AQS内部原理解析

AQS最核心的三大部分：

#### state状态

~~~java
private volatile int state ;
//这里的state的具体含义，会根据具体实现类的不同而不同，比如在Semaphore里，他表示“剩余的许可证数量”，而在CountDownLatc，它表示“还需要倒数的数量”
//所有修改state的方法都需要保证线程安全，比如getState,setState已经compareAndSetState操作来读取和更新这个状态 

/*
	在ReentrantLock中，state用来表示"锁"的占有情况，包括可重入计数
	当state的值为0的时候，标识 该Lock不被任何线程锁占有
*/
~~~



#### 控制线程抢锁和配合FIFO队列

这个队列用来<b style="color:red">存放“等待的线程”</b>，AQS就是“排队管理器，当多个线程争用同一把锁时，必须有排队机制将那些没能拿到锁的线程串在一次。当锁释放时，锁管理器就会挑选一个合适的线程来占有这个刚刚释放的锁

AQS会维护一个等待的线程队列，把<b style="color:red">线程都放进这个队列里</b>

![](D:%5Ctask%5Cpeng_imooc%5Cjuc%5Csrc%5Cmain%5Cresources%5Cimages%5CAQS%203.png)

#### 期望协作工具类去实现的获取/释放等重要信息。

这里的获取和释放方法，是利用AQS的协作工具类里面的最重要的方法，是由协作类自己去实现的，并且含义各不相同

#### 获取方法

获取操作会依赖state变量，经常会阻塞（比如获取不到锁的时候)。

在Semaphore中，获取就是acquire方法，作用是获取一个许可证。

而在CountDownLatch里面，获取就是await方法，作用是"等待，直到倒数结束"。

#### 释放方法

- 释放操作不会阻塞
- 在Senaphore中，释放就是release方法，作用是释放一个许可证
- 在CountDownLatch里面，获取就是countDown方法，作用是“倒数1个数”

### AQS用法

1. 写一个类，想好协作的逻辑，实现获取/释放的方法。
2. 内部写一个Sync继承 AbstractQueuedSynchronizer 
3. 根据是否独占来重写,<b style="color:red">tryAcqurie/tryRelease</b> 或 <b style="color:red">tryAcquireShared(int acquires) 和 tryReleaseShared(int releases)</b> 等方法，在之前写的获取/释放方法中调用 AQS的acquire/release或 Shared方法 

### AQS在CountDownLatch总结

AQS在CountDownLatch的await方法时，便会尝试获取"共享锁"，不过一开始是获取不到锁的，于是线程被阻塞。

#### AQS在Semaphore的应用

在Semaphore中，state表示许可证的剩余数量，

看tryAcqurie方法，判断nonfairTryAcquireShare大于等于0，代表成功

这里会先检查<b style="color:red">剩余许可证数量够不够这次需要的</b>，用减法计算，如果直接不够，那就<b style="color:red">返回负数，表示失败</b>，如果够了，就用自旋加compareAndSetState来改变state状态，知道改变<b style="color:red">成功就返回正数</b>；或者期间如果被其他人修改了导致剩余数量不够了，那也就返回负数代表获取失败。

### AQS在ReentrantLock的应用

由于是可重入的，所以state代表重入的次数，每次释放锁，先判断是不是当前持有锁的线程释放的，如果不是就抛出异常，如果是的话，重入次数就减一，如果减到0，就说明完全释放了，于是free就是true，并且把state设置为0。

















