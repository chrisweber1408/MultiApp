import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {provideHttpClient, withInterceptorsFromDi} from '@angular/common/http';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HomizerComponent} from './homizer/homizer.component';
import {HomeComponent} from './home/pages/home-page/home.component';
import {HomizerItemMainpageComponent} from './homizer/pages/homizer-item-mainpage/homizer-item-mainpage.component';
import {FormsModule} from '@angular/forms';
import {HomizerService} from './homizer/homizer.service';
import {HomizerAddButtonComponent} from './homizer/components/homizer-add-button/homizer-add-button.component';
import {HomizerAddPageComponent} from './homizer/pages/homizer-add-page/homizer-add-page.component';
import {HomizerChoicePannelComponent} from './homizer/components/homizer-choice-pannel/homizer-choice-pannel.component';
import {HomizerItemEditPageComponent} from './homizer/pages/homizer-item-edit-page/homizer-item-edit-page.component';
import {HomeHeaderComponent} from './home/components/app-header/app-header.component';
import {HomeLoginComponent} from './home/pages/login-page/home-login.component';
import {HomeRegisterComponent} from './home/pages/home-register/home-register.component';
import {CookieService} from 'ngx-cookie-service';
import {provideAnimationsAsync} from '@angular/platform-browser/animations/async';

@NgModule({
  declarations: [
    AppComponent,
    HomizerComponent,
    HomeComponent,
    HomizerChoicePannelComponent,
    HomizerItemMainpageComponent,
    HomizerAddButtonComponent,
    HomizerAddPageComponent,
    HomizerItemEditPageComponent,
    HomeHeaderComponent,
    HomeLoginComponent,
    HomeRegisterComponent,
  ],
  bootstrap: [
    AppComponent
  ], imports: [BrowserModule,
    AppRoutingModule,
    FormsModule],
  providers: [
    HomizerService,
    CookieService,
    provideHttpClient(withInterceptorsFromDi()),
    provideAnimationsAsync()
  ]
})
export class AppModule {
}
