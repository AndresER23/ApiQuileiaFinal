package com.quileia.api.controller;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

import com.quileia.api.dto.MenuDTO;
import com.quileia.api.entity.Menu;
import com.quileia.api.service.MenuService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/menus")
public class MenuController {

	private MenuService menuService;
	private static Logger log= Logger.getLogger(MenuController.class);

	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}

	/**
	 * In the first instance, the existence of the restaurant to which the menu will
	 * be associated is verified, if it exists, the data supplied in the requestBody
	 * is transferred from a DTO type to an entity <Menu>, then saves the new menu.
	 * 
	 * @param an object of type MenuDTO
	 * @return on success the method will return the created menu and the "CREATED"
	 *         status, otherwise if the restaurant doesn't exist or is not found the
	 *         response will be "notFound" status.
	 */
	@PostMapping
	public ResponseEntity<MenuDTO> create(@RequestBody MenuDTO menuDTO) {
		Menu savedMenu;
		try {
			savedMenu = menuService.save(menuDTO);
			menuDTO.setIdMenu(savedMenu.getIdMenu());
		} catch (NotFoundException e) {
			log.error("The restaurant to which want to associate the menu couldn't be found " + e);
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(menuDTO);
	}

	/**
	 * 
	 * the method retrieves a list of all the menus that are in the "menus" table
	 * and checks that it is not empty.
	 * 
	 * @return if the list of menus is not empty, the method will return the list
	 *         and "OK" status, otherwise if it is empty, the method will return
	 *         "NotFound" status.
	 */
	@GetMapping
	public ResponseEntity<List<Menu>> readAll() {
		List<Menu> menus = menuService.findAll();
		
		if (menus.isEmpty()) {
			log.error("The menus list is empty");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		return ResponseEntity.status(HttpStatus.OK).body(menus);
	}

	/**
	 * the id provided in the path will be used for look up for the specific menu,
	 * then the existence of the menu will be checked for later response.
	 * 
	 * @param id of the specific menu that wants to be retrieved.
	 * @return if the menu exists the method will respond with the menu found and
	 *         the "OK" status, otherwise, if the menu is not found, the response
	 *         will be "notFound" status
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Menu> read(@PathVariable(name = "id") Long menuId) {
		Optional<Menu> menuOptional = menuService.findMenuById(menuId);

		if (menuOptional.isEmpty()) {
			log.error("The requested menu doesn't exist or was not found");
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(menuOptional.get());
	}

	/**
	 * The id provided in the route will be used to find the existing menu, then the
	 * data from the object sent in the request body will be used to update the
	 * previously retrieved menu.
	 * 
	 * @param an object of type MenuDTO with the data to be updated
	 * @param id of the object to update
	 * @return If the update was successful, the method will return the updated menu
	 *         encapsulated in a data and the status of "CREATED"
	 */
	@PutMapping("/{id}")
	public ResponseEntity<MenuDTO> update(@RequestBody MenuDTO menu, @PathVariable(value = "id") Long idMenu) {
		if (menuService.findMenuById(idMenu).isEmpty()) {
			log.error("The menu to update was not found ");
			return ResponseEntity.notFound().build();
		}

		menu.setIdMenu(idMenu);

		try {
			menuService.save(menu);
		} catch (NotFoundException e) {
			log.error("The associated restaurant was not found or does not exist" + e);
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(menu);
	}

	/**
	 * With the id supplied in the request the method will verify its existence, if
	 * it exists the menu will be eliminated.
	 * 
	 * @param id of the menu to be removed
	 * @return If the operation is successful, it will respond with the menu removed
	 *         and the status OK. In case the menu was not found, the method will
	 *         respond with a status of notFound.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Menu> delete(@PathVariable(value = "id") Long idMenu) {
		Optional<Menu> menu = menuService.findMenuById(idMenu);

		if (menu.isEmpty()) {
			log.error("The requested menu to delete doesn't exist or was not found");
			return ResponseEntity.notFound().build();
		}

		menuService.delete(idMenu);

		return ResponseEntity.ok(menu.get());
	}
}
