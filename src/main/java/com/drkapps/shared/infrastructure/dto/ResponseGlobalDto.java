package com.drkapps.shared.infrastructure.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseGlobalDto {

    private Object data;
    private String message;
    private int status;
    private boolean success;


    public static ResponseGlobalDto success (Object data) {
        return ResponseGlobalDto.builder().data(data).success(true).status(200).message("").build();
    }

    public static ResponseGlobalDto error (String message, int status) {
        return ResponseGlobalDto.builder().data(null).success(false).status(status).message(message).build();
    }


}
