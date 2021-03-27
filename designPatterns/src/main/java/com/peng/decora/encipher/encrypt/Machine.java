package com.peng.decora.encipher.encrypt;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @Author lovely
 * @Create 2020-11-27 9:54
 * @Description
 */
@Slf4j
public class Machine implements Encryption, Decryption {
    private static final int REPEAT = 5;//重复5次加密


    private Filter encryptFilter;

    private Filter decryptFilter;

    private Filter currentFilter;

    public Machine() {
        encryptFilter = new EncryptFilter();
        decryptFilter = new DecryptFilter();
    }


    @Override
    public String encrypt(String str) throws UnsupportedEncodingException {
        currentFilter = encryptFilter;
        String encoderData = filter(str);
        for (int i = 0; i < REPEAT; i++) {
            encoderData = encoder.encodeToString(encoderData.getBytes(StandardCharsets.UTF_8));
        }
        log.info("加密后的值:{}",encoderData);
        return encoderData;
    }

    @Override
    public String decrypt(String str) {
        currentFilter = decryptFilter;
        byte[] data = str.getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i < REPEAT; i++) {
            data = decoder.decode(data);
        }
        String decoderData = new String(data);
        return filter(decoderData);
    }

    @Override
    public String filter(String str) {
        return currentFilter.filter(str);
    }

}