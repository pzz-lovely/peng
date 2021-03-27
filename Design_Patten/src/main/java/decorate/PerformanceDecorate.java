package decorate;

import java.util.logging.Logger;

/**
 * @Auther lovely
 * @Create 2020-02-18 20:24
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class PerformanceDecorate implements Command {
    private Command command;
    public PerformanceDecorate(Command command) {
        this.command = command;
    }

    @Override
    public void execute() {
        Logger logger = Logger.getLogger(PerformanceDecorate.class.getCanonicalName());
        logger.info("start");
        this.command.execute();
        logger.info("end");
    }
}
