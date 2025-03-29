import {Injectable} from "@angular/core";
import {HomizerItemDto, HomizerStorageDto} from "./homizer.models";
import axios, {AxiosResponse} from "axios";
import {CookieService} from "ngx-cookie-service";

@Injectable({providedIn: 'root'})
export class HomizerDataService {

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

  saveHomizerItem(item: HomizerItemDto) {
    return axios.post('/api/homizer/item/', item, this.requestConfig())
  }

  async loadHomizerItems() {
    return (await axios.get('/api/homizer/item/', this.requestConfig())).data
  }

  loadHomizerItem(id: string) {
    return axios.get<HomizerItemDto>('/api/homizer/item/' + id, this.requestConfig())
  }

  deleteHomizerItem(id: string) {
    return axios.delete('/api/homizer/item/delete/' + id, this.requestConfig())
  }

  async loadHomizerItemsForStorage(id: string) {
    return (await axios.get('/api/homizer/items/' + id, this.requestConfig())).data
  }

  // Homizer-Storage

  saveHomizerStorage(storage: HomizerStorageDto) {
    return axios.post('/api/homizer/storage/', storage, this.requestConfig())
  }

  async loadHomizerStorages() {
    return (await axios.get('/api/homizer/storage/', this.requestConfig())).data
  }

  loadHomizerStorage(id: string) {
    return axios.get('/api/homizer/storage/' + id, this.requestConfig())
  }

  deleteHomizerStorage(id: string) {
    return axios.delete('/api/homizer/storage/delete/' + id, this.requestConfig())
  }
}
