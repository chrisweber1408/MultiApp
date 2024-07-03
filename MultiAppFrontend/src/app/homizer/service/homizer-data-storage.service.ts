import { Injectable } from "@angular/core";
import { HomizerItem } from "./homizer.models";
import axios from "axios";
import { CookieService } from "ngx-cookie-service";

@Injectable({providedIn: 'root'})
export class DataStorageService {

    constructor(private cookieService: CookieService){

    }

    requestConfig(){
        return {
            headers: {
                Authorization: `Bearer ${this.cookieService.get("jwt")}`
            }
        }
    }

    saveHomizerItem(item: HomizerItem) {
        return axios.post('/api/homizer/storageItem/' ,item, this.requestConfig())
    }

    async loadHomizerItems() {
        return (await axios.get('/api/homizer/storageItem/', this.requestConfig())).data
    }

    loadHomizerItem(id: string) {
        return axios.get<HomizerItem>('/api/homizer/storageItem/' + id, this.requestConfig())
    }

    deleteHomizerItem(id: string) {
        return axios.delete('/api/homizer/storageItem/delete/' + id, this.requestConfig())
    }
    
}