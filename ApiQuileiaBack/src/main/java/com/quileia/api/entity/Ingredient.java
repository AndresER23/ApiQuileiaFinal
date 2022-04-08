package com.quileia.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ingredients")
public class Ingredient implements Serializable {

	private static final long serialVersionUID = 2642816151397651259L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long idIngredient;
	
	@Column(name = "INGREDIENT_NAME")
	private String ingredientName;
	
	@Column(name = "CALORIES")
	private int calories;
	
	@JoinColumn(name = "ID_MENU", referencedColumnName = "ID_MENU")
	@ManyToOne
	@JsonIgnore
	private Menu menu;	
}
