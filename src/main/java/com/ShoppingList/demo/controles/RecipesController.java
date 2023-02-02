package com.ShoppingList.demo.controles;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ShoppingList.demo.dto.RecipeDTO;
import com.ShoppingList.demo.service.RecipesServices;

@Controller
public class RecipesController {
	
	@Autowired
	RecipesServices recipeService;
	
	@GetMapping(value="/go-to-Recipe")
	public String goTopageListRecetas(Model modelo) {
		
		ArrayList<RecipeDTO> recipe=recipeService.getListRecipesRandom();
		modelo.addAttribute("recipes", recipe);
		
		return"Recipe/pListaRecipe";
		
	}

	@GetMapping("/add-ingredients")
	public String addIngredientsByIdMeal(@RequestParam("idMeal")int idMeal) {
		
		RecipeDTO recipe= recipeService.getIndregientsByIdMeal(idMeal);
		
		return"pLista";
	}

}
