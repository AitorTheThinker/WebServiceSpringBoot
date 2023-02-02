package com.ShoppingList.demo.dto;

import java.util.ArrayList;

public class RecipeResponseDTO {
	
	public ArrayList<RecipeDTO> meals;

	public ArrayList<RecipeDTO> getMeals() {
		return meals;
	}

	public void setMeals(ArrayList<RecipeDTO> meals) {
		this.meals = meals;
	}

}
