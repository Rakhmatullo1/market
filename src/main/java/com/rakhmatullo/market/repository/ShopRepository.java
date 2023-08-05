package com.rakhmatullo.market.repository;

import com.rakhmatullo.market.dao.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  ShopRepository extends JpaRepository<Shop, Integer> {
}
