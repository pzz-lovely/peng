package runtimeAnnotations.ioc;

/**
 * @Auther lovely
 * @Create 2020-03-16 9:58
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
@Bean
public class RadioChannel implements IChannel {
    @Override
    public boolean build() {
        System.out.println("radioChannel 已经 构建好了");
        return true;
    }

    @Override
    public void close() throws Exception {
        System.out.println("radioChannel 关闭");
    }
}
