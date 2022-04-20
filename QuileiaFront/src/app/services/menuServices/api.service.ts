import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {ResponseI} from '../../models/restaurantModels/response.interface';
import { HttpClient, HttpHeaders} from '@angular/common/http'
import { menuI } from 'src/app/models/menuModels/menuInterface';
import { environment} from 'src/environments/environment'


@Injectable({
  providedIn: 'root'
})

export class ApiServiceM {
  
  url = environment.urlMenus;


  constructor(private http : HttpClient) { }

  createNewMenu(form : menuI): Observable<menuI>{
    return this.http.post<menuI>(this.url , form);
  }

  findAllMenus(){
    return this.http.get<menuI[]>(this.url);
  }

  getMenuById(id : number){
    let url = this.url + id;
    return this.http.get(url);
  }

  updateMenu(form : menuI , id :number){
    let url = this.url + id;
    return this.http.put<ResponseI>(url,form)
  }
}
