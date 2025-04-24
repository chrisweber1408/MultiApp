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
import {HomizerItemAddPageComponent} from './homizer/pages/homizer-item-add-page/homizer-item-add-page.component';
import {HomizerItemEditPageComponent} from './homizer/pages/homizer-item-edit-page/homizer-item-edit-page.component';
import {HomeHeaderComponent} from './home/components/app-header/app-header.component';
import {HomeLoginComponent} from './home/pages/login-page/home-login.component';
import {RegisterPageComponent} from './home/pages/register-page/register-page.component';
import {CookieService} from 'ngx-cookie-service';
import {provideAnimationsAsync} from '@angular/platform-browser/animations/async';
import {NavigationItemComponent} from './home/components/navigation-item/navigation-item.component';
import {NavigationBarComponent} from './home/components/navigation-bar/navigation-bar.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatButtonToggleModule} from "@angular/material/button-toggle";
import {MatRadioModule} from "@angular/material/radio";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatInputModule} from "@angular/material/input";

import {
  HomizerStorageMainpageComponent
} from './homizer/pages/homizer-storage-mainpage/homizer-storage-mainpage.component';
import {
  HomizerStorageAddPageComponent
} from './homizer/pages/homizer-storage-add-page/homizer-storage-add-page.component';
import {MatButton, MatIconButton} from "@angular/material/button";
import {MatOption, MatSelect} from "@angular/material/select";
import {MatIcon} from "@angular/material/icon";
import {
  HomizerStorageEditPageComponent
} from './homizer/pages/homizer-storage-edit-page/homizer-storage-edit-page.component';
import {ImageViewDialogComponent} from './homizer/components/image-view-dialog/image-view-dialog.component';
import {MatProgressSpinner} from "@angular/material/progress-spinner";
import {UserPageComponent} from './home/pages/user-page/user-page.component';

@NgModule({
  declarations: [
    AppComponent,
    HomizerComponent,
    HomeComponent,
    HomizerItemMainpageComponent,
    HomizerItemAddPageComponent,
    HomizerItemEditPageComponent,
    HomeHeaderComponent,
    HomeLoginComponent,
    RegisterPageComponent,
    NavigationBarComponent,
    HomizerStorageMainpageComponent,
    HomizerStorageAddPageComponent,
    HomizerStorageEditPageComponent,
    ImageViewDialogComponent,
    UserPageComponent,

  ],
  bootstrap: [
    AppComponent
  ], imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    NavigationItemComponent,
    MatFormFieldModule,
    MatButtonToggleModule,
    MatRadioModule,
    MatDatepickerModule,
    MatInputModule,
    MatButton,
    MatSelect,
    MatOption,
    MatIcon,
    MatIconButton,
    MatProgressSpinner
  ],
  providers: [
    HomizerService,
    CookieService,
    provideHttpClient(withInterceptorsFromDi()),
    provideAnimationsAsync()
  ]
})
export class AppModule {
}
