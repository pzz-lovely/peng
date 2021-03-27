# ThreadLocal

### 两大使用场景

#### 典型场景1：每个线程需要一个独享的对象(通常是工具类，典型需要使用的类有SimpleDateFromat和Random)。

每个Thread内有自己的实例副本 ，不共享。比喻：有一本教材书，分给班级30个一起看，这就相当于多个线程访问一个变量。然后老师将这个本书 复印30份 就解决了。。

#### 典型场景2：每个线程内需要保存全局变量(例如在拦截器中获取用户信息)，可以让不同方法直接使用，避免参数传递的麻烦



#### 方法

T initialValue() 该方法会返回当前线程对应的 "初始值"，这是一个延迟加载的方法，只有在调用 get 的时候才会触发。还有一种 set方法  就不会调用 这个方法了。

void set(T t) 为这个线程设置一个新值 

T get() 得到这个线程对应的value。如果是首次调用get()，则会调用 initialize来得到这个值。是取出当前线程的ThreadLocalMap，然后调用map.getEntry方法，把本地ThreadLocal的引用作为 参数 传入，取出map中属于本地ThreadLocal

void remove() 删除这个线程对应的值。

#### 好处

1. 免去传参的繁琐：无论是场景一的工具列，还是场景二的用户名，都可以在任何地方通过ThreadLocal拿到，再也不需要每次都传同样的参数。ThreadLocal使得代码耦合度更低，更优雅

### 原理 (记得瞅源码)

![](images/ThreadLocal%E5%8E%9F%E7%90%86.png)

这个map以及map中的Kye和value都是保存在线程中的，而不是保存在ThreadLocal中

#### 总结

- 让某个需要用到的对象在线程间隔离(每个线程都有自己独立的对象)；
- 在任何方法中都可以轻松获取到这个对象。

#### 注意点 

- 内存泄露 ： 某个对象不再有用，但是占有的 内存却 不能回收。
- 弱引用的特点是，如果这歌对象只被弱引用关联(没有任何强引用关联)，那么这个对象就可以被回收。弱引用不会阻止 GC
- ThreadLocalMap每个 Entry都是一个对 key的弱引用，同时，每个Entry都包含了一个对value的强引用
- 正常情况下，当线程终止，保存在ThreadLocal里的value会被垃圾回收，因为没有任何强引用了
- 但是线程不终止 (比如线程需要保持很久) ，那么key对应的value就不能被回收，因此有以下调用链
- Thread ——> ThreadLocalMAP ——> Entry(key为null)  ——> Value
- 因为value和Thread之间还存在这个强引用链路，所以导致 value 无法回收，就可能出现OOM
- JDK已经考虑到这个问题，所以在set,reomve,rehash方法中会扫描key为null的Entry，并把对应的value设置为null,这样value对象就可以被回收

如何避免内存泄露 (阿里)

调用remove方法，就会删除对应的Entry对象，可以避免内存泄露，所有使用完ThreadLocal之后，应该调用remove 方法



Spring中也用到了ThreadLocal 框架有支持了 尽量用框架。RequestContextHolder，DateTimeContextHolder类。

### ThreadLocalMap

ThreadLocalMap类，也就是<b style="color:red">Thread.threadLocals</b>

ThreadLocalMap类是每个线程 Thread类里面的变量，里面最重要的是一个键值对数组 Entry[] tabel，可以认为是一个map 键值对 

- 键：这个ThreadLocal
- 值：实际需要的成员变量，比如要 set 进的值。