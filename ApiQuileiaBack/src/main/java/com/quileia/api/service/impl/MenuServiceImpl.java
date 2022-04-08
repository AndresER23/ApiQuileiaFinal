package com.quileia.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quileia.api.dto.MenuDTO;
import com.quileia.api.entity.Menu;
import com.quileia.api.entity.Restaurant;
import com.quileia.api.repository.MenuRepository;
import com.quileia.api.service.MenuService;
import com.quileia.api.service.RestaurantService;

@Service
public class MenuServiceImpl implements MenuService {

	private MenuRepository menuRepository;
	private RestaurantService restaurantService;
	
	@Autowired
	public MenuServiceImpl(MenuRepository menuRepository, RestaurantService restaurantService) {
		this.menuRepository = menuRepository;
		this.restaurantService = restaurantService;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Menu> findAll() {
		return menuRepository.findAll();
	}

	@Override
	@Transactional
	public void delete(Long idMenu) {
		menuRepository.deleteById(idMenu);
	}

	@Override
	@Transactional
	public Menu save(MenuDTO menuDTO) throws NotFoundException {
		Optional<Restaurant> associatedRestaurant= restaurantService.findRestById(menuDTO.getIdRestaurant());
		
		if (associatedRestaurant.isEmpty()) {
			throw new NotFoundException();
		}
		
		Menu menu= new Menu();
		menu.setMenuName(menuDTO.getMenuName());
		menu.setPrice(menuDTO.getPrice());
		menu.setTypeMenu(menuDTO.getTypeMenu());
		menu.setRestaurant(associatedRestaurant.get());

		return menuRepository.save(menu);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Menu> findMenuById(Long idMenu) {
		return menuRepository.findById(idMenu);
	}
}
