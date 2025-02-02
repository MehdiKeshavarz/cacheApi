package com.example.cacheApi.services;

import com.example.cacheApi.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CacheServiceTest {

    @Mock
    RedisTemplate<String, Object> redisTemplate;
    @Mock
    private ValueOperations<String, Object> valueOperations;


    @InjectMocks
    CacheService cacheService;


    @BeforeEach
    void setUp() {
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
    }

    @Test
    void givenValidKeyAndValue_whenCreate_thenShouldStoreInRedis(){
        String key = "testKey";
        String value = "testValue";
        int ttl = 10;

        cacheService.create(key, value, ttl);

        verify(valueOperations).set(key, value,ttl, TimeUnit.MINUTES);
    }

    @Test
    void givenNullKey_whenCreate_thenShouldThrowIllegalArgumentException(){
        String key = null;
        String value = "testValue";
        int ttl = 10;

        Assertions.assertThrows(IllegalArgumentException.class, () -> cacheService.create(key, value, ttl));
    }

    @Test
    void givenExistingKey_whenRead_thenShouldReturnValue(){
        String key = "testKey";
        String expectedValue = "testValue";

        when(valueOperations.get(key)).thenReturn(expectedValue);

        String actualValue = cacheService.read(key);

        Assertions.assertEquals(expectedValue, actualValue);

    }

    @Test
    void givenNonExistingKey_whenRead_thenShouldThrowResourceNotFoundException(){
        String unknownKey = "unknownKey";

        when(valueOperations.get(unknownKey)).thenReturn(null);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> cacheService.read(unknownKey));
    }

    @Test
    void givenExistingKey_whenDelete_thenShouldRemoveFromCache(){
        String key = "testKey";

        when(redisTemplate.delete(key)).thenReturn(true);

        cacheService.delete(key);

        verify(redisTemplate).delete(key);
    }


    @Test
    void givenNonExistingKey_whenDelete_thenShouldThrowResourceNotFoundException() {
        String key = "unknownKey";
        when(redisTemplate.delete(key)).thenReturn(false);


        Assertions.assertThrows(ResourceNotFoundException.class, () -> cacheService.delete(key));
    }

}