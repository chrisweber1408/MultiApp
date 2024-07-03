import { Component } from '@angular/core';
import { LoginData } from '../../service/user.models';
import { UserDataStorageService } from '../../service/user-data-storage.service';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-home-login',
  templateUrl: './home-login.component.html',
  styleUrl: './home-login.component.css'
})
export class HomeLoginComponent {

  loginDto: LoginData;

  constructor(private loginDataStorageService: UserDataStorageService, private router: Router, private cookieService: CookieService) {
    this.loginDto = new LoginData('','');
  }

  onSendLogin(email: string, password: string) {    
    this.loginDto.email = email;
    this.loginDto.password = password;
    this.loginDataStorageService.sendLogin(this.loginDto)
    .then(data => this.cookieService.set("jwt", data.token))
    .then(() => this.router.navigate(['/home']))
  }

}
