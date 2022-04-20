import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators} from '@angular/forms';
import {ApiServiceM} from 'src/app/services/menuServices/api.service'
import {menuI} from 'src/app/models/menuModels/menuInterface'
import {ApiService} from 'src/app/services/resturantServices/api.service'
import { ListRestaurantsI } from 'src/app/models/restaurantModels/listRestaurants';
import { Router} from '@angular/router'
import { Location } from '@angular/common';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.scss']
})
export class CreateComponent implements OnInit {
  
  public recoveredRestaurants : ListRestaurantsI[] = [];

  constructor(private api : ApiServiceM, private apiRes: ApiService, private route : Router , private _location : Location) { }

  ngOnInit(): void {
    this.apiRes.getAllRestaurants().subscribe((data) => {
      this.recoveredRestaurants = data;
      console.log(" restuarantes recuperados : " + this.recoveredRestaurants)
    })
  }

  createNewMenu = new FormGroup({
    idRestaurant : new FormControl('' , Validators.required),
    typeMenu: new FormControl('' , Validators.required),
    menuName : new FormControl('' , Validators.required),
    price: new FormControl('' , Validators.required)
  })
  
  getValuesForm(form : menuI){
    console.log("data del menu" + form);
    this.api.createNewMenu(form).subscribe(data =>{
    })
    this._location.back()
  }

 
}