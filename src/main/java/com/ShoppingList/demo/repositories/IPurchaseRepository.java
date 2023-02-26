package com.ShoppingList.demo.repositories;

import java.util.List;

import com.ShoppingList.demo.dto.PurchaseDTO;

public interface IPurchaseRepository {
	
	public boolean saveCompra(PurchaseDTO compra);
	public boolean updateCompra(PurchaseDTO compra);
	public List<PurchaseDTO> getAllCompras();
	public PurchaseDTO getCompraByID(int id);
	public boolean deleteCompra(int id);
	List<PurchaseDTO> getAllCategorias();
	public PurchaseDTO getCompraByDescription(String descripcion);
}
