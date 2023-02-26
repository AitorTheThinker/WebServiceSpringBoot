package com.ShoppingList.demo.service;
	import java.util.ArrayList;
	
	import org.springframework.stereotype.Service;
	import org.springframework.web.client.RestTemplate;
	
	import com.ShoppingList.demo.dto.RecipeDTO;
	import com.ShoppingList.demo.dto.RecipeResponseDTO;
	import com.google.gson.Gson;
	
	
	@Service
	public class RecipesServices {
		
		private final String urlMealApiRandom="https://www.themealdb.com/api/json/v1/1/random.php";
		private final String urlMealApiByIdMeal="https://www.themealdb.com/api/json/v1/1/lookup.php?i=";
		
		public RecipeDTO getRecipeFromApi(){
			
			RestTemplate restT= new RestTemplate();
			String result= restT.getForObject(urlMealApiRandom, String.class);
			
			RecipeResponseDTO recipes=getResponseByString(result);

			return recipes.getMeals().get(0);
			
		}
		
		public ArrayList<RecipeDTO> getListRecipesRandom(){
			ArrayList<RecipeDTO> recipes= new ArrayList<RecipeDTO>();
			for(int i=0;i<5;i++) {
				recipes.add(getRecipeFromApi());
			}
			
			return recipes;
	}
		public RecipeDTO getIndregientsByIdMeal(int idMeal) {
			RestTemplate restT= new RestTemplate();
			String result= restT.getForObject(urlMealApiByIdMeal+idMeal, String.class);
			RecipeResponseDTO recipe= getResponseByString(result);
			return recipe.getMeals().get(0);
		}
		
		public RecipeResponseDTO getResponseByString(String result) {
			Gson gson= new Gson();
			RecipeResponseDTO recipes=gson.fromJson(result, RecipeResponseDTO.class);
			return recipes;
		}
	}
