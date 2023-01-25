package com.ShoppingList.demo.dto;

public class CategoriasDTO {
	public int id;

	public String categoria;
	
	public String getcategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria= categoria;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public CategoriasDTO() {
		super();
	}
	public CategoriasDTO(int id, String categoria) {
		super();
		this.id = id;
		this.categoria = categoria;
	}
}
