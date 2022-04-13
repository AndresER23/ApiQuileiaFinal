import { Component, OnInit } from '@angular/core';
import {ApiService} from '../../../services/api.service';
import {ListRestaurantsI} from '../../../models/restaurantModels/listRestaurants'
@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListOfRestaurants implements OnInit {

  constructor(private apiServ: ApiService) { }

  ngOnInit(): void {
       this.apiServ.getAllRestaurants().subscribe(data=>{
         console.log(data)
       })
      
  }

}
