import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './home/pages/home-page/home.component';
import {HomizerItemMainpageComponent} from './homizer/pages/homizer-item-mainpage/homizer-item-mainpage.component';
import {HomizerItemAddPageComponent} from './homizer/pages/homizer-item-add-page/homizer-item-add-page.component';
import {HomizerItemEditPageComponent} from './homizer/pages/homizer-item-edit-page/homizer-item-edit-page.component';
import {HomeLoginComponent} from './home/pages/login-page/home-login.component';
import {HomeRegisterComponent} from './home/pages/home-register/home-register.component';
import {
  HomizerStorageMainpageComponent
} from "./homizer/pages/homizer-storage-mainpage/homizer-storage-mainpage.component";
import {
  HomizerStorageAddPageComponent
} from "./homizer/pages/homizer-storage-add-page/homizer-storage-add-page.component";

const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'login', component: HomeLoginComponent},
  {path: 'register', component: HomeRegisterComponent},
  {path: 'homizer-item', component: HomizerItemMainpageComponent},
  {path: 'homizer-item-add-page', component: HomizerItemAddPageComponent},
  {path: 'homizer-item-edit-page/:id', component: HomizerItemEditPageComponent},
  {path: 'homizer-storage', component: HomizerStorageMainpageComponent},
  {path: 'homizer-storage-add-page', component: HomizerStorageAddPageComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
