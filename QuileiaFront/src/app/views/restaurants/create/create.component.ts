import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ListRestaurantsI } from 'src/app/models/restaurantModels/listRestaurants';
import { ApiService } from '../../../services/resturantServices/api.service';
import { Router} from '@angular/router'
import { Location} from '@angular/common'

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.scss']
})
export class CreateRestaurant implements OnInit {

  constructor(private api : ApiService, private router : Router, private _location : Location) { }

  ngOnInit(): void {
    
  }

  createNewRestaurant = new FormGroup({
     
    comercialName: new FormControl('', Validators.required),
    socialReason : new FormControl('', Validators.required),
    typeOfRestaurant : new FormControl('', Validators.required),
    locationCity : new FormControl('', Validators.required),
    openingTime : new FormControl('', Validators.required),
    closingTime : new FormControl('', Validators.required),

  });

  getValuesForm(form : ListRestaurantsI){
    this.api.createNewRestaurant(form).subscribe(data =>{
        console.log(data);
    })

    this._location.back();
  }

}

