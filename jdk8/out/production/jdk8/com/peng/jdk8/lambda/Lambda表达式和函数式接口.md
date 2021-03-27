Lamdba 表达式将函数当成参数传递给某个方法，或者把代码本身当作数据处理；

** 语法格式：**

* 用逗号分隔的参数列表
* -> 符号
* 和`语句块`组成

~~~java
Arrays.asList(1, 2, 3).forEach(e -> System.out.println( e ) );
~~~

等价于

~~~java
List<String> list = Arrays.asList(1, 2, 3);
for(String e:list){
    System.out.println(e);
}
~~~

对与执行的代码为多行，使用 `{}` 包起来

~~~java
Arrays.asList(1, 2, 3).forEach(e -> {
    String m = "9527-"+e;
    System.out.print(m);
});
~~~

Lambda 的返回值和参数类型由编译器推理得出，不需要显示定义，如果只有一行代码可以不写 return 语句

~~~java
Arrays.asList(3, 5, 1, 4).sort((e1, e2 ) -> e1.compareTo(e2));
~~~

等价于

~~~java
List<String> list = Arrays.asList(3, 5, 1, 4);
Collections.sort(list, new Comparator<String>() {
    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
});
~~~

** 函数式接口 **

* 接口中只能有一个接口方法
* 可以有静态方法和默认方法
* 使用`@FunctionalInterface`标记
* 默认方法可以被覆写

~~~java
@FunctionalInterface
public interface FunctionalInterfaceDemo {
    int add(int num1, int num2);
    
    default int defaultAdd(int num1, int num2) {
        return this.add(num1,num2);
    }
    
    static int defaultAdd(int num1, int num2) {
        return this.add(num1,num2);
    }
}
~~~

Java8中提供了大量的默认函数ship接口，在rt.jar包中的 java.util.function 目录下可以看到所有默认的函数式接口，大致分为几类：

* Function<T,R> T作为输入，返回的 R 作为输出
* Predicate<T> T作为输入，返回boolean的输出
* Consumer<T> T作为输入，没有输入
* Supplier<R> 没有输入，R作为输出
* BinaryOperator<T> 两个 T 作为输入，T同样是输出
* UnaryOperator<T> 是Function的变种，输入输入者是T

示例：

~~~java

~~~

JVM在默认方法的实现中对字节码层面提供了支持。