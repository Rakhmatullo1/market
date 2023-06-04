package com.rakhmatullo.market.service;


import com.rakhmatullo.market.dao.Good;
import com.rakhmatullo.market.exception.GoodNotFoundException;
import com.rakhmatullo.market.repository.GoodRepository;
import com.rakhmatullo.market.response.GoodResponse;
import com.rakhmatullo.market.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoodService  {
    private final GoodRepository goodRepository;
    public List<GoodResponse> findAllGoods() {
        Authentication  authentication = SecurityContextHolder.getContext().getAuthentication();
        User user  = (User) authentication.getPrincipal();
        List<Good> goods =  goodRepository.findByUserId(user.getId());
        return getGoodResponses(goods);
    }

    public GoodResponse findById(Integer id) throws GoodNotFoundException {
        Authentication  authentication = SecurityContextHolder.getContext().getAuthentication();
        User user  = (User) authentication.getPrincipal();
        var theGood = goodRepository.findByUserId(user.getId(), id);

        if(theGood.isPresent()) {
            Good good =theGood.get();
            return GoodResponse.builder()
                    .id(good.getId())
                    .imageUrl(good.getPhotoUrl())
                    .barcode(good.getBarcode())
                    .article(good.getArticle())
                    .name(good.getName())
                    .build();
        } else {
            throw new GoodNotFoundException(String.format("The Id %s Not Found", id));
        }
    }

    public List<GoodResponse> findByCategoryId(Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user= (User) authentication.getPrincipal();
        var goods = goodRepository.findByCategoryId(id, user.getId());
        return getGoodResponses(goods);
    }

    private List<GoodResponse> getGoodResponses(List<Good> goods) {
        List<GoodResponse> goodResponses =new ArrayList<>();
        for(Good good : goods){
            GoodResponse  response =  GoodResponse
                    .builder()
                    .id(good.getId())
                    .article(good.getArticle())
                    .barcode(good.getBarcode())
                    .name(good.getName())
                    .imageUrl(good.getPhotoUrl()).build();
            goodResponses.add(response);
        }
        return goodResponses;
    }

    @Transactional
    public GoodResponse updateGood(Integer id,Good good) throws GoodNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Optional<Good> theGood =goodRepository.findByUserId(user.getId(), id);
        if(theGood.isPresent()) {
            Good good1 = Good.builder()
                    .article(good.getArticle())
                    .name(good.getName())
                    .barcode(good.getBarcode())
                    .photoUrl(good.getPhotoUrl())
                    .whereId(good.getWhereId())
                    .categoryId(good.getCategoryId())
                    .userId(theGood.get().getUserId())
                    .pUserId(good.getPUserId())
                    .id(theGood.get().getId())
                    .build();
            goodRepository.save(good1);
            return GoodResponse.builder()
                    .id(good1.getId())
                    .imageUrl(good1.getPhotoUrl())
                    .article(good1.getArticle())
                    .name(good1.getName())
                    .barcode(good1.getBarcode())
                    .build();
        } {
            throw new GoodNotFoundException(String.format("The Id %s Not Found", id));
        }
    }

    @Transactional
    public GoodResponse addNewGood(Good good) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user= (User) authentication.getPrincipal();
        Good good1 = Good.builder()
                .userId(user.getId())
                .pUserId(user.getParentId())
                .categoryId(good.getCategoryId())
                .whereId(good.getWhereId())
                .photoUrl(good.getPhotoUrl())
                .name(good.getName())
                .barcode(good.getBarcode())
                .article(good.getArticle())
                .build();
        goodRepository.save(good1);
        return GoodResponse.builder()
                .name(good1.getName())
                .article(good.getArticle())
                .barcode(good.getBarcode())
                .article(good.getArticle())
                .imageUrl(good.getPhotoUrl())
                .id(good1.getId())
                .build();
    }

    @Transactional
    public String deleteItem(Integer id) throws GoodNotFoundException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Optional<Good> theGood =goodRepository.findByUserId(user.getId(), id);
        if(theGood.isPresent()) {
            Good good = theGood.get();
            goodRepository.delete(good);
            return String.format("Successfully Deleted %s ", id);
        } else {
            throw new GoodNotFoundException(String.format("The Id %s Not Found", id));
        }

    }
}
