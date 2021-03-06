import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {InitialComponent} from './views/initial/initial.component';
import{CreateComponent} from './views/menu/create/create.component';
import { EditComponent } from './views/menu/edit/edit.component';
import { ListOfMenu } from './views/menu/list/list.component';
import {CreateRestaurant} from './views/restaurants/create/create.component'
import { EditRestaurant } from './views/restaurants/edit/edit.component';
import { ListOfRestaurants } from './views/restaurants/list/list.component';
import {ConsultComponent} from './views/restaurants/consult/consult.component'; 

const routes: Routes = [
  {path:'', redirectTo:'initialPage', pathMatch:'full'},
  {path:'initialPage', component:InitialComponent},
  {path:'menu/create', component:CreateComponent},
  {path:'menu/edit/:id', component:EditComponent },
  {path:'restaurant/create', component:CreateRestaurant},
  {path:'restaurant/edit/:id', component:EditRestaurant},
  {path:'restaurant/list', component:ListOfRestaurants},
  {path:'menu/list' , component:ListOfMenu},
  {path:'restaurant/consult/:id', component:ConsultComponent},
  {path:'menu/consult/:id' , component: ConsultComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingModule = [InitialComponent, CreateComponent, EditComponent, CreateRestaurant, EditRestaurant, ListOfRestaurants, ListOfMenu]
