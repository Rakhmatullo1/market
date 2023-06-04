package com.rakhmatullo.market.controller;


import com.rakhmatullo.market.dao.Good;
import com.rakhmatullo.market.exception.GoodNotFoundException;
import com.rakhmatullo.market.response.GoodResponse;
import com.rakhmatullo.market.service.GoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/market/api/v1/goods")
@RequiredArgsConstructor
public class GoodController {

    private final GoodService service;

    @GetMapping
    public List<GoodResponse> findAll() {
        return service.findAllGoods();
    }

    @GetMapping("/{goodId}")
    public GoodResponse findById(@PathVariable Integer goodId) throws GoodNotFoundException {
        return service.findById(goodId);
    }

    @GetMapping("/categoryId={categoryId}")
    public List<GoodResponse> findByCategoryId(@PathVariable Integer categoryId) {
        return service.findByCategoryId(categoryId);
    }

    @PostMapping
    public GoodResponse addNewGood(@RequestBody Good good) {
        return service.addNewGood(good);
    }

    @PutMapping("/{id}")
    public GoodResponse updateGood(@PathVariable int id, @RequestBody Good good) throws GoodNotFoundException {
        return service.updateGood(id, good);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGood(@PathVariable int id) throws GoodNotFoundException{
        return  ResponseEntity.ok(service.deleteItem(id));
    }
}
