package com.demo.skyros.service;

import com.demo.skyros.exception.AppResponse;

public interface CachingService {

    AppResponse clearCache();

    AppResponse clearCacheByName(String cacheName);
}
