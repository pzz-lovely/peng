package runtimeAnnotations.ioc;

/**
 * @Auther lovely
 * @Create 2020-03-16 9:58
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
@Bean
public class Message {
/*    @Autowired
    private IChannel channel;*/

    @Resource("RadioChannel")
    private IChannel channel;

/*    @Autowired
    private InternetChannel internetChannel;*/

    public String echoChannel(String msg) {
        String echoMessage = "消息发送失败";
        if (channel.build()) {
            echoMessage = "发送的消息 : " + msg;
        }
        return echoMessage;
    }

}
