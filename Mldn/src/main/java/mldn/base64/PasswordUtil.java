package mldn.base64;

import java.util.Base64;

/**
 * @Auther lovely
 * @Create 2020-03-24 20:12
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 定义自己一个密码的加密处理类
 */
public class PasswordUtil {
    private static final int REPEAT = 5;//重复5次加密
    private static final String SALT = "0.0";//追加操作盐值
    private PasswordUtil(){}

    public static String encrypt(String string) {
        String encoderData = "{" + SALT + "}" + string; //处理要加密的数据
        Base64.Encoder encoder = Base64.getEncoder();
        for (int i = 0; i < REPEAT; i++) {
            encoderData = encoder.encodeToString(encoderData.getBytes());//加密操作
        }
        return encoderData;
    }

    public static String decrypt(String str){
        Base64.Decoder decoder = Base64.getDecoder();
        byte data[] = str.getBytes();
        for (int i = 0; i < REPEAT; i++) {
            data = decoder.decode(data);    //多次解密
        }
        String decoderData = new String(data);
        return decoderData.substring(("{" + SALT + "}").length());
    }

}
