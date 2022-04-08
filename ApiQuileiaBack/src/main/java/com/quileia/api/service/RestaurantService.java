package com.quileia.api.service;

import java.util.List;
import java.util.Optional;

import com.quileia.api.entity.Restaurant;

public interface RestaurantService {
	/**
	 * When this method is called, the repository's ".findAll()" method is executed.
	 * Allows the generation of the list of total restaurants in the "restaurants"
	 * table
	 * 
	 * @return list of all restaurants present in the table called "restaurants".
	 */
	public List<Restaurant> findAll();

	/**
	 * Retrieve a specific restaurant using its id
	 * 
	 * @param id of restaurant to retrieve
	 * @return a restaurant encapsulated in the entity restaurant.
	 */
	public Optional<Restaurant> findRestById(Long id);

	/**
	 * Stores the news restaurants or save the changes made to a previous
	 * restaurants.
	 * 
	 * @param receives an object of type restaurant.
	 * @return a restaurant encapsulated in the entity restaurant.
	 */
	public Restaurant save(Restaurant restaurant);

	/**
	 * Allow removal of specific restaurant using its id
	 * 
	 * @param id of the restaurant to remove.
	 */
	public void delete(Long idRestaurant);
}
