import { Injectable } from "@angular/core";
import axios, { AxiosResponse } from "axios";
import { LoginData, RegisterData } from "./user.models";
import { LoginResponse } from "./login.models";

@Injectable({providedIn: 'root'})
export class UserDataStorageService {

    sendLogin(loginDto: LoginData) {
        return axios.post('/api/user/login', loginDto)
        .then((response:AxiosResponse<LoginResponse>) => response.data);
    }

    sendRegister(registerData: RegisterData) {
        return axios.post('/api/user/register', registerData);
    }

}
