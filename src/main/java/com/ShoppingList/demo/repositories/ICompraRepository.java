package com.ShoppingList.demo.repositories;

import java.util.List;

import com.ShoppingList.demo.dto.CompraDTO;

public interface ICompraRepository {
	
	public boolean saveCompra(CompraDTO compra);
	public boolean updateCompra(CompraDTO compra);
	public List<CompraDTO> getAllCompras();
	public CompraDTO getCompraByID(int id);
	public boolean deleteCompra(int id);
	List<CompraDTO> getAllCategorias();
	public CompraDTO getCompraByDescription(String descripcion);
}
