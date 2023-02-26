package com.ShoppingList.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShoppingList.demo.dto.CategoriesDTO;
import com.ShoppingList.demo.dto.PurchaseDTO;
import com.ShoppingList.demo.dto.RecipeDTO;
import com.ShoppingList.demo.repositories.PurchaseRepository;



@Service
public class CompraService {

	public static String uriImagenIngrediente = "https://www.themealdb.com/images/ingredients/%s-Small.png";
	
	@Autowired
	public PurchaseRepository compraRepo;
	
	public boolean insertIngredientByApiMeal(RecipeDTO receta) {
		
		for (String ingrediente : receta.getIngredients()) {
			
			String urlImagen = String.format(uriImagenIngrediente, ingrediente);
			CategoriesDTO cat = new CategoriesDTO(1,"Frutas");
			
			PurchaseDTO compra = new PurchaseDTO(ingrediente,cat,urlImagen);
			
			if(getCompraByDescripcion(ingrediente)) {
				compraRepo.updateCompra(compra);
			}else {
				compraRepo.saveCompra(compra);
			}
		}
		
		return true;
	}
	
	public boolean getCompraByDescripcion(String descripcion) {
		
		PurchaseDTO compra = compraRepo.getCompraByDescription(descripcion);
		
		if(compra == null) {
			return false;
		}
		
		return true;
	}
	
}
