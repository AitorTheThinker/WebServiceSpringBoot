package com.ShoppingList.demo.repositories;

import java.util.List;

import com.ShoppingList.demo.dto.CategoriesDTO;
import com.ShoppingList.demo.dto.PurchaseDTO;

public interface ICategory{
	
	public boolean saveCategoria(CategoriesDTO categoria );
	public boolean updateCompra(CategoriesDTO categoria );
	public List<CategoriesDTO> getAllCategorias();
	public CategoriesDTO getCategoriasByID(int id);
	public boolean deleteCategoria(int id);
	List<CategoriesDTO> getAllCategoria();
	CategoriesDTO getCategoriasById(int id);
	public CategoriesDTO getCategoriaById(int id);

	
}
