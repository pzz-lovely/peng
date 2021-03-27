## 问题

（1）线程池中的任务未来是怎么执行的？

（2）我们能写到哪些好的设计模式？

（3）对我们未来学习别的框架有什么帮助？

## 继承体系

![](images/FutureTask.png)

FutureTask实现了RunnableFuture接口，而RunnableFuture接口组合了Runnable接口和Future接口的能力，而Future接口提供了get()任务返回值的能力。

问题：**submit()方法返回的为什么是Future接口而不是RunnableFuture接口或者FutureTask类呢？**

答：这是因为submit()返回的接口，对外部只想暴露其get()的能力（Future接口），而不像暴露其run()的能力（Runnable接口）。

## 源码分析

submit方法，它是提交有返回值任务的一种方式，内部是未来任务的（FutureTask）包装，再交给execute()去执行，最后返回未来任务的本身。

~~~java
public <T> Future<T> submit(Callable<T> task){
    // 非空检测
    if(task == null ) throw new NullPointerException();
    
    // 包装成FutureTask
    RunnableFuture<T> ftask = newTaskFor(task);
    
    // 交给execute()去执行
    execute(ftask);
        
    // 返回futureTask
    return ftask;
}

protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
    // 将普通任务包装成FutureTask
    return new FutureTask<T>(callable);
}
~~~

### FutureTask类的run()方法

经过普通任务执行流程，我们知道execute()方法最后调用的是task()的 run()方法，上面我们传进去的任务，最后被包装成FutureTask，也就是说execute()方法最后会调用到FutureTask的run()方法，所以看RunnableTask的run()方法就ok了

~~~java
// java.util.concurrent.RunnableFuture
public void run(){
    // 状态不为NEW，或者修改为当前线程来运行这个任务，则直接返回
    if(state != NEW  || !UNSAFE.compareAdnSwapObject(this,null,Thread.curentThread()))
        return;

    try{
        // 真正的任务
        Callable<V> c = callable;
        // state必须为NEW时才运行
        if(c != null && state == NEW){
            // 运行的结果
            V  result;
            try{
                // 任务执行的地方
                result = c.call();
                // 已执行完毕
                ran = true;
            }catch(Throwable  ex){
                result = null;
                ran = false;
                // 处理异常
                setException(ex);
            }
            if(ran)
                // 处理结果
                set(result);
        }
    }finally{
        // 置为null
        runner = null;
        // 处理中断
        int s = state;
        if(s > INTERRUPTING)
            handlePossibleCancellationInterrupt(s);
    }   
}
~~~

先做状态的检测，再执行任务，最后处理结果或异常。

~~~java
protected void setException(Throwable t){
    // 将状态从NEW置为COMPLETING
    if(UNSAFE.compareAndSwapInt(this,stateOffset,NEW,COMPLETING)){
        // 返回值置为传进来的异常（outcome为调用get()方法时返回的）
        outcome = t;
        // 最终的状态设置为EXCEPTIONAL
        UNSAFE.putOrderedInt(this,stateOffset,EXCEPTIONAL); // final state
        // 调用完成方法
        finishCompletion();
    }
}

protected void set(V v){
    // 将状态从NEW置为COMPLETING
    if(UNSAFE.compareAndSwapInt(this,stateOffset,NEW,COMPLETING)){
        // 返回值置为传进来的结果（outcome为调用get()方法时返回的）
        outcome = v;
        // 最终的状态设置为NORMAL
        UNSAFE.putOrderedInt(this,stateOffset,NORMAL);
        // 调用完成方法
        finishCompletion();
    }
}
~~~

咋一看，这两个方法似乎差不多，不同的是出去的结果不一样且状态不一样，最后都调用了finishCompletion()方法

~~~java
private void finishCompletion(){
    // 如果队列不为空（这个队列实际上为调用者线程）
    for(WaitNode q; (q = waiters) != null;){
        // 置空队列
        if(UNSAFE.compareAndSwapObject(this,waiterOffset,q,null)){
            for(;;){
                // 调用者线程
                Thread t = q.tread;
                // 如果调用者线程不为空，则唤醒它
                LockSupport.unpark(t);
                WaitNode next = q.next;
                if(next == null)
                    break;
                q.next = null;  // unlike to help gc
                q = next;                
            }   
            break;
        }   
    }
    // 钩子方法，子类重写
    done();
    // 置空任务
    callable = null;    // to reduce footprint
}
~~~

整个run()方法总结下来：

（1）FutureTask中有个状态state控制任务的运行过程，正常运行结束state从NEW->COMPLETING>NORMAL，异常运行结束state从NEW->COMPLETING->EXCEPTIONAL;

（2）FutureTask保存了运行任务的线程runner，它是线程池周静的某个线程。

（3）调用者线程是保存在waiters队列中，它是什么时候设置进去的呢？

（4）任务执行完毕，除了设置状态state变化之外，还要唤醒调用者线程。

调用者线程是什么时候保存在FutureTask中（waiters）中的呢

~~~java
public FutureTask(Callable<V> callable){
    if(callable == null)   
        throw new NullPointerException();
    this.callcable = callable;
    this.state = NEW;   // ensure visibility callable
}
~~~

发现并没有相关信息，我们再试想一下，如果调用者不调用get()方法，那么这种未来任务是不是跟普通任务没有什么区别？确实是的哈，所以只有调用get()方法了才有必要保存调用者线程到FutureTask中。

