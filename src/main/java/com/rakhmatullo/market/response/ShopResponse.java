package com.rakhmatullo.market.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ShopResponse {
    private int id;
    private int parentId;
    private int typeId;
    private String address;
    private String shopName;

}
