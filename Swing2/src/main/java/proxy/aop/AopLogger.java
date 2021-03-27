package proxy.aop;

import java.util.logging.Logger;

/**
 * @Auther lovely
 * @Create 2020-03-16 21:27
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class AopLogger {
    private Logger logger = Logger.getLogger(AopLogger.class.getName());
    public void before(){
        logger.info("Logger before");
    }

    public void after(){
        logger.info("Logger after");
    }

}
