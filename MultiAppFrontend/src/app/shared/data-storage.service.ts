import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { HomizerService } from "../homizer/homizer.service";
import { HomizerItem } from "../homizer/service/homizer.models";
import { Observable } from "rxjs";

@Injectable({providedIn: 'root'})
export class DataStorageService {
    constructor(
        private http: HttpClient,
        private homizerService: HomizerService
    ) {}

    saveHomizerItem(item: HomizerItem) {
        this.http.post('http://localhost:8080/homizer/storageItem' ,item).subscribe( response => console.log(response))
    }

    loadHomizerItems(): Observable<HomizerItem>{
        return this.http.get<HomizerItem>('http://localhost:8080/homizer/storageItem')
    }
    
}