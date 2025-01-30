package com.example.cacheApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.UnifiedJedis;

@SpringBootApplication
public class CacheApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CacheApiApplication.class, args);

	}

}
