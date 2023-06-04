package com.rakhmatullo.market.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class RestError {
    private String message;
    private int statusCode;
    private long timestamp;
}
