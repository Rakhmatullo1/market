package com.rakhmatullo.market.repository;


import com.rakhmatullo.market.dao.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GoodRepository extends JpaRepository<Good, Integer> {

    @Query(value = "SELECT * FROM good where user_id=?", nativeQuery = true)
    List<Good> findByUserId(Integer id);

    @Query(value = "SELECT * FROM good where user_id=? AND id=?",nativeQuery = true)
    Optional<Good>  findByUserId(int userId, int goodId);

    @Query(value = "SELECT * FROM good where category_id=? AND user_id=? ", nativeQuery = true)
    List<Good> findByCategoryId(int categoryId, int userId);


}
