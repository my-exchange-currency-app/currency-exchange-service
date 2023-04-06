package com.demo.skyros.service;

import com.demo.skyros.exception.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service
public class CachingServiceImpl implements CachingService {

    @Autowired
    private CacheManager cacheManager;

    @Override
    public AppResponse clearCache() {
        Collection<String> cacheNames = cacheManager.getCacheNames();
        cacheNames.forEach(cacheName -> {
            getCacheManager().getCache(cacheName).clear();
        });

        return prepareAppResponse(null, "the cache cleared successfully ");
    }


    @Override
    public AppResponse clearCacheByName(String cacheName) {
        getCacheManager().getCache(cacheName).clear();
        return prepareAppResponse(null, "the cache cleared successfully ");
    }

    private AppResponse prepareAppResponse(Object data, String message) {
        AppResponse appResponse = new AppResponse(message);
        appResponse.setData(data);
        appResponse.setResponseDate(new Date());
        appResponse.setHttpStatus(HttpStatus.OK);
        return appResponse;
    }

    public CacheManager getCacheManager() {
        return cacheManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
}
