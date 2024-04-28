import { Injectable } from "@angular/core";
import axios, { AxiosResponse } from "axios";
import { LoginData } from "./login.models";

@Injectable({providedIn: 'root'})
export class LoginDataStorageService {

    sendLogin(loginData: LoginData) {
        console.log(loginData)
        return axios.post('/api/login', loginData);
    }

}