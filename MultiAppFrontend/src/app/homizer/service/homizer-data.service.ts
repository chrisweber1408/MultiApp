import {Injectable} from "@angular/core";
import {HomizerItem, HomizerStorage} from "./homizer.models";
import axios from "axios";
import {CookieService} from "ngx-cookie-service";

@Injectable({providedIn: 'root'})
export class DataService {

  constructor(private cookieService: CookieService) {

  }

  requestConfig() {
    return {
      headers: {
        Authorization: `Bearer ${this.cookieService.get("jwt")}`
      }
    }
  }

  // Homizer-Item

  saveHomizerItem(item: HomizerItem) {
    return axios.post('/api/homizer/item/', item, this.requestConfig())
  }

  async loadHomizerItems() {
    return (await axios.get('/api/homizer/item/', this.requestConfig())).data
  }

  loadHomizerItem(id: string) {
    return axios.get<HomizerItem>('/api/homizer/item/' + id, this.requestConfig())
  }

  deleteHomizerItem(id: string) {
    return axios.delete('/api/homizer/item/delete/' + id, this.requestConfig())
  }

  // Homizer-Storage

  saveHomizerStorage(storage: HomizerStorage) {
    return axios.post('/api/homizer/storage/', storage, this.requestConfig())
  }

  async loadHomizerStorages() {
    return (await axios.get('/api/homizer/storage/', this.requestConfig())).data
  }

  loadHomizerStorage(id: string) {
    return axios.get('/api/homizer/storage/' + id, this.requestConfig())
  }

  deleteHomizerStorage(id: string) {
    return axios.delete('/api/homizer/storage/delete' + id, this.requestConfig())
  }
}
