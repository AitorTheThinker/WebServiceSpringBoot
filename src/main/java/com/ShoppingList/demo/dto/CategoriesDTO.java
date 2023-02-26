package com.ShoppingList.demo.dto;

public class CategoriesDTO {

	public int id;
	public String categoria;
	
	public CategoriesDTO(int id, String categoria) {
		super();
		this.id = id;
		this.categoria = categoria;
	}
	public CategoriesDTO() {
		super();
	}
	
	
	public CategoriesDTO(int id) {
		super();
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
	
}
