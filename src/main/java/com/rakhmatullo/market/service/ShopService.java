package com.rakhmatullo.market.service;


import com.rakhmatullo.market.dao.Shop;
import com.rakhmatullo.market.exception.ShopNotFoundException;
import com.rakhmatullo.market.repository.ShopRepository;
import com.rakhmatullo.market.response.ShopResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;

    public List<ShopResponse> findAllShops() {
        List<Shop> shops = shopRepository.findAll();
        return getShopResponses(shops);
    }

    public ShopResponse addNewShop(Shop shop) {
        shopRepository.save(shop);
        return ShopResponse.builder()
                .id(shop.getId())
                .shopName(shop.getShopName())
                .address(shop.getAddress())
                .parentId(shop.getParentId())
                .typeId(shop.getTypeId()).
                build();

    }

    ;

    public List<ShopResponse> getShopResponses(List<Shop> shops) {
        List<ShopResponse> shopResponses = new ArrayList<>();
        for (Shop shop : shops) {
            ShopResponse shopResponse = ShopResponse.builder().shopName(shop.getShopName()).address(shop.getAddress()).parentId(shop.getParentId()).typeId(shop.getTypeId()).id(shop.getId()).build();
            shopResponses.add(shopResponse);
        }
        return shopResponses;
    }

    public Shop updateById(int id) throws ShopNotFoundException{
        Optional<Shop> shop = shopRepository.findById(id);
        if(shop.isPresent()){
            shopRepository.save(shop.get());
            return shop.get();
        } else {
            throw new ShopNotFoundException(String.format("Shop %s is Not Found", id));
        }
    }

    public Shop getById(int id) throws ShopNotFoundException {
        Optional<Shop> shop = shopRepository.findById(id);
        if(shop.isPresent()){
            return shop.get();
        } else {
            throw new ShopNotFoundException(String.format("Shop %s is Not Found", id));
        }
    }

    public String deleteItem(int id) throws ShopNotFoundException {
        Optional<Shop> shop = shopRepository.findById(id);
        if(shop.isPresent()){
            shopRepository.delete(shop.get());
            return "Deleted successfully";
        } else {
            throw new ShopNotFoundException(String.format("Shop %s is Not Found", id));
        }

    }


}
