import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-initial',
  templateUrl: './initial.component.html',
  styleUrls: ['./initial.component.scss']
})


export class InitialComponent{

  constructor(private router: Router) { }

  redirect(parameter : any){
      switch(parameter){
        case('restaurants'):
          this.router.navigate(['restaurant/list']);
          break;
        case('menu'):
          this.router.navigate(['menu/list']);
          break;
      }
  }
}
