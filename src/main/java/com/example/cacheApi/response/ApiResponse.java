package com.example.cacheApi.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ApiResponse {
    public String status;
    public String message;
    public Object data;


    public ApiResponse(String  status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;

    }

}
