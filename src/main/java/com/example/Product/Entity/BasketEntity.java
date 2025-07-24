package com.example.Product.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "basket")
public class BasketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long productsId;
    private int quantity;
    

    public BasketEntity() {
    }

    public BasketEntity(Long userId, Long productsId,int quantity) {
        this.userId = userId;
        this.productsId = productsId;
        this.quantity=quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductsId() {
        return productsId;
    }

    public void setProductsId(Long productsId) {
        this.productsId = productsId;
    }
    public int getQuantity() {
    	return quantity; 
    	}
    
    public void setQuantity(int quantity) {
    	this.quantity = quantity;
}}