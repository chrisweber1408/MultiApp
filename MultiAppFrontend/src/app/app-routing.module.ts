import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomizerComponent } from './homizer/homizer.component';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { HomizerItemMainpageComponent } from './homizer/homizer-item-mainpage/homizer-item-mainpage.component';
import { HomizerStorageMainpageComponent } from './homizer/homizer-storage-mainpage/homizer-storage-mainpage.component';
import { HomizerAddPageComponent } from './homizer/homizer-add-page/homizer-add-page.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'homizer-item', component: HomizerItemMainpageComponent },
  { path: 'homizer-add-page', component: HomizerAddPageComponent },
  { path: 'homizer-storage', component: HomizerStorageMainpageComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
