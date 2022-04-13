import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {InitialComponent} from './views/initial/initial.component';
import{CreateComponent} from './views/menu/create/create.component';
import { EditComponent } from './views/menu/edit/edit.component';
import { ListOfMenu } from './views/menu/list/list.component';
import {CreateRestaurant} from './views/restaurants/createRestaurant/create.component'
import { EditRestaurant } from './views/restaurants/edit/edit.component';
import { ListOfRestaurants } from './views/restaurants/list/list.component';

const routes: Routes = [
  {path:'', redirectTo:'initialPage', pathMatch:'full'},
  {path:'initialPage', component:InitialComponent},
  {path:'menu/create', component:CreateComponent},
  {path:'menu/edit', component:EditComponent },
  {path:'restaurant/create', component:CreateRestaurant},
  {path:'restaurant/edit', component:EditRestaurant},
  {path:'restaurant/list', component:ListOfRestaurants},
  {path:'menu/list' , component:ListOfMenu}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingModule = [InitialComponent, CreateComponent, EditComponent, CreateRestaurant, EditRestaurant, ListOfRestaurants, ListOfMenu]
