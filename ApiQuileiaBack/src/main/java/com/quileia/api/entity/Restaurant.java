package com.quileia.api.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "restaurants")
public class Restaurant implements Serializable {

	private static final long serialVersionUID = 123578170135326487L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_restaurant")
	private Long idRestaurant;

	@Column(name = "COMMERCIAL_NAME", length = 50)
	private String commercialName;

	@Column(name = "SOCIAL_REASON")
	private String socialReason;

	@Column(name = "TYPE_OF_RESTAURANT")
	private int typeOfRestaurant;

	@Column(name = "LOCATION_CITY")
	private String locationCity;

	@Column(name = "OPENING_TIME")
	private int openingTime;

	@Column(name = "CLOSING_TIME")
	private int closingTime;

	@JsonManagedReference
	@OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Menu> menuList;
}	

