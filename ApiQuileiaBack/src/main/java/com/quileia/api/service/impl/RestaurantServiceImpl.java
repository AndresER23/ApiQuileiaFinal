package com.quileia.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quileia.api.entity.Restaurant;
import com.quileia.api.repository.RestaurantRepository;
import com.quileia.api.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	private RestaurantRepository restaurantRepository;

	@Autowired
	public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
		this.restaurantRepository = restaurantRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Restaurant> findAll() {
		return restaurantRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Restaurant> findRestById(Long id) {
		return restaurantRepository.findById(id);
	}

	@Override
	@Transactional
	public Restaurant save(Restaurant restaurant) {
		return restaurantRepository.save(restaurant);
	}

	@Override
	@Transactional
	public void delete(Long idRestaurant) {
		restaurantRepository.deleteById(idRestaurant);
	}

}
