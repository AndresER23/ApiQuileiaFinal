import { EventEmitter, Injectable, OnInit, Output} from '@angular/core';
import { ResponseI} from '../models/response'
import { HttpClient, HttpHeaders} from '@angular/common/http'
import {ListRestaurantsI} from '../models/restaurantModels/listRestaurants'
import { Observable } from 'rxjs';



@Injectable({
  providedIn: 'root'
})
export class ApiService{
  
  @Output() comunicateDataRes: EventEmitter<ListRestaurantsI> = new EventEmitter();

  constructor(private http: HttpClient) { }
  
  getAllRestaurants(): Observable<ListRestaurantsI[]>{
    let url = 'http://localhost:8080/api/restaurants';
    return this.http.get<ListRestaurantsI[]>(url)
  }

  getRestaurantById(id:number): Observable<ListRestaurantsI>{
    let url = 'http://localhost:8080/api/restaurants/' + id;
    return this.http.get<ListRestaurantsI>(url);
  }
}
