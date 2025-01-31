package com.example.cacheApi.services;
import com.example.cacheApi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CacheService {
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public CacheService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void create(String key, String value, int timeToLiveInMinutes) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key and Value are required");
        }

        redisTemplate.opsForValue().set(
                key,
                value,
                timeToLiveInMinutes,
                TimeUnit.MINUTES);
    }

    public String read(String key) {
        String value = (String) redisTemplate.opsForValue().get(key);

        if (value == null) {
            throw new ResourceNotFoundException("No data found in the cache for key " + key + "!");
        }

        return value;
    }

    public void delete(String key) {
        Boolean deleted = redisTemplate.delete(key);
        if (!deleted) {
            throw new ResourceNotFoundException("No data found in the cache for key " + key + "!");
        }

    }
}
