import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomizerComponent } from './homizer/homizer.component';
import { HomeComponent } from './home/home.component';
import { HomizerHeaderComponent } from './homizer/homizer-header/homizer-header.component';
import { HomizerMainpageComponent } from './homizer/homizer-mainpage/homizer-mainpage.component';
import { FormsModule } from '@angular/forms';
import { HomizerService } from './homizer/homizer.service';

@NgModule({
  declarations: [
    AppComponent,
    HomizerComponent,
    HomeComponent,
    HomizerHeaderComponent,
    HomizerMainpageComponent
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
