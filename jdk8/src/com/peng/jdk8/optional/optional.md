Optional类是一个可以为 null 的容器对象。如果值存在则 isPresent()方法会返回 true，调用get()方法会返回该对象。
Optional是个容器，它可以保存类型T的值，或者仅仅保存null,Optional提供很多有用的方法，这样就不用显示进行空值检测。
Optional类的引入很好的解决控指针异常。

**类方法**

| 方法                                                         | 描述                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| static \<T> Optional\<T> empty()                             | 返回空的 Optional 实例                                       |
| boolean equals(Object obj)                                   | 判断其它对象是否等于Optional                                 |
| Optional \<T> filter(Predicate<? super T> predicate)         | 如果值存在，并且这个值匹配给定的predicate，返回一个Optional用以描述这个值，否则返回空的Optinal |
| \<U> Optional\<U> flatMap(Function<? super T,Optional\<U>> mapper) | 如果值存在，返回基于Optional包含的映射方法的值，否则返回一个空的 Optional |
| T get()                                                      | 如果在这个Optional中包含这个值，返回值，否则抛出异常：NoSuchElementException |
| void ifPresent(Consumer\<? super T> consumer)                | 如果值存在则使用该值调用 consumer，否则不做任何事情          |
| boolean isPresent()                                          | 如果值存在会返回true，否则返回false、                        |
| \<U>Optional\<U> map(Function<? super T,? extends U> mapper) | 如果有值，则对其执行调用映射函数得到返回值。如果返回值不为null，则创建包含映射返回值的Optional作为map方法返回值，否则返回空的Optional |
| static \<T> Optional\<T> of(T value)                         | 返回一个指定非null值的Optional                               |
| static \<T> Optional\<T> ofNullable(T value)                 | 如果为非空，返回Optional描述的指定值，否则返回空的 Optional  |
| T orElse(T other)                                            | 如果存在该值，返回值，否则返回other                          |
| T orElseGet(Supplier<? extends T> other)                     | 如果存在该值，返回值， 否则触发 other，并返回 other 调用的结果。 |
| \<X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) | 如果存在该值，返回包含的值，否则抛出由 Supplier 继承的异常   |



