package com.quileia.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.quileia.api.dto.IngredientDTO;
import com.quileia.api.entity.Ingredient;
import com.quileia.api.entity.Menu;
import com.quileia.api.exceptions.ExceededCaloriesException;
import com.quileia.api.repository.IngredientRepository;
import com.quileia.api.service.IngredientService;
import com.quileia.api.service.MenuService;

@Service
public class IngredientServiceImpl implements IngredientService {

	@Value("${maximum.calorie.limit}")
	private int maximunCalories;
	private IngredientRepository ingredientRepository;
	private MenuService menuService;

	@Autowired
	public IngredientServiceImpl(IngredientRepository ingredientRepository, MenuService menuService) {
		this.ingredientRepository = ingredientRepository;
		this.menuService = menuService;
	}

	@Override
	public List<Ingredient> findAll() {
		return ingredientRepository.findAll();
	}

	@Override
	public Optional<Ingredient> findById(Long id) throws NotFoundException {
		Optional<Ingredient> optionalIngredient = ingredientRepository.findById(id);

		if (optionalIngredient.isEmpty()) {
			throw new NotFoundException();
		}

		Ingredient verificatedIngredient = optionalIngredient.get();
		IngredientDTO ingredientDTO = new IngredientDTO();

		ingredientDTO.setCalories(verificatedIngredient.getCalories());
		ingredientDTO.setIdIngredient(verificatedIngredient.getIdIngredient());
		ingredientDTO.setIngredientName(verificatedIngredient.getIngredientName());

		return optionalIngredient;
	}

	@Override
	public IngredientDTO save(IngredientDTO newIngredient) throws ExceededCaloriesException, NotFoundException {
		long idMenu = newIngredient.getIdMenu();
		Optional<Menu> retrievedMenu = menuService.findMenuById(idMenu);

		if (retrievedMenu.isEmpty()) {
			throw new NotFoundException();
		}

		Menu menu = retrievedMenu.get();
		
		Ingredient ingredient = new Ingredient();
		ingredient.setIngredientName(newIngredient.getIngredientName());
		ingredient.setCalories(newIngredient.getCalories());
		ingredient.setMenu(menu);

		if (!caloriesVerification(newIngredient)) {
			throw new ExceededCaloriesException("The calories quantity is exceeded");
		}
		
		ingredientRepository.save(ingredient);
		return newIngredient;
	}

	@Override
	public void delete(Long id) {
		ingredientRepository.deleteById(id);
	}

	@Override
	public List<Ingredient> findIngredientByIdMenu(Long idMenu) {
		return ingredientRepository.findIngredientByMenuIdMenu(idMenu);
	}

	public boolean caloriesVerification(IngredientDTO newIngredient) {
		List<Ingredient> associatedIngredients = ingredientRepository
				.findIngredientByMenuIdMenu(newIngredient.getIdMenu());
		int preExistingCalories = 0;

		for (Ingredient ingredient : associatedIngredients) {
			preExistingCalories += ingredient.getCalories();
		}

		return (preExistingCalories + newIngredient.getCalories() <= maximunCalories);
	}
}
