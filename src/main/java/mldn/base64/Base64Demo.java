package mldn.base64;

import java.util.Base64;

/**
 * @Auther lovely
 * @Create 2020-03-24 20:06
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class Base64Demo {
    public static void main(String[] args) {
        String message = "www.baidu.com";
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodeData = encoder.encode(message.getBytes());
        System.out.println("加密后的数据内容" + new String(encodeData));
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(encodeData);
        System.out.println("解密后的数据内容"+new String(decode));

        String encrypt = PasswordUtil.encrypt("peng.com");
        System.out.println(PasswordUtil.decrypt(encrypt));
    }
}
