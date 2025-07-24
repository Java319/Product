package com.example.Product.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Product.Entity.BasketEntity;

@Repository
public interface BasketRepository extends JpaRepository<BasketEntity, Long> {
    List<BasketEntity> findByUserId(Long userId);
    
   
    Optional<BasketEntity> findByUserIdAndProductsId(Long userId, Long productsId);
    void deleteByUserIdAndProductsId(Long userId, Long productsId);
}