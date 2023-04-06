package com.demo.skyros.controller;

import com.demo.skyros.exception.AppResponse;
import com.demo.skyros.service.CachingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private CachingService cachingService;

    @GetMapping("clearCache")
    public AppResponse clearCache() {
        return cachingService.clearCache();
    }

    @GetMapping("clearCacheByName/{cacheName}")
    public AppResponse clearCacheByName(@PathVariable String cacheName) {
        return cachingService.clearCacheByName(cacheName);
    }

}
