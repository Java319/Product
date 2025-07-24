package com.example.Product.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.Product.jwt.SecurityUtil;

import com.example.Product.Entity.ProductsEntity;
import com.example.Product.Entity.UserEntity;
import com.example.Product.Repository.ProductsRepository;
import com.example.Product.Repository.UserRepository;
import com.example.Product.Request.ProductsRequest;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private UserRepository userRepository;

    public void addProduct(ProductsRequest request) {
        String username = SecurityUtil.getCurrentUsername();
        UserEntity user = userRepository.findByUsername(username)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        ProductsEntity product = new ProductsEntity();
product.setDescription(request.getDescription());
        product.setBrand(request.getBrand());
        product.setModel(request.getModel());
        product.setCategory(request.getCategory());
        product.setImageUrl(request.getImageUrl());
        product.setPrice(request.getPrice());
        product.setRating(request.getRating());
        product.setOwnerId(user.getId()); 

        productsRepository.save(product);
    }

    public List<ProductsEntity> getUserProducts() {
        String username = SecurityUtil.getCurrentUsername();
        UserEntity user = userRepository.findByUsername(username)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        return productsRepository.findByOwnerId(user.getId());
    }

    public ProductsEntity getProductById(Long id) {
        return productsRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }
   

    public void deleteProduct(Long id) {
        ProductsEntity product = getProductById(id);
        productsRepository.delete(product);
    }

    public List<ProductsEntity> getAllProducts() {
        return productsRepository.findAll();
    }

    public List<ProductsEntity> searchProducts(String keyword) {
        return productsRepository.findByBrandContainingIgnoreCase(keyword);
    }

    public List<ProductsEntity> sortByMinRating(Double minRating) {
        return productsRepository.findAllByRatingGreaterThanEqual(minRating);
    }

    public List<ProductsEntity> sortByPriceAsc() {
        return productsRepository.findAllByOrderByPriceAsc();
    }

    public List<ProductsEntity> sortByPriceDesc() {
        return productsRepository.findAllByOrderByPriceDesc();
    }

    public void updateProduct(Long id, ProductsEntity updatedProduct) {
        ProductsEntity existing = getProductById(id);
        String username = SecurityUtil.getCurrentUsername();
        UserEntity user = userRepository.findByUsername(username)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        existing.setBrand(updatedProduct.getBrand());
        existing.setModel(updatedProduct.getModel());
        existing.setCategory(updatedProduct.getCategory());
        existing.setImageUrl(updatedProduct.getImageUrl());
        existing.setPrice(updatedProduct.getPrice());
        existing.setRating(updatedProduct.getRating());
        existing.setOwnerId(user.getId());
        existing.setDescription(updatedProduct.getDescription());

        productsRepository.save(existing);
    }
    
    
    @PreAuthorize("hasRole('ROLE_ADMIN')") 
    public void deleteAllProducts() {
        productsRepository.deleteAll();
    }
}