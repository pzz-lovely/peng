# Future和Callable

### Runable的缺陷

- 子线程不能返回一个返回值。
- 也不能 抛出 checked Exception 异常 

### Callable接口

类似于Runnable，被其他线程执行的任务

实现call方法

有返回值

~~~java
public interface Callable<V>{
    V call()throw Exception;
}
~~~

### Future类

#### Future的作用

异步获取。核心思想，一个任务他在运行的过程中可能会很耗时，在这个运行的过程中，没有必要一直等待任务执行结束，用子线程去执行，子线程去执行的过程，我可干别的事情。直到我想要得到结果

#### Callable和Future的关系

- 可以使用 <b style="color:red">Future.get()</b> 来获取Callable接口返回的执行结果，还可以通过 <b style="color:red">Future.isDone()</b>来判断任务是否已经执行完了，已经取消这个任务，限时获取任务的结果等
- 在call()方法未执行完毕之前，调用Future的get()的线程(假定是主线程)会被 <b style="color:red">阻塞</b>，直到call()方法返回了结果后，此时future.get()才会得到该结果，然后主线程才会切换到 Runnable状态
- 所有Future是一个<b style="color:red">存储器，它存储了call()这个任务的结果</b>，而这个任务的执行时间是无法提前确定的，因为这完全取决于call()方法执行的情况

#### Future方法

~~~java
V get();
V get(long timeout,TimeUnit unit);
/*
	get方法的行为取决于Callable任务的状态，只有一下这5中情况：
		1.任务正常完成：get方法会立刻返回结果
		2.任务尚未完成（任务还没开始或进行中）：get将阻塞并直到任务完成。
		3.任务执行过程中抛出Exception：get方法会抛出ExceptionException：这里的抛出异常，是call()执行时产生的那个异常。java.util.concurrent.ExecutionException
		4.任务被取消：get方法会抛出CancellationException
		5.任务超时：get方法有一个重载方法，是传入一个延迟时间的，如果时间到了还没有获的结果，get方法就会抛出TimeoutException
*/
void cancel();
/*
	取消任务的执行 
		如果这个任务还没开始执行，那么这种情况最简单，任务会被正常的取消，未来为不会执行，方法返回true。无论你传入true还是false都会取消
		如果任务已完成，或者已取消：那么cancel()方法会执行失，方法返回false.无论传入的是true还是fasle都是已取消的
		如果这个任务已经开始执行了，那么这个取消方法将不会直接取消该任务，而是会根据我们传入的参数 ture 还是 false
	Future.cancel(true)适用于：
		1.任务能够处理interrupt
	Future.cancel(false) 仅用于避免启动尚未启动的任务，适用于：
		未能处理Interrupt的任务
		不清楚任务是否支持取消
		需要等待已经开始的任务执行完成
*/

boolean isDone();
/*
	判断线程是否执行完毕
*/

boolean isCancelled();
//判断任务是否被取消了
~~~

![](D:%5Ctask%5Cpeng_imooc%5Cjuc%5Csrc%5Cmain%5Cresources%5Cimages%5CFutureTask.png)

### Future注意点

Future的声明周期不能后退的

​	声明周期只能前进，不能后退。就和线程池的声明中予以一样，一旦完成了任务，他就永久停在了“已完成”的状态，不能重头再来。