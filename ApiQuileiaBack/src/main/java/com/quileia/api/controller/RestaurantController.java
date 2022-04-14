package com.quileia.api.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Status;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quileia.api.dto.RestaurantDTO;
import com.quileia.api.entity.Restaurant;
import com.quileia.api.service.RestaurantService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/restaurants")
public class RestaurantController {

	private static Logger log = Logger.getLogger(RestaurantController.class);
	private RestaurantService restaurantService;

	public RestaurantController(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

	/**
	 * Using the data provided in the body of the request encapsulated in the object
	 * of type DTO, the method will create a new restaurant.
	 * 
	 * @param an object of type RestaurantDTO.
	 * @return the created object encapsulated in object of type DTO and a the
	 *         status of CREATED.
	 */
	@PostMapping
	public ResponseEntity<RestaurantDTO> create(@RequestBody RestaurantDTO restaurant) {
		Restaurant newRestaurant = new Restaurant();

		newRestaurant.setCommercialName(restaurant.getComercialName());
		newRestaurant.setLocationCity(restaurant.getLocationCity());
		newRestaurant.setSocialReason(restaurant.getSocialReason());
		newRestaurant.setTypeOfRestaurant(restaurant.getTypeOfRestaurant());
		newRestaurant.setOpeningTime(restaurant.getOpeningTime());
		newRestaurant.setClosingTime(restaurant.getClosingTime());
		restaurantService.save(newRestaurant);

		return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
	}

	/**
	 * the method returns all the restaurants present in the "restaurants" table if
	 * they exist.
	 * 
	 * @return if there aren't restaurants in the "restaurants" table, the method
	 *         will return the status "notFound", if there are restaurants in the
	 *         table, the method will return the list of restaurants.
	 */
	@GetMapping
	public ResponseEntity<List<Restaurant>> readAll() {
		List<Restaurant> restaurantList = restaurantService.findAll();

		if (restaurantList.isEmpty()) {
			log.error("The restaurant list is empty");
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(restaurantList);
	}

	/**
	 * Using the id provided in the path of the request, the method will verifies
	 * the existence of the restaurant in the "restaurants" table, then if there is
	 * a restaurant with this id, it will be the response.
	 * 
	 * @param id of the restaurant to be found, its will be provided through the
	 *           request path.
	 * @return if the restaurant is found the method will response with the found
	 *         restaurant, if the restaurant doesn't exist in the table
	 *         "restaurants" the method will response with the "notFound" status.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Restaurant> readById(@PathVariable(value = "id") Long restaurantId) {
		Optional<Restaurant> restaurantOptional = restaurantService.findRestById(restaurantId);

		if (restaurantOptional.isEmpty()) {
			log.error("The restaurant with id '" + restaurantId + "', doesn't exist or couldn't be recovered ");
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(restaurantOptional.get());
	}

	/**
	 * * the method first verifies the existence of the restaurant with the id, if
	 * it exists it's updated with the data provided in the body of the request
	 * 
	 * 
	 * @param updatedRestaurant contains the data for the update of the retrieved
	 *                          restaurant.
	 * 
	 * @param restaurant        id, provided in the path request.
	 * 
	 * @return if as a result of the verification it is found that there is no
	 *         restaurant, the method will return the status "notFound", otherwise,
	 *         if the update is successful, the method will return the updated
	 *         restaurant and the "CREATED" status
	 */
	@PutMapping("/{id}")
	public ResponseEntity<RestaurantDTO> update(@RequestBody RestaurantDTO updatedRestaurant,
			@PathVariable(value = "id") Long restaurantId) {
		Optional<Restaurant> previousRestaurant = restaurantService.findRestById(restaurantId);

		if (previousRestaurant.isEmpty()) {
			log.error("There isn't restaurant with id '" + restaurantId + "', "
					+ "the update is impossible without a previous restaurant.");
			return ResponseEntity.notFound().build();
		}

		previousRestaurant.get().setLocationCity(updatedRestaurant.getLocationCity());
		previousRestaurant.get().setSocialReason(updatedRestaurant.getSocialReason());
		previousRestaurant.get().setCommercialName(updatedRestaurant.getComercialName());
		previousRestaurant.get().setTypeOfRestaurant(updatedRestaurant.getTypeOfRestaurant());
		previousRestaurant.get().setOpeningTime(updatedRestaurant.getOpeningTime());
		previousRestaurant.get().setClosingTime(updatedRestaurant.getClosingTime());

		restaurantService.save(previousRestaurant.get());

		return ResponseEntity.status(HttpStatus.CREATED).body(updatedRestaurant);
	}

	/**
	 * It first verifies the existence of the restaurant using its id, then if it
	 * exists it will be removed.
	 * 
	 * @param id of the restaurant to be deleted
	 * @return if the restaurant is not found, the method will return the status
	 *         "not found", if the operation is successful, the status "OK" will be
	 *         returned together with the eliminated restaurant
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Status> delete(@PathVariable(value = "id") Long restaurantId) {
		Optional<Restaurant> restaurant = restaurantService.findRestById(restaurantId);

		if (restaurant.isEmpty()) {
			log.error("There isn't restaurant with id '" + restaurantId + "', "
					+ "the delete is impossible without a previous restaurant.");
			return ResponseEntity.notFound().build();
		}

		restaurantService.delete(restaurantId);

		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
