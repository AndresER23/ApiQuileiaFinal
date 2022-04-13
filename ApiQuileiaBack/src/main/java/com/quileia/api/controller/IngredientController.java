package com.quileia.api.controller;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quileia.api.dto.IngredientDTO;
import com.quileia.api.entity.Ingredient;
import com.quileia.api.exceptions.ExceededCaloriesException;
import com.quileia.api.service.IngredientService;

import ch.qos.logback.core.status.Status;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

	private IngredientService ingredientService;
	private static Logger log = Logger.getLogger(IngredientController.class);

	@Autowired
	public IngredientController(IngredientService ingredientService) {
		this.ingredientService = ingredientService;
	}

	/**
	 * the method receives all the data necessary to create an ingredient,
	 * encapsulated in an object of type ingredientDTO and then it calls the create
	 * method of the ingredient service.
	 *
	 * @param an object of type ingredientDTO
	 * @return in case of success in the operation it returns the saved element
	 *         otherwise it returns a forbidden status
	 */
	@PostMapping
	public ResponseEntity<IngredientDTO> create(@RequestBody IngredientDTO ingredientDTO) {
		IngredientDTO savedIngredient;
		try {
			savedIngredient= ingredientService.save(ingredientDTO);
			ingredientDTO.setIdIngredient(savedIngredient.getIdIngredient());
			return ResponseEntity.status(HttpStatus.CREATED).body(ingredientDTO);
		} catch (ExceededCaloriesException | NotFoundException e) {
			log.error( "it was not possible to create a new ingredient,"
					    + " the total calories exceed those allowed by menu "+ e);
			
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}

	/**
	 * with the id provided in the path of the request, the method searches for a
	 * specific ingredient by its id
	 * 
	 * @param id of the ingredient to be found
	 * @return if the ingredient is found it will be returned with the status "OK",
	 *         if the ingredient is not found the status "notFound()" will be
	 *         returned.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Ingredient> read(@PathVariable(value = "id") Long idIngredient) {
		Optional<Ingredient> recoveredIngredient;
		Ingredient ingredient;

		try {
			recoveredIngredient = ingredientService.findById(idIngredient);

			if (recoveredIngredient.isEmpty()) {
				log.info("no ingredient found with id:" + idIngredient);
				return ResponseEntity.notFound().build();
			}
			ingredient = recoveredIngredient.get();
			
		} catch (NotFoundException e) {
			log.error("it was not possible to find the menu to which the ingredient would be associated." + e);
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(ingredient);
	}

	/**
	 * with the id provided in the path the method looks up the ingredient, checks
	 * the existence of the ingredient and then allows update the specific
	 * ingredient
	 * 
	 * @param an         object of type "Ingredient" in the body of the request
	 * @param ingredient id to be updated
	 * @return on success the method returns the updated ingredient, if the
	 *         ingredient is not found the method returns the status "notFound".
	 * @throws NotFoundException         in case the ingredient is not found
	 * @throws ExceededCaloriesException in case the calories exceed the stipulated
	 *                                   limit
	 */
	@PutMapping("/{id}")
	public ResponseEntity<IngredientDTO> update(@RequestBody IngredientDTO ingredientDTO,
			@PathVariable(value = "id") Long idIngredient) throws ExceededCaloriesException, NotFoundException {

		Optional<Ingredient> ingredientOptional = ingredientService.findById(idIngredient);
		if (ingredientOptional.isEmpty()) {
			log.error("The ingredient to be updated could not be found");
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(ingredientService.save(ingredientDTO));
	}

	/**
	 * 
	 * the method receives the id of the ingredient to be eliminated, locates the
	 * ingredient and, if it exists, eliminates it.
	 * 
	 * @param id of the ingredient to be removed.
	 * @return if the ingredient exist and the removal is successful, the response
	 *         will be the ingredient that was removed and the "OK" state, otherwise
	 *         if the ingredient doesn't exist, the method will response with the
	 *         "notFound()" state.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Status> delete(@PathVariable(value = "id") Long idIngredient) {
		try {
			ingredientService.findById(idIngredient);
		} catch (NotFoundException e) {
			log.error("The ingredient to be deleted could not be found" + e);
			return ResponseEntity.notFound().build();
		}

		ingredientService.delete(idIngredient);

		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
