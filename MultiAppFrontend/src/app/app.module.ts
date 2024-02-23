import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomizerComponent } from './homizer/homizer.component';
import { HomeComponent } from './home/home.component';
import { HomizerHeaderComponent } from './homizer/homizer-header/homizer-header.component';
import { HomizerItemMainpageComponent } from './homizer/homizer-item-mainpage/homizer-item-mainpage.component';
import { FormsModule } from '@angular/forms';
import { HomizerService } from './homizer/homizer.service';
import { HomizerStorageMainpageComponent } from './homizer/homizer-storage-mainpage/homizer-storage-mainpage.component';
import { HomizerAddButtonComponent } from './homizer/homizer-add-button/homizer-add-button.component';
import { HomizerAddPageComponent } from './homizer/homizer-add-page/homizer-add-page.component';

@NgModule({
  declarations: [
    AppComponent,
    HomizerComponent,
    HomeComponent,
    HomizerHeaderComponent,
    HomizerItemMainpageComponent,
    HomizerStorageMainpageComponent,
    HomizerAddButtonComponent,
    HomizerAddPageComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [
    HomizerService
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
