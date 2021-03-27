package com.peng.http.cache;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.CacheRequest;

/**
 * @Auther lovely
 * @Create 2020-08-24 16:41
 * @Description todo
 */
public class SimpleCacheRequest extends CacheRequest {


    private ByteArrayOutputStream out = new ByteArrayOutputStream();
    @Override
    public OutputStream getBody() throws IOException {
        return out;
    }

    @Override
    public void abort() {
        out.reset();
    }


    public byte[] getData(){
        if(out.size() == 0) return null ;
        else return out.toByteArray();
    }
}
