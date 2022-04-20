import { Component, OnInit } from '@angular/core';
import { FormControl, Validators, FormGroup } from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router'
import { ListRestaurantsI } from 'src/app/models/restaurantModels/listRestaurants';
import {ApiService} from '../../../services/resturantServices/api.service';
@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.scss']
})
export class EditRestaurant implements OnInit {
  
  public idRestaurant : number = 0
  public recoveredRestaurant : any = []

  constructor(private router: Router, private activateRoute : ActivatedRoute, private api : ApiService) {
    this.activateRoute.params.subscribe(data =>{
      this.idRestaurant = data['id']
      console.log(this.idRestaurant)
    })
   }

  ngOnInit(): void {
      this.api.getRestaurantById(this.idRestaurant).subscribe(data=>{
          this.recoveredRestaurant= data
      })
  }

  editRestaurant = new FormGroup({
     
    comercialName: new FormControl('', Validators.required),
    socialReason : new FormControl('', Validators.required),
    typeOfRestaurant : new FormControl('', Validators.required),
    locationCity : new FormControl('', Validators.required),
    openingTime : new FormControl('', Validators.required),
    closingTime : new FormControl('', Validators.required),

  });

  getValuesForm(form : ListRestaurantsI){
    this.api.updateRestaurant(form, this.idRestaurant).subscribe(data =>{
        console.log("restaurante actualizado "  + this.recoveredRestaurant)
    })

    this.router.navigate(['restaurant/list'])
  }

  deleteRestaurant(){
    this.api.deleteRestaurant(this.idRestaurant).subscribe(data=>{
      
    })
    this.router.navigate(['restaurant/list'])
  }

}
