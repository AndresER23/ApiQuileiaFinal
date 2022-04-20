import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { menuI } from 'src/app/models/menuModels/menuInterface';
import { ApiServiceM } from 'src/app/services/menuServices/api.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListOfMenu implements OnInit {
  
  public recoveredMenus : menuI[] = [];

  constructor(private api : ApiServiceM, private route : Router) { }

  ngOnInit(): void {
    this.api.findAllMenus().subscribe((data)=>{
        this.recoveredMenus= data;
    })
  }
  
  editMenu(id: number){
    this.route.navigate([`menu/edit/${id}`])
  }

  createMenu(){
    this.route.navigate(['menu/create'])
  }

}
