import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { HomizerService } from "../homizer/homizer.service";
import { HomizerItem } from "../homizer/service/homizer.models";

@Injectable({providedIn: 'root'})
export class DataStorageService {
    constructor(
        private http: HttpClient,
        private homizerService: HomizerService
    ) {}

    saveHomizerItem(item: HomizerItem) {
        console.log(item)
        this.http.post('https://localhost:5432' ,item).subscribe(response => {
            console.log(response)
        })
    }
    
}