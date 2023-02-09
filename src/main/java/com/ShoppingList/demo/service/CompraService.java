package com.ShoppingList.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShoppingList.demo.dto.CategoriasDTO;
import com.ShoppingList.demo.dto.CompraDTO;
import com.ShoppingList.demo.dto.RecipeDTO;
import com.ShoppingList.demo.repositories.CompraRepository;



@Service
public class CompraService {

	public static String uriImagenIngrediente = "https://www.themealdb.com/images/ingredients/%s-Small.png";
	
	@Autowired
	public CompraRepository compraRepo;
	
	public boolean insertIngredientByApiMeal(RecipeDTO receta) {
		
		for (String ingrediente : receta.getIngredients()) {
			
			String urlImagen = String.format(uriImagenIngrediente, ingrediente);
			CategoriasDTO cat = new CategoriasDTO(1,"Frutas");
			
			CompraDTO compra = new CompraDTO(ingrediente,cat,urlImagen);
			
			if(getCompraByDescripcion(ingrediente)) {
				compraRepo.updateCompra(compra);
			}else {
				compraRepo.saveCompra(compra);
			}
		}
		
		return true;
	}
	
	public boolean getCompraByDescripcion(String descripcion) {
		
		CompraDTO compra = compraRepo.getCompraByDescription(descripcion);
		
		if(compra == null) {
			return false;
		}
		
		return true;
	}
	
}
