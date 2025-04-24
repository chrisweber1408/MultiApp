import {Injectable} from "@angular/core";
import axios, {AxiosResponse} from "axios";
import {LoginData, RegisterData} from "./user.models";
import {LoginResponse} from "./login.models";
import {CookieService} from "ngx-cookie-service";

@Injectable({providedIn: 'root'})
export class UserDataStorageService {

  constructor(private cookieService: CookieService) {
  }

  sendLogin(loginDto: LoginData) {
    return axios.post('/api/user/login', loginDto)
      .then((response: AxiosResponse<LoginResponse>) => response.data);
  }

  sendRegister(registerData: RegisterData) {
    return axios.post('/api/user/register', registerData);
  }


  // User

  async loadUserInfos() {
    return (await axios.get('/api/home/user/infos', this.requestConfig())).data
  }


  requestConfig() {
    return {
      headers: {
        Authorization: `Bearer ${this.cookieService.get("jwt")}`
      }
    }
  }

}
