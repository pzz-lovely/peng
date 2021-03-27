package runtimeAnnotations.ioc;

import java.nio.channels.Channel;

/**
 * @Auther lovely
 * @Create 2020-03-16 9:58
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
@Bean
public class InternetChannel extends HttpChannel implements IChannel{
    @Override
    public boolean build() {
        System.out.println("InternetChannel 已经 构建好了");
        return true;
    }

    @Override
    public void close() throws Exception {
        System.out.println("InternetChannel 关闭");
    }
}
