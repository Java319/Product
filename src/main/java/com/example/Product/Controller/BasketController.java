package com.example.Product.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.Product.Response.BasketResponse;
import com.example.Product.Service.BasketService;

@RestController
@RequestMapping("/api/product/basket")
@CrossOrigin("*")
public class BasketController {

    @Autowired
    private BasketService basketService;

    @PostMapping("/add/{productId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> addToBasket(@PathVariable Long productId) {
        basketService.addToBasket(productId);
        return ResponseEntity.ok("Product added to basket");
    }

    @DeleteMapping("/remove/{productId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void removeFromBasket(@PathVariable Long productId) {
        basketService.removeFromBasket(productId);
    }

    @GetMapping("/my-basket")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<BasketResponse> getMyBasket() {
        return basketService.getMyBasket();
    }
}
