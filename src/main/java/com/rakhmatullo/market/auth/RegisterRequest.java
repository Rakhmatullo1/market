package com.rakhmatullo.market.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class RegisterRequest {
    private String username;
    private String password;
    private String fullName;
    private int parentId;
    private int status;
}
