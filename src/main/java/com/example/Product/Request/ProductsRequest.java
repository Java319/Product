package com.example.Product.Request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductsRequest {

    @NotBlank(message = "brand name must not be empty")
    private String brand;

    @NotBlank(message = "model must not be empty")
    private String model;

    private String category;

    @Size(max = 1000, message = "image URL is too long")
    private String imageUrl;

    @NotNull(message = "price must not be empty")
    private Double price;

    @Min(value = 1, message = "rating should be ≥ 1")
    @Max(value = 5, message = "rating should be ≤ 5")
    private Double rating;
    
    
    
   private String description;

    public ProductsRequest() {
    }

    public ProductsRequest(String brand, String model, String category, String imageUrl, Double price, Double rating, String description) {
        this.brand = brand;
        this.model = model;
        this.category = category;
       this.imageUrl=imageUrl;
        this.price = price;
        this.rating = rating;
        this.description=description;
    }

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}