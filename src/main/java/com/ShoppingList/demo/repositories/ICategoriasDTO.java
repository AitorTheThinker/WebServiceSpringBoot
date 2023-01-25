package com.ShoppingList.demo.repositories;

import java.util.List;

import com.ShoppingList.demo.dto.CategoriasDTO;
import com.ShoppingList.demo.dto.CompraDTO;

public interface ICategoriasDTO{
	
	public boolean saveCategoria(CategoriasDTO categoria );
	public boolean updateCompra(CategoriasDTO categoria );
	public List getAllCategorias();
	public CategoriasDTO getCategoriasByID(int id);
	public boolean deleteCategoria(int id);

	
}
