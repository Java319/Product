package com.example.Product.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.Product.Entity.ProductsEntity;
import com.example.Product.Request.ProductsRequest;
import com.example.Product.Service.ProductsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @GetMapping("/get-all")
    public ResponseEntity<List<ProductsEntity>> getAllProducts() {
        return ResponseEntity.ok(productsService.getAllProducts());
    }

    @GetMapping("/my-products")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<ProductsEntity>> getMyProducts() {
        return ResponseEntity.ok(productsService.getUserProducts());
    }
    @GetMapping("/{id}")
    @PreAuthorize("permitAll()") 
    public ResponseEntity<ProductsEntity> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productsService.getProductById(id));
    }
    @GetMapping("/search")
    public ResponseEntity<List<ProductsEntity>> searchProducts(@RequestParam String keyword) {
        return ResponseEntity.ok(productsService.searchProducts(keyword));
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Map<String, String>> addProduct(@RequestBody @Valid ProductsRequest productsRequest) {
        if (productsRequest == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Product data must not be null"));
        }
        productsService.addProduct(productsRequest);
        return ResponseEntity.ok(Map.of("message", "Product added successfully"));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable Long id) {
        productsService.deleteProduct(id);
        return ResponseEntity.ok(Map.of("message", "Product deleted successfully"));
    }

    @GetMapping("/rating")
    public ResponseEntity<List<ProductsEntity>> byRating(@RequestParam Double minRating) {
        return ResponseEntity.ok(productsService.sortByMinRating(minRating));
    }

    @GetMapping("/sort-by-price-asc")
    public ResponseEntity<List<ProductsEntity>> byPriceAsc() {
        return ResponseEntity.ok(productsService.sortByPriceAsc());
    }

    @GetMapping("/sort-by-price-desc")
    public ResponseEntity<List<ProductsEntity>> byPriceDesc() {
        return ResponseEntity.ok(productsService.sortByPriceDesc());
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Map<String, String>> updateProduct(
            @PathVariable Long id,
            @RequestBody @Valid ProductsEntity productsEntity) {

        if (productsEntity == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Product data must not be null"));
        }

      
        productsEntity.setId(id);

        productsService.updateProduct(id, productsEntity);
        return ResponseEntity.ok(Map.of("message", "Product updated successfully"));
    }
    
    
    @DeleteMapping("/delete-all")
    @PreAuthorize("hasRole('ROlE_USER')") 
    public ResponseEntity<String> deleteAllProducts() {
        productsService.deleteAllProducts();
        return ResponseEntity.ok("Все продукты успешно удалены.");
    }
}