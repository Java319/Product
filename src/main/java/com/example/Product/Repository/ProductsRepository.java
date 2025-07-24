package com.example.Product.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Product.Entity.ProductsEntity;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsEntity, Long> {
    List<ProductsEntity> findByBrandContainingIgnoreCase(String brand);

    List<ProductsEntity> findByOwnerId(Long ownerId);

    List<ProductsEntity> findAllByOrderByPriceAsc();

    List<ProductsEntity> findAllByOrderByCategoryAsc();

    List<ProductsEntity> findAllByOrderByRatingDesc();

    List<ProductsEntity> findAllByRatingGreaterThanEqual(Double minRating);

    List<ProductsEntity> findAllByOrderByPriceDesc();
}