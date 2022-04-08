package com.quileia.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.quileia.api.dto.IngredientDTO;
import com.quileia.api.entity.Ingredient;
import com.quileia.api.exceptions.ExceededCaloriesException;

public interface IngredientService {

	/**
	 * When this method is called, the repository's ".findAll()" method is executed.
	 * allows the generation of the list of total ingredients in the "ingredients"
	 * table
	 * 
	 * @return list of all ingredients present in the table called "ingredients".
	 */
	public List<Ingredient> findAll();

	/**
	 * Retrieve a specific ingredient using its id
	 * 
	 * @param id of ingredient to retrieve
	 * @return an ingredient encapsulated in the entity Ingredient
	 * @throws NotFoundException
	 */
	public Optional<Ingredient> findById(Long id) throws NotFoundException;

	/**
	 * V erifies the existence of the menu to which the ingredient is associated,
	 * calls the "caloriesVerification" method and based on the result of the
	 * execution of this method, the new method or the changes made to an existing
	 * one will be saved.
	 * 
	 * @param receives an object of type ingredient.
	 * @return an ingredient encapsulated in the entity Ingredient
	 * 
	 * @throws NotFoundException
	 */
	public IngredientDTO save(IngredientDTO ingredient) throws ExceededCaloriesException, NotFoundException;

	/**
	 * Allow removal of specific ingredient using its id
	 * 
	 * @param id of the ingredient to remove.
	 */
	public void delete(Long id);

	/**
	 * Find all the ingredient associated with an id menu.
	 * 
	 * @param Receives as a parameter the id of the menu from which you want to know
	 *                 the ingredients associated with it
	 * @return List of ingredients associated to the specific menu.
	 */
	public List<Ingredient> findIngredientByIdMenu(Long idMenu);

	/**
	 * 
	 * This method verifies that the new calories do not exceed the maximum calories
	 * stipulated in the requirement.
	 * 
	 * @param recieves the data necessary for create an ingredient, encapsulated in
	 *                 a object of type ingredientDTO
	 * @return if the sum of calories exceeds 2000 calories it will return false, if
	 *         instead the sum of calories does not exceed the limit the method will
	 *         return true
	 */
	public boolean caloriesVerification(IngredientDTO newIngredient);
}
