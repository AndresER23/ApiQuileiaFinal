package com.quileia.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientDTO {

	private Long idMenu;
	private Long idIngredient;
	private String ingredientName;
	private int calories;
}
