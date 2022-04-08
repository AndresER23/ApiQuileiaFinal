package com.quileia.api.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RestaurantDTO {

	private String comercialName;
	private String socialReason;
	private int typeOfRestaurant;
	private String locationCity;
	private int openingTime;
	private int closingTime;
}
