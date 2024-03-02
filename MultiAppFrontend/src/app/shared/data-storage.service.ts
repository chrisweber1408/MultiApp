import { HttpClient, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { HomizerItem } from "../homizer/service/homizer.models";
import { Observable, catchError, throwError } from "rxjs";

@Injectable({providedIn: 'root'})
export class DataStorageService {
    constructor(
        private http: HttpClient,
    ) {}

    saveHomizerItem(item: HomizerItem): Observable<HttpResponse<any>> {
        return this.http.post('http://localhost:8080/homizer/storageItem' ,item, {observe: 'response'})
    }

    loadHomizerItems(): Observable<HomizerItem>{
        return this.http.get<HomizerItem>('http://localhost:8080/homizer/storageItem')
    }

    loadHomizerItem(id: string): Observable<HomizerItem> {
        return this.http.get<HomizerItem>('http://localhost:8080/homizer/storageItem/' + id)
    }

    deleteHomizerItem(id: string): Observable<HttpResponse<any>> {
        return this.http.delete('http://localhost:8080/homizer/storageItem/delete/' + id, {observe: 'response'})
    }
    
}