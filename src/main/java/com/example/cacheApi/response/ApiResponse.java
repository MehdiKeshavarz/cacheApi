package com.example.cacheApi.response;


import lombok.Data;

@Data
public class ApiResponse {
    public Boolean status;
    public String message;
    public Object data;
}
