package com.example.Product.Entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "users")
public class UserEntity {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY )
 private Long id;
 @NotBlank(message = "username should not be blank.")
 private String username;
 private String password;
 private boolean enabled;
 private String name;
 private String surname;
 
 @Email(message = "must me email-format")
 private String email;

 
 private String phonenumber;
 public UserEntity(Long id, String username, String password, boolean enabled, String email, String phonenumber, String name, String surname) {
	    this.id = id;
	    this.username = username;
	    this.password = password;
	    this.enabled = enabled;
	    this.email = email;
	    this.phonenumber = phonenumber;
	    this.name = name;
	    this.surname = surname;
	}
public UserEntity() {
	
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
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public boolean isEnabled() {
	return enabled;
}
public void setEnabled(boolean enabled) {
	this.enabled = enabled;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhonenumber() {
	return phonenumber;
}
public void setPhonenumber(String phonenumber) {
	this.phonenumber = phonenumber;
}

public String getName() {
    return name;
}
public void setName(String name) {
    this.name = name;
}

public String getSurname() {
    return surname;
}
public void setSurname(String surname) {
    this.surname = surname;
}
}