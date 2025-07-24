package com.example.Product.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.Product.jwt.SecurityUtil;
import com.example.Product.Entity.BasketEntity;
import com.example.Product.Entity.ProductsEntity;
import com.example.Product.Entity.UserEntity;
import com.example.Product.Repository.BasketRepository;
import com.example.Product.Repository.ProductsRepository;
import com.example.Product.Repository.UserRepository;
import com.example.Product.Response.BasketResponse;

import jakarta.transaction.Transactional;

@Service
public class BasketService {

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private UserRepository userRepository;

    public void addToBasket(Long productId) {
        String username = SecurityUtil.getCurrentUsername();

        UserEntity user = userRepository.findByUsername(username)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Optional<BasketEntity> existingItem = basketRepository.findByUserIdAndProductsId(user.getId(), productId);

        if (existingItem.isPresent()) {
            BasketEntity basket = existingItem.get();
            basket.setQuantity(basket.getQuantity() + 1);
            basketRepository.save(basket);
        } else {
            BasketEntity basket = new BasketEntity();
            basket.setUserId(user.getId());
            basket.setProductsId(productId);
            basket.setQuantity(1);
            basketRepository.save(basket);
        }
    }

    @Transactional
    public void removeFromBasket(Long productId) {
        String username = SecurityUtil.getCurrentUsername();

        UserEntity user = userRepository.findByUsername(username)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        BasketEntity basketItem = basketRepository.findByUserIdAndProductsId(user.getId(), productId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found in basket"));

        basketRepository.delete(basketItem);
    }

    public List<BasketResponse> getMyBasket() {
        String username = SecurityUtil.getCurrentUsername();

        UserEntity user = userRepository.findByUsername(username)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        List<BasketEntity> baskets = basketRepository.findByUserId(user.getId());

        return baskets.stream().map(basket -> {
            ProductsEntity product = productsRepository.findById(basket.getProductsId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

            BasketResponse response = new BasketResponse();
            response.setId(product.getId());
            response.setBrand(product.getBrand());
            response.setCategory(product.getCategory());
            response.setDescription(product.getDescription());
            response.setPrice(product.getPrice());
            response.setRating(product.getRating());
            response.setImageUrl(product.getImageUrl());
            response.setOwnerId(product.getOwnerId());
            response.setModel(product.getModel());
            response.setQuatity(basket.getQuantity());
          
            return response;
        }).collect(Collectors.toList());
    }
}