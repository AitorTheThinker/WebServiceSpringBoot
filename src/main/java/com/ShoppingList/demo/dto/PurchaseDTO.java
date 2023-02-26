package com.ShoppingList.demo.dto;

public class PurchaseDTO {
	
	public int id;
	public String descripcion;
	public CategoriesDTO categorias;
	public String imagenUrl;
	public boolean enabled;
	
	public PurchaseDTO(int id, String descripcion, CategoriesDTO categorias, String imagenUrl, boolean enabled) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.categorias = categorias;
		this.imagenUrl = imagenUrl;
		this.enabled = enabled;
	}

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public PurchaseDTO() {
		super();
		categorias = new CategoriesDTO();
	}
	
	public PurchaseDTO(String ingrediente, CategoriesDTO cat, String urlImagen) {
		// TODO Auto-generated constructor stub
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

	
	public CategoriesDTO getCategorias() {
		return categorias;
	}
	
	public void setCategorias(CategoriesDTO categoria) {
		this.categorias = categoria;
		categorias.setId(categoria.getId());
	}
}
