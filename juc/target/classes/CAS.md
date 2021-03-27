# CAS

### 什么是CAS

1. 用于并发

2. CAS有三个操作数：内存值V、预期值A、要修改的值B，当且仅当预期值A 和 内存值V 相同时，才将内存值修改为B，否则什么都不做。做好返回现在的V值。

3. CPU的特殊指令。 

4. CAS的等价代码 

5. ~~~java
   private volatile int value = 0;
   public synchronized int compareAndSwap(int expectedValue,int newValue){
       if(expectedValue == value){
           value = newValue;
       }
       return value;
   }
   ~~~

### 应用场景

- 乐观锁 
- 并发容器 ConcurrentHashMap()
- 原理类

### 如何利用CAS实现原子操作

加载Unsafe工具，用来直接操作内存数据。

用volatile关键字修饰value字段，保证可见性。{offset可以叫获得地址}

### Unsafe类

Unsfafe是CAS的核心类。Java无法直接访问底层操作系统，而是通过本地 (native)方法访问。不过尽管如此，JVM还是开了一个后门，JDK有一个类Unsafe，它提供了硬件级别的原子操作

valueOffset表示的是变量值在内存中的偏移地址，因为Unsafe就是根据内存偏移地址获取数据的原值，这样我们就能通过unsafe来实现CAS了。

### CAS也有不好的地方

ABA 在CAS的过程中，它只是去进行比较值，而不是去判断是否被修改。value=5;比如一个线程进行CAS将value修改成7了，第二个线程则将它修改成5了，导致下一次线程进来看的时候，发现他没有改变过，则会对它进行改变。575

自旋时间过长。