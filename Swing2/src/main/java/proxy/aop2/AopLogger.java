package proxy.aop2;

import lombok.extern.slf4j.Slf4j;

import java.util.logging.Logger;

/**
 * @Auther lovely
 * @Create 2020-03-17 7:43
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
@Slf4j
public class AopLogger {
    public void before(){
        System.out.println("before");
    }

    public void after(){
        System.out.println("after");
    }
}
