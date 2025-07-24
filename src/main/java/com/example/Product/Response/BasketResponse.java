package com.example.Product.Response;


public class BasketResponse {
	private Long id;
    private String brand;
    private String model;
    private String type;           
    private String category;      
    private String description;
    private Double price;
    private Double rating;
    private String imageUrl;
    private Long ownerId;
    private Integer quatity;
    
  
    public BasketResponse() {
    }

   
    public BasketResponse(String brand, String type, String category, String description, Double price, Double rating, String imageUrl, Long ownerId, Integer quatity, Long id,String model) {
        this.brand = brand;
        this.type = type;
        this.category = category;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.ownerId = ownerId;
        this.quatity = quatity;
        this.id=id;
        this.model=model;
    }

   
    public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getQuatity() {
        return quatity;
    }

    public void setQuatity(Integer quatity) {
        this.quatity = quatity;
    }

}