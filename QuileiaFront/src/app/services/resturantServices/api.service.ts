import { EventEmitter, Injectable, OnInit, Output} from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http'
import {ListRestaurantsI} from '../../models/restaurantModels/listRestaurants'
import { Observable } from 'rxjs';
import {ResponseI} from '../../models/restaurantModels/response.interface';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ApiService{
  
  url = environment.urlRestaurants;

  constructor(private http: HttpClient) { }
  
  getAllRestaurants(): Observable<ListRestaurantsI[]>{
    return this.http.get<ListRestaurantsI[]>(this.url)
  }

  getRestaurantById(id:number): Observable<ListRestaurantsI>{
    let url = this.url + id;
    return this.http.get<ListRestaurantsI>(url);
  }

  createNewRestaurant(form : ListRestaurantsI): Observable<ResponseI>{
    return this.http.post<ResponseI>(this.url , form);
  }
  
  updateRestaurant(form : ListRestaurantsI , id :number){
    let url = this.url + id;
    return this.http.put<ResponseI>(url,form)
  }

  deleteRestaurant(id : number) :Observable<ResponseI>{
    let url = this.url + id ;
    return this.http.delete<ResponseI>(url);
  }

}
