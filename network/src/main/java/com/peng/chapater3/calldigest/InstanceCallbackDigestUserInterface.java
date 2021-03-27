package com.peng.chapater3.calldigest;

import javax.xml.bind.DatatypeConverter;

/**
 * @Auther lovely
 * @Create 2020-04-30 8:26
 * @Description todo
 */
public class InstanceCallbackDigestUserInterface {
    private String filename;
    private byte[] digest;

    public InstanceCallbackDigestUserInterface(String filename) {
        this.filename = filename;
    }


    public void calculateDigest(){
        InstanceCallbackDigest cb = new InstanceCallbackDigest(filename, this);
        Thread thread = new Thread(cb);
        thread.start();
    }


    void receiveDigest(byte[] digest) {
        this.digest = digest;
        //可以把这一步看做是回调
        System.out.println(this);
    }

    @Override
    public String toString() {
        String result = filename + " : ";
        if (digest != null) {
            result += DatatypeConverter.printHexBinary(digest);
        }else{
            result += "digest not available";
        }
        return result;
    }

    public static void main(String[] args) {
        InstanceCallbackDigestUserInterface d = new InstanceCallbackDigestUserInterface("E:\\work\\Data\\Hibernate.md");
        d.calculateDigest();
    }
}
