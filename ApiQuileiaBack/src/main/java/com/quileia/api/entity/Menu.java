package com.quileia.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "menus")
public class Menu implements Serializable {

	private static final long serialVersionUID = 7366815033026146015L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_MENU")
	private Long idMenu;
	
	@Column(name = "TYPE_MENU")
	private int typeMenu;
	
	@Column(name = "MENU_NAME")
	private String menuName;
	
	@Column(name = "PRICE")
	private int price;
	
	@JsonBackReference
	@JoinColumn(name = "ID_RESTAURANT", referencedColumnName = "ID_RESTAURANT")
	@ManyToOne(fetch = FetchType.LAZY)
	private Restaurant restaurant;
}
