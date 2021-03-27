package com.peng.http.cache;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther lovely
 * @Create 2020-08-24 16:51
 * @Description todo
 */
public class MemoryCache extends ResponseCache {

    private final Map<URI, SimpleCacheResponse> responses = new ConcurrentHashMap<>();

    private final int maxEntries;

    public MemoryCache(){
        this(1000);
    }

    public MemoryCache(int maxEntries) {
        this.maxEntries = maxEntries;
    }

    @Override
    public CacheResponse get(URI uri, String rqstMetho, Map<String, List<String>> headers) throws IOException {
        return null;
    }

    @Override
    public CacheRequest put(URI uri, URLConnection conn) throws IOException {
        if(responses.size() >= maxEntries) return null;

        CacheControl cacheControl = new CacheControl(conn.getHeaderField("Cache-Control"));

        if (cacheControl.isNoStore()) {
            return null;
        } else if (!conn.getHeaderField(0).startsWith("GET")) {
            // 只缓存GET
            return null;
        }
        SimpleCacheRequest request = new SimpleCacheRequest();
        return null;
    }
}
