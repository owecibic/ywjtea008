package com.msb.business.controller;

import com.msb.framework.redis.annotation.CacheExpire;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.msb.framework.redis.consts.Time.FOREVER;
import static com.msb.framework.redis.consts.Time.SECOND;


@RestController
@RequestMapping("/cache")
@Slf4j

public class CacheController {

    @CacheExpire(FOREVER)
    @Cacheable("my_cache")
    @GetMapping("cache")
    @Transactional(rollbackFor = Exception.class)
    public String getCache(String arg) {
        return arg;
    }

    @GetMapping("cache1")
    @CacheExpire(2 * SECOND)
    @Cacheable("my_cache")
    public String getCache1(String arg1, String arg2) {
        return arg1 + arg2;
    }


    @CacheEvict("my_cache")
    @GetMapping("cache2")
    @Transactional(rollbackFor = Exception.class)
    public String update(String arg) {
        return arg;
    }
}
