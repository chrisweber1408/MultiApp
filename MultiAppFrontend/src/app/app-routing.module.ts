import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './home/pages/home-page/home.component';
import {HomizerItemMainpageComponent} from './homizer/pages/homizer-item-mainpage/homizer-item-mainpage.component';
import {HomizerItemAddPageComponent} from './homizer/pages/homizer-item-add-page/homizer-item-add-page.component';
import {HomizerItemEditPageComponent} from './homizer/pages/homizer-item-edit-page/homizer-item-edit-page.component';
import {HomeLoginComponent} from './home/pages/login-page/home-login.component';
import {RegisterPageComponent} from './home/pages/register-page/register-page.component';
import {
  HomizerStorageMainpageComponent
} from "./homizer/pages/homizer-storage-mainpage/homizer-storage-mainpage.component";
import {
  HomizerStorageAddPageComponent
} from "./homizer/pages/homizer-storage-add-page/homizer-storage-add-page.component";
import {
  HomizerStorageEditPageComponent
} from "./homizer/pages/homizer-storage-edit-page/homizer-storage-edit-page.component";
import {UserPageComponent} from "./home/pages/user-page/user-page.component";

const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'user', component: UserPageComponent},
  {path: 'login', component: HomeLoginComponent},
  {path: 'register', component: RegisterPageComponent},
  {path: 'homizer-item', component: HomizerItemMainpageComponent},
  {path: 'homizer-item-add-page', component: HomizerItemAddPageComponent},
  {path: 'homizer-item-edit-page/:id', component: HomizerItemEditPageComponent},
  {path: 'homizer-storage', component: HomizerStorageMainpageComponent},
  {path: 'homizer-storage-add-page', component: HomizerStorageAddPageComponent},
  {path: 'homizer-storage-edit-page/:id', component: HomizerStorageEditPageComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
