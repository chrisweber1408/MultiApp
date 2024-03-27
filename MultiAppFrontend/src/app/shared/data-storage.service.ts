import { HttpClient, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { HomizerItem } from "../homizer/service/homizer.models";
import { Observable } from "rxjs";
import { environment } from "../../environments/environment.prod";
import axios, { Axios, AxiosResponse } from "axios";

@Injectable({providedIn: 'root'})
export class DataStorageService {

    private server: string = environment.API_BASE_URL

    constructor(
        private http: HttpClient
    ) {}

    saveHomizerItem(item: HomizerItem) {
        return axios.post('/api/homizer/storageItem/' ,item)
    }

    async loadHomizerItems() {
        return (await axios.get('/api/homizer/storageItem/')).data
    }

    loadHomizerItem(id: string) {
        return axios.get<HomizerItem>('/api/homizer/storageItem/' + id)
    }

    deleteHomizerItem(id: string) {
        return axios.delete('/api/homizer/storageItem/delete/' + id)
    }
    
}