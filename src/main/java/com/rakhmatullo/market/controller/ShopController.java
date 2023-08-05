package com.rakhmatullo.market.controller;

import com.rakhmatullo.market.dao.Shop;
import com.rakhmatullo.market.exception.ShopNotFoundException;
import com.rakhmatullo.market.response.ShopResponse;
import com.rakhmatullo.market.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/market/api/v1/shops")
@RequiredArgsConstructor
public class ShopController {
    private final  ShopService shopService;

    @GetMapping
    public List<ShopResponse> getAllShops() {
        return shopService.findAllShops();
    }

    @GetMapping("/{id}")
    public Shop getShopById(@PathVariable int id) throws ShopNotFoundException {
        return shopService.getById(id);
    }

    @PostMapping
    public ShopResponse addNewShop(@RequestBody  Shop shop) {
        return shopService.addNewShop(shop);
    }

    @PutMapping("/{id}")
    public Shop updateShop(@PathVariable int id) throws ShopNotFoundException {
        return shopService.updateById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteShopById(@PathVariable int id) throws ShopNotFoundException {
        return shopService.deleteItem(id);
    }

}
