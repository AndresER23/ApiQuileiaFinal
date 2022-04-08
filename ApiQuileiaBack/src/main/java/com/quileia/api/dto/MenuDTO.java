package com.quileia.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuDTO {

	private Long idMenu;
	private int typeMenu;
	private String menuName;
	private int price;
	private Long idRestaurant;
}
