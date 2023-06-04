package com.rakhmatullo.market.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class GoodResponse {
    private int id;
    private String name;
    private String article;
    private String barcode;
    private String imageUrl;
}
