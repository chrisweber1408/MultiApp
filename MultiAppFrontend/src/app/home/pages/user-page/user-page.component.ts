import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {UserInfosDto} from "../../../homizer/service/homizer.models";
import {UserDataStorageService} from "../../service/user-data-storage.service";
import {CookieService} from "ngx-cookie-service";

@Component({
  selector: 'app-user-page',
  standalone: false,

  templateUrl: './user-page.component.html',
  styleUrl: './user-page.component.css'
})
export class UserPageComponent implements OnInit {

  userInfos: UserInfosDto;
  email: string;
  itemCount: number;
  storageCount: number;
  isLoading: boolean = true;

  constructor(private userDataStorageService: UserDataStorageService, private router: Router, private cookieService: CookieService) {
  }

  ngOnInit() {
    this.loadUserInfos().then(() => {
      this.isLoading = false;
    })
  }

  async loadUserInfos(): Promise<void> {
    try {
      this.userInfos = await this.userDataStorageService.loadUserInfos();
      if (this.userInfos) {
        this.email = this.userInfos.email;
        this.itemCount = this.userInfos.itemCount;
        this.storageCount = this.userInfos.storageCount;
        return;
      }
    } catch (error) {
      if (error.response?.status === 403) {
        await this.router.navigate(['/login']);
      } else {
        console.error('Error while loading infos: ', error);
      }
    } finally {
      this.isLoading = false;
    }
  }

  onLogout(): void {
    this.router.navigate(['/login']).then(r =>
      this.cookieService.set("jwt", "")
    );
  }

}
