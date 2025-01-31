package com.example.cacheApi.controller;

import com.example.cacheApi.request.CacheRequest;
import com.example.cacheApi.response.ApiResponse;
import com.example.cacheApi.services.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/caches")
public class CacheController {

    private final CacheService cacheService;

    @Autowired
    public CacheController(CacheService cacheService) {
        this.cacheService = cacheService;
    }


    @PostMapping()
    public ResponseEntity<ApiResponse> create(@RequestBody CacheRequest request) {
        cacheService.create(request.getKey(), request.getValue(), request.getTimeToLiveInMinutes());
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Success", "Data cached successfully", null));
    }


    @GetMapping("/{key}")
    public ResponseEntity<ApiResponse> read(@PathVariable String key) {
        String data = cacheService.read(key);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Success", null, data));

    }

    @DeleteMapping("/{key}")
    public ResponseEntity<ApiResponse> delete(@PathVariable String key) {
        cacheService.delete(key);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Success", "Delete cached successfully", null));
    }

}
