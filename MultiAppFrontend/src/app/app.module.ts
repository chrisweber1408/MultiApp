import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomizerComponent } from './homizer/homizer.component';
import { HomeComponent } from './home/home.component';
import { HomizerHeaderComponent } from './homizer/homizer-header/homizer-header.component';
import { HomizerItemMainpageComponent } from './homizer/homizer-item-mainpage/homizer-item-mainpage.component';
import { HomizerItemListComponent } from './homizer/homizer-item-list/homizer-item-list.component';

@NgModule({
  declarations: [
    AppComponent,
    HomizerComponent,
    HomeComponent,
    HomizerHeaderComponent,
    HomizerItemMainpageComponent,
    HomizerItemListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
