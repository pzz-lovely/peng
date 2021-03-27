package mldn.optional;

import java.util.Optional;

/**
 * @Auther lovely
 * @Create 2020-03-24 20:42
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class Factory {
    static Optional<IMessage> create(){
        return Optional.of(message->{
            System.out.println("消息发送"+message);
            return message;
        });
    }
}
