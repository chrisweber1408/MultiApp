import { Component } from '@angular/core';
import { RegisterData } from '../../service/user.models';
import { UserDataStorageService } from '../../service/user-data-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home-register',
  templateUrl: './home-register.component.html',
  styleUrl: './home-register.component.css'
})
export class HomeRegisterComponent {

  registerData: RegisterData;

  constructor(private loginDataStorageService: UserDataStorageService, private router: Router) {
    this.registerData = new RegisterData('','','');
  }

  onSendRegister(email: string, password: string, passwordRepeat: string) {
    this.registerData.email = email;
    this.registerData.password = password;
    this.registerData.passwordRepeat = passwordRepeat;
    this.loginDataStorageService.sendRegister(this.registerData)
    ;
  }

}
