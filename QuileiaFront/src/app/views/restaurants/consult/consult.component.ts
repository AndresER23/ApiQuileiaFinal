import { Component, OnInit, Input } from '@angular/core';
import { ListRestaurantsI } from 'src/app/models/restaurantModels/listRestaurants';
import {ApiService} from '../../../services/api.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-consult',
  templateUrl: './consult.component.html',
  styleUrls: ['./consult.component.scss']
})

export class ConsultComponent implements OnInit {

  public idRestaurant: number = 0;

  constructor(private apiServ: ApiService, private router: Router, private activateRoute : ActivatedRoute) {

    this.activateRoute.params.subscribe(params => {
      this.idRestaurant= params['id'];
    })
   }

  public recoveredRestaurant : any = [];

  ngOnInit(): void {
    this.apiServ.getRestaurantById(this.idRestaurant).subscribe(data=>{
        this.recoveredRestaurant= data;
    })
  }

  showMenu(){
    this.router.navigate(['menu/list']);
  }


}
