package one.chapter6.proxy.aop;

import java.util.logging.Logger;

/**
 * @Auther lovely
 * @Create 2020-02-19 20:32
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class AopLogger {
    private Logger logger = Logger.getLogger(AopLogger.class.getCanonicalName());
    public void before(){
        System.out.println("before");
        logger.info("before");
    }


    public void after(){
        System.out.println("after");
        logger.info("after");
    }
}
