package com.ShoppingList.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ShoppingList.demo.repositories.PurchaseRepository;

import com.ShoppingList.demo.dto.PurchaseDTO;



@RestController
public class PurchaseApi {
	
	@Autowired
	PurchaseRepository purchaseRepository;
	
	@GetMapping("/compras")
	public List<PurchaseDTO> getCompras() {
		return purchaseRepository.getAllCompras();
	}
	
	@GetMapping("/categorias")
	public List<PurchaseDTO> getCategorias() {
		return purchaseRepository.getAllCategorias();
	}
	
	@GetMapping("/compras/{id}")
	public PurchaseDTO getCompraById(int id) {
		return purchaseRepository.getCompraByID(id);
	}
	
	@GetMapping("/compras/descripcion/{descripcion}")
	public PurchaseDTO getCompraByDescripcion(String descripcion) {
		return purchaseRepository.getCompraByDescription(descripcion);
	}
	
}
