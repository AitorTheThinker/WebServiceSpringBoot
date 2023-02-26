package com.ShoppingList.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ShoppingList.demo.dto.RecipeDTO;
import com.ShoppingList.demo.service.CompraService;
import com.ShoppingList.demo.service.RecipesServices;

@Controller
public class RecipesController {
	
	@Autowired
	RecipesServices recipeService;
	
	@Autowired
	CompraService compraService;
	
	@GetMapping(value="/go-to-Recipe")
	public String goTopageListRecetas(Model modelo) {
		
		ArrayList<RecipeDTO> recipe=recipeService.getListRecipesRandom();
		modelo.addAttribute("recipes", recipe);
		
		return"Recipe/RecipeList";
		
	}

	@GetMapping("/add-ingredients")
	public String addIngredientsByIdMeal(@RequestParam("idMeal")int idMeal) {
		
		RecipeDTO receta = recipeService.getIndregientsByIdMeal(idMeal);
		
		compraService.insertIngredientByApiMeal(receta);
		
		return "redirect:/go-to-lista";
	}

}
