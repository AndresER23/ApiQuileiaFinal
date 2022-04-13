import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule, routingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './templates/header/header.component';
import { FooterComponent } from './templates/footer/footer.component';
import { InitialComponent } from './views/initial/initial.component';
import { ListOfRestaurants } from './views/restaurants/list/list.component';
import { EditRestaurant } from './views/restaurants/edit/edit.component';
import { CreateComponent } from './views/menu/create/create.component';
import { RouterModule } from '@angular/router';
import { ListOfMenu } from './views/menu/list/list.component';

import {HttpClientModule} from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    routingModule,
    ListOfMenu
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
