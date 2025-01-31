package com.example.cacheApi.request;

import lombok.*;

@Data
public class CacheRequest {
    private String key;
    private String value;
    private int timeToLiveInMinutes;
}
