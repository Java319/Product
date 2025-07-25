package com.example.Product.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "authorities")
public class AuthorityEntity {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
 String	username;
 String	authority;
public AuthorityEntity(Long id, String username, String authority) {
	super();
	this.id = id;
	this.username = username;
	this.authority = authority;
}
public AuthorityEntity() {
	
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getAuthority() {
	return authority;
}
public void setAuthority(String authority) {
	this.authority = authority;
}

 
	
	
}
