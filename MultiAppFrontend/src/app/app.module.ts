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
import {HomizerAddPageComponent} from './homizer/pages/homizer-add-page/homizer-add-page.component';
import {HomizerItemEditPageComponent} from './homizer/pages/homizer-item-edit-page/homizer-item-edit-page.component';
import {HomeHeaderComponent} from './home/components/app-header/app-header.component';
import {HomeLoginComponent} from './home/pages/login-page/home-login.component';
import {HomeRegisterComponent} from './home/pages/home-register/home-register.component';
import {CookieService} from 'ngx-cookie-service';
import {provideAnimationsAsync} from '@angular/platform-browser/animations/async';
import {NavigationItemComponent} from './homizer/components/navigation-item/navigation-item.component';
import {NavigationBarComponent} from './homizer/components/navigation-bar/navigation-bar.component';

@NgModule({
  declarations: [
    AppComponent,
    HomizerComponent,
    HomeComponent,
    HomizerItemMainpageComponent,
    HomizerAddPageComponent,
    HomizerItemEditPageComponent,
    HomeHeaderComponent,
    HomeLoginComponent,
    HomeRegisterComponent,
    NavigationBarComponent,

  ],
  bootstrap: [
    AppComponent
  ], imports: [BrowserModule,
    AppRoutingModule,
    FormsModule, NavigationItemComponent],
  providers: [
    HomizerService,
    CookieService,
    provideHttpClient(withInterceptorsFromDi()),
    provideAnimationsAsync()
  ]
})
export class AppModule {
}
