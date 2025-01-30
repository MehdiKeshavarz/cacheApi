package com.example.cacheApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CacheData implements Serializable {
    private String key;
    private String value;
    private int timeToLiveInMinute;
}
