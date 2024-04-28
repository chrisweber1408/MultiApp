import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomizerComponent } from './homizer/homizer.component';
import { HomeComponent } from './home/pages/home-page/home.component';
import { HomizerHeaderComponent } from './homizer/components/homizer-header/homizer-header.component';
import { HomizerItemMainpageComponent } from './homizer/pages/homizer-item-mainpage/homizer-item-mainpage.component';
import { FormsModule } from '@angular/forms';
import { HomizerService } from './homizer/homizer.service';
import { HomizerStorageMainpageComponent } from './homizer/pages/homizer-storage-mainpage/homizer-storage-mainpage.component';
import { HomizerAddButtonComponent } from './homizer/components/homizer-add-button/homizer-add-button.component';
import { HomizerAddPageComponent } from './homizer/pages/homizer-add-page/homizer-add-page.component';
import { HomizerChoicePannelComponent } from './homizer/components/homizer-choice-pannel/homizer-choice-pannel.component';
import { HomizerItemEditPageComponent } from './homizer/pages/homizer-item-edit-page/homizer-item-edit-page.component';
import { HomeHeaderComponent } from './home/components/home-header/home-header.component';
import { HomeLoginComponent } from './home/pages/login-page/home-login.component';

@NgModule({
  declarations: [
    AppComponent,
    HomizerComponent,
    HomeComponent,
    HomizerHeaderComponent,
    HomizerChoicePannelComponent,
    HomizerItemMainpageComponent,
    HomizerStorageMainpageComponent,
    HomizerAddButtonComponent,
    HomizerAddPageComponent,
    HomizerItemEditPageComponent,
    HomeHeaderComponent,
    HomeLoginComponent,
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
