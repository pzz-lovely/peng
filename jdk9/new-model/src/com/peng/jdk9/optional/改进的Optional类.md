Optional类在 Java8 中引入，很好的解决空指针遗产。

jdk9中，添加了是哪方法来改进它的功能：

* stream()
* isPresentOrElse()
* or()

**stream()方法**

~~~
public Stream<T> stream()
~~~

stream方法的作用就是将 Optional 转为一个Stream，如果该 Optional 中包含值，那么就返回包含这个值的Stream，否则返回一个空的 Stream(Stream.empty())

**isPresentOrElse()方法**

~~~
public void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction)
~~~

ifPresentOrElse 方法的改进就是有了 else，接收两个参数 Consumer 和 Runnable

ifPresentOrElse 方法的用途是，如果一个 Optional 包含值，则对其包含的值调用函数 action，即 action.accept(value)，这与 ifPresent 一致；与 ifPresent 方法的区别在于，ifPresentOrElse 还有第二个参数 emptyAction —— 如果 Optional 不包含值，那么 ifPresentOrElse 便会调用 emptyAction，即 emptyAction.run()。

**or()方法**

~~~
public Optional<T> or(Supplier<? extends Optional<? extends T>> supplier)
~~~

如果值存在，返回 Optional 指定的值，否则返回一个预设的值
