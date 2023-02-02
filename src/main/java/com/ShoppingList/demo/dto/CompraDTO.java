package com.ShoppingList.demo.dto;

public class CompraDTO {
	
	public int id;
	public String descripcion;
	private static int counterByID=1;
	
	public CategoriasDTO categorias;
	
	public static void setCounterByID(int counterByID) {
		CompraDTO.counterByID = counterByID;
	}

	public CompraDTO() {
		super();
		categorias = new CategoriasDTO();
	}
	
	public int getId() {	
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public static int getCounterByID() {

		return counterByID++;
	}

	
	public CategoriasDTO getCategorias() {
		return categorias;
	}
	
	public void setCategorias(CategoriasDTO categoria) {
		this.categorias = categoria;
		categorias.setId(categoria.getId());
	}
}