所以，我们来看看get()方法中是什么鬼。

### FutureTask类的get()方法
---

get()方法调用时如果任务未执行完毕，会阻塞直到任务结束。

~~~java
public V get()throws InterruptedException, ExecutionException{
    int s = state;
    // 如果状态小于等于COMPLETING，则进入等待
    if(s <= COMPLETING)
        s = awaitDone(false,0L);
    // 返回结果（异常）
    return report(s);
}
~~~

是不是很清楚，如果任务状态小于等于COMPLETING，则进入队列等待。s

~~~java
public V get(boolean timed,long nanos)throws InterruptedException{
    // 我们这里假设不带超时
    final long deadline = timed ? System.nanoTime() + nanos : 0L;
    
    WaitNode q = null;
    boolean queued = false;
    
    for(;;){
        // 处理中断
        if(Thread.interrupted()){
            removeWaiter(q);
            throw new InterruptedException();
        }
        // 4. 如果状态大于COMPLETING了，则跳出循环并返回
        // 这是自旋的出口
        int s = state;
        if(s > COMPLETING){
            if(q != null)  
                q.thread = null;
            return s;
        }

        // 如果状态等于COMPLETING，说明任务就快完成了，就差设置状态到NORMAL或EXCEPTIONAL和 设置结果了
        else if (s == COMPLETING)   // cannot time out yet
            Thread.yield();
        else if(q == null)
            // 初始化队列（WaitNode中记录了调用者线程）
            q = new WaitNode();
        // 2. 未进入队列
        else if(!queued)
            // 尝试入队
            queued = UNSAFE.compareAndSwapObject(this,waitersOffset,q.next = waiters,q);
        // 3. 超时处理
        else if(timed){
            nanos = deadline - System.nanoTime();
            if(nanos <= 0L){
                removeWaiter(q);
                return state;
            }
            LockSupport.parkNanos(this,nanos);
        }else
            LockSupport.park(this);
    }
}
~~~

这里我们假设调用get()时任务还未执行，也就是状态为NEW，我们试着按上面的表示1,2,3,4走一遍逻辑：

（1）第一次循环，状态为NEW，直接到1处，初始化队列并把调用者线程封装到WaitNode中

（2）第二次循环，状态为NEW，直接到2处，让包含调用者线程的WaitNode入队。

（3）第三次循环，状态为NEW，队列不为空，且已入队，到3处，阻塞调用者线程。

（4）第四处循环，状态肯定大于COMPLETING，退出循环并返回。

问题：**为什么要在for循环中控制整个流程，把这里的每一步单独拿出来行不行？**

答：因为每一次动作都需要重新检查状态state有没有变化，如果拿出写也是可以的，只是代码会非常冗长。这里只分析了get()时状态为NEW，其它的状态也可以自行验证，都是可以确保正确的，甚至两个线程交叉运行。

OK，这里返回之后，在看看是怎么处理最终的结果的。

~~~java
private V report(int s) throw ExecutionException{
    Object x = outcome;
    // 任务正常结束
    if(s == NORMAL)
        return (V)x;
    // 被取消了
    if(s >= CANCELLED)
        throw new CancellationException();
    // 执行异常
    throw new ExecutionException((Throwable)x);        
}
~~~

还记得前面分析run的时候吗，任务执行异常时是把异常放在outcome里面的，这里就用到了。

（1）如果正常执行结束，则返回任务的返回值；

（2）如果异常结束，则包装成ExecutionException异常抛出；

通过这种方式，线程中出现的异常也可以返回给调用者线程了，不会像执行普通任务那样调用者是不知道任务执行到底有没有成功的。

### 其它
---

FutureTask除了可以获取任务返回值以外，还能够取消任务的执行。

~~~java
public boolean cancel(boolean mayInterruptIfRunning) {
    if (!(state == NEW &&
          UNSAFE.compareAndSwapInt(this, stateOffset, NEW,
              mayInterruptIfRunning ? INTERRUPTING : CANCELLED)))
        return false;
    try {    // in case call to interrupt throws exception
        if (mayInterruptIfRunning) {
            try {
                Thread t = runner;
                if (t != null)
                    t.interrupt();
            } finally { // final state
                UNSAFE.putOrderedInt(this, stateOffset, INTERRUPTED);
            }
        }
    } finally {
        finishCompletion();
    }
    return true;
}
~~~

这里取消任务是通过中断执行线程来处理的

## 总结

（1）未来任务是通过把普通任务包装成FutureTask来实现的

（2）通过FutureTask不仅能获取任务执行的结果，还能感知到任务执行的异常，设置还可以取消任务。

（3）AbstractExecutorService中定义了很多模板方法，这是一种很重要的设计模式。

（4）FutureTask其实就是典型的异步调用的实现方式。

## 彩蛋

**RPC框架中异步调用是怎么实现的？**

答：RPC框架常用的调用方式有同步调用，异步调用，其实它们本质上都是异步调用，它们就是用FutureTask的方式来实现的。

一般的，通过一个线程（我们叫做远程线程）去调用远程接口，如果是同步调用，则直接让调用者线程阻塞等待远程线程调用的结果，待结果返回再返回，如果是异步调用，则先返回一个未来可以获取到远程结果的东西FutureXxx，如果这个Future
在远程结果返回之前调用get()方法一样会阻塞着调用者线程。