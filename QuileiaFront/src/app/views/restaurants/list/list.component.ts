import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../../services/resturantServices/api.service';
import { ListRestaurantsI } from '../../../models/restaurantModels/listRestaurants';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss'],
})
export class ListOfRestaurants implements OnInit {
  public allRestaurants: ListRestaurantsI[] = [];
  
  

  constructor(private apiServ: ApiService, private router: Router) {}

  ngOnInit(): void {
    this.apiServ.getAllRestaurants().subscribe((data) => {
      this.allRestaurants = data;
      console.log(data)
    });
  }

  viewRestaurant(idRes: number) {
    this.router.navigate([`restaurant/consult/${idRes}`]);
  }

  createRestaurant(){
    this.router.navigate(['restaurant/create']);
  }
  
  editRestaurant(idRes: number){
    this.router.navigate([`restaurant/edit/${idRes}`]);
  }

}
