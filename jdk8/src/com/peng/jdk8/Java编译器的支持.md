Java 8 开始正式支持参数名称，终于不需要读 class 字节码来获取参数名称了，这对于经常使用反射的人特别有用。

在 Java8 这个特性默认是关闭的，需要开启参数才能获取参数名称：

~~~xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.1</version>
    <configuration>
        <compilerArgument>-parameters</compilerArgument>
        <source>1.8</source>
        <target>1.8</target>
    </configuration>
</plugin>
~~~