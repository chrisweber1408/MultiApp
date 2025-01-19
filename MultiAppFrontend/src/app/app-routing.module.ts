import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/pages/home-page/home.component';
import { HomizerItemMainpageComponent } from './homizer/pages/homizer-item-mainpage/homizer-item-mainpage.component';
import { HomizerAddPageComponent } from './homizer/pages/homizer-add-page/homizer-add-page.component';
import { HomizerItemEditPageComponent } from './homizer/pages/homizer-item-edit-page/homizer-item-edit-page.component';
import { HomeLoginComponent } from './home/pages/login-page/home-login.component';
import { HomeRegisterComponent } from './home/pages/home-register/home-register.component';
import {
  HomizerStorageMainpageComponent
} from "./homizer/pages/homizer-storage-mainpage/homizer-storage-mainpage.component";

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: HomeLoginComponent },
  { path: 'register', component: HomeRegisterComponent },
  { path: 'homizer-item', component: HomizerItemMainpageComponent },
  { path: 'homizer-storage', component: HomizerStorageMainpageComponent },
  { path: 'homizer-add-page', component: HomizerAddPageComponent },
  { path: 'homizer-item-edit-page/:id', component: HomizerItemEditPageComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
