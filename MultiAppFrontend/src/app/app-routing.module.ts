import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { HomizerItemMainpageComponent } from './homizer/pages/homizer-item-mainpage/homizer-item-mainpage.component';
import { HomizerStorageMainpageComponent } from './homizer/pages/homizer-storage-mainpage/homizer-storage-mainpage.component';
import { HomizerAddPageComponent } from './homizer/pages/homizer-add-page/homizer-add-page.component';
import { HomizerItemEditPageComponent } from './homizer/pages/homizer-item-edit-page/homizer-item-edit-page.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'homizer-item', component: HomizerItemMainpageComponent },
  { path: 'homizer-add-page', component: HomizerAddPageComponent },
  { path: 'homizer-item-edit-page/:id', component: HomizerItemEditPageComponent },
  { path: 'homizer-storage', component: HomizerStorageMainpageComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
