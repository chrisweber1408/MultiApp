import { Injectable } from "@angular/core";
import { HomizerItem } from "./homizer.models";
import axios from "axios";

@Injectable({providedIn: 'root'})
export class DataStorageService {

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