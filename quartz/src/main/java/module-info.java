module quartz_demo {
    exports com.peng.quartz.job;
    exports com.peng.quartz.cron;
    requires org.slf4j;
    requires quartz;
}