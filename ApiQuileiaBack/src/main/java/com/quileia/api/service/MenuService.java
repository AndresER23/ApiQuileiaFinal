package com.quileia.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.quileia.api.dto.MenuDTO;
import com.quileia.api.entity.Menu;

public interface MenuService {
	/**
	 * When this method is called, the repository's ".findAll()" method is executed.
	 * Allows the generation of the list of total menus in the "menus" table
	 * 
	 * @return list of all menus present in the table called "menus".
	 */
	public List<Menu> findAll();

	/**
	 * Retrieve a specific menu using its id.
	 * 
	 * @param Id of menu to retrieve.
	 * @return a menu encapsulated in the entity Menu.
	 */
	public Optional<Menu> findMenuById(Long idMenu);

	/**
	 * Stores the new menu or save the changes made to a previous menus.
	 * 
	 * @param Receives an object of type menu.
	 * @return A menu encapsulated in the entity menu
	 * @throws NotFoundException 
	 */
	public Menu save(MenuDTO menu) throws NotFoundException;

	/**
	 * Allow removal of specific menu using its id
	 * 
	 * @param id of the menu to remove.
	 */
	public void delete(Long idMenu);
}
