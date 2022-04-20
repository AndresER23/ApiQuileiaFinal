import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute} from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ApiServiceM } from 'src/app/services/menuServices/api.service';
import { menuI } from 'src/app/models/menuModels/menuInterface';
import { ApiService } from 'src/app/services/resturantServices/api.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.scss']
})
export class EditComponent implements OnInit {
  
  public idMenu : number = 0
  public recoveredMenu : any = []
  public recoveredRestaurants: any = []
  public associatedRestuarant : any = []

  constructor(private router: Router, private activatedRoute : ActivatedRoute, private api : ApiServiceM ,
    private apiRes: ApiService, private _location : Location) {
    this.activatedRoute.params.subscribe(data =>{
      this.idMenu = data['id']
    })
   }

  ngOnInit(): void {
    this.api.getMenuById(this.idMenu).subscribe((data)=>{
      this.recoveredMenu= data
    })
    this.apiRes.getAllRestaurants().subscribe((data)=>{
        this.recoveredRestaurants= data
    })
  }

  editMenu = new FormGroup({
    idRestaurant: new FormControl('', Validators.required),
    typeMenu : new FormControl('', Validators.required),
    menuName : new FormControl('', Validators.required),
    price : new FormControl('', Validators.required)
  });

  getValuesForm(form : menuI){
    this.api.updateMenu(form, this.idMenu).subscribe(data =>{
        console.log("restaurante actualizado "  + this.recoveredMenu)
    })
    
    this._location.back()
  }

  deleteMenu(){

  }
  }
