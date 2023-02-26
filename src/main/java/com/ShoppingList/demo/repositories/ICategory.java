package com.ShoppingList.demo.repositories;

import java.util.List;

import com.ShoppingList.demo.dto.CategoriasDTO;
import com.ShoppingList.demo.dto.CompraDTO;

public interface ICategorias{
	
	public boolean saveCategoria(CategoriasDTO categoria );
	public boolean updateCompra(CategoriasDTO categoria );
	public List<CategoriasDTO> getAllCategorias();
	public CategoriasDTO getCategoriasByID(int id);
	public boolean deleteCategoria(int id);
	List<CategoriasDTO> getAllCategoria();
	CategoriasDTO getCategoriasById(int id);
	public CategoriasDTO getCategoriaById(int id);

	
}
