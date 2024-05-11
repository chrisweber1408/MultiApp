import { Component } from '@angular/core';
import { LoginData } from '../../service/user.models';
import { UserDataStorageService } from '../../service/user-data-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home-login',
  templateUrl: './home-login.component.html',
  styleUrl: './home-login.component.css'
})
export class HomeLoginComponent {

  loginDto: LoginData;

  constructor(private loginDataStorageService: UserDataStorageService, private router: Router) {
    this.loginDto = new LoginData('','');
  }

  onSendLogin(email: string, password: string) {    
    this.loginDto.email = email;
    this.loginDto.password = password;
    this.loginDataStorageService.sendLogin(this.loginDto)
    .then(data => localStorage.setItem("jwt", data.token))
    .then(() => this.router.navigate(['/home']))
  }

}
