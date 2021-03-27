package com.peng.http.cache;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CacheResponse;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther lovely
 * @Create 2020-08-24 16:44
 * @Description todo
 */
public class SimpleCacheResponse extends CacheResponse {

    private final Map<String,List<String>> headers;
    private final SimpleCacheRequest request;
    private final Date expires;
    private final CacheControl cacheControl;

    public SimpleCacheResponse(Map<String, List<String>> headers, SimpleCacheRequest request, Date expires, CacheControl cacheControl) {
        this.headers = headers;
        this.request = request;
        this.expires = expires;
        this.cacheControl = cacheControl;
    }


    @Override
    public Map<String, List<String>> getHeaders() throws IOException {
        return headers;
    }


    public CacheControl getCacheControl(){
        return cacheControl;
    }
    @Override
    public InputStream getBody() throws IOException {
        return new ByteArrayInputStream(request.getData());
    }

    public boolean isExpired(){
        Date now = new Date();
        if(cacheControl.getMaxAge().before(now)) return true;
        else if(expires != null && cacheControl.getMaxAge() != null)
            return expires.before(now);
        else
            return false;
    }
}
