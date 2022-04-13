import { NgIf } from "@angular/common"

export interface ListRestaurantsI{
    idRestaurant: number
    commercialName: String
    socialReason : String
    typeOfRestaurant : number
    locationCity : String
    openingTime : number
    closingTime : number
}