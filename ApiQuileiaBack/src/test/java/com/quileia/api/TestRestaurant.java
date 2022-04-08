package com.quileia.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.quileia.api.dto.RestaurantDTO;
import com.quileia.api.entity.Restaurant;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
@SpringBootTest(classes = ApiRestaurantesQuileiaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestRestaurant {

	@LocalServerPort
	Integer port;
	
	private static Logger log= Logger.getLogger(TestRestaurant.class);
	private TestRestTemplate testRestTemplate;
	private RestaurantDTO restaurantSent;
	
	@Autowired
	public TestRestaurant(TestRestTemplate testRestTemplate) {
		this.testRestTemplate = testRestTemplate;
	}
	
	@BeforeEach
	void SetUp() {
		
		restaurantSent= new RestaurantDTO("MCDONDALDS", "SAS", 2 , "BOGOTA", 9, 18);
	}
	
	@Test
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "cleanPostRestaurant.sql")
	void postRestaurantTest() {
		
		ResponseEntity<RestaurantDTO> Response= testRestTemplate.postForEntity("http://localhost:" + port + "/api/restaurants",
				restaurantSent,RestaurantDTO.class);
		
		log.info("The result of the request through to restTemplate is: " + Response);
		//JdbcTemplate para ir a la base de datos.
		
		assertEquals(restaurantSent, Response.getBody());
	}
	
	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "prepareGetRestaurant.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "cleanGetRestaurant.sql")
	void getRestaurantTest() {
		
		ResponseEntity<Restaurant> recoveredRestaurant= testRestTemplate.getForEntity("http://localhost:" + port + "/api/restaurants/1",
				Restaurant.class);
		
		assertNotNull(recoveredRestaurant);
		
		log.info("the result of the get request through to restTemplate is: "+ recoveredRestaurant.getBody());
	}
	
	@Test
	void putRestaurantTest() {
		
		RestaurantDTO restaurantWchanges= new RestaurantDTO("COCORICO", "SAS", 2, "BOGOTA", 7, 20);
		int id= 1;
		
		testRestTemplate.put("http://localhost:" + port + "/api/restaurants", 
				restaurantWchanges, id);
		
		assertNotEquals(restaurantWchanges, restaurantSent);
		assertNotEquals(restaurantWchanges.getComercialName(), restaurantSent.getComercialName());
	}
	
	
	@Test
	void deleteRestaurantTest() {
		
		int id= 1;
		
		testRestTemplate.delete("http://localhost:" + port + "/api/restaurants", id);
		
		ResponseEntity<Restaurant> recoveredRestaurant= testRestTemplate.getForEntity("http://localhost:" + port + "/api/restaurants/1",
				Restaurant.class);
		
		assertNull(recoveredRestaurant.getBody());
	}
}
		

