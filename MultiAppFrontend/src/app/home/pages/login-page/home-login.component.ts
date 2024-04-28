import { Component } from '@angular/core';
import { LoginData } from '../../service/login.models';
import { LoginDataStorageService } from '../../service/login-data-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home-login',
  templateUrl: './home-login.component.html',
  styleUrl: './home-login.component.css'
})
export class HomeLoginComponent {

  loginData: LoginData;

  constructor(private loginDataStorageService: LoginDataStorageService, private router: Router) {
    this.loginData = new LoginData('','');
  }

  onSendLogin(email: string, password: string) {
    console.log(email);
    this.loginData.email = email;
    this.loginData.password = password;
    this.loginDataStorageService.sendLogin(this.loginData).then(
      () => {
        this.router.navigate(['/home']);
      }
    );
  }

}
