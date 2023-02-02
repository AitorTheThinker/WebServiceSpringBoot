package com.ShoppingList.demo.dto;

public class UserDTO {
	private String userName;
	private String password;
	private String roles;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String usernName) {
		this.userName = usernName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
}
