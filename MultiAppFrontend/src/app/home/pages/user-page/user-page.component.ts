import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {UserInfosDto} from "../../../homizer/service/homizer.models";
import {UserDataStorageService} from "../../service/user-data-storage.service";
import {CookieService} from "ngx-cookie-service";
import {MatDialog} from "@angular/material/dialog";
import {ConfirmDialogComponent} from "../../components/confirm-dialog/confirm-dialog.component";

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

  constructor(
    private userDataStorageService: UserDataStorageService,
    private router: Router,
    private cookieService: CookieService,
    private dialog: MatDialog
  ) {
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
    this.router.navigate(['/login']).then(() =>
      this.cookieService.set("jwt", "")
    );
  }

  onDeleteUser() {
    const dialogRef1 = this.dialog.open(ConfirmDialogComponent, {
      data: {
        title: 'Confirm Deletion',
        message: 'Are you sure you want to delete your account?'
      }
    });

    dialogRef1.afterClosed().subscribe(result1 => {
      if (!result1) return;

      const dialogRef2 = this.dialog.open(ConfirmDialogComponent, {
        data: {
          title: 'Really Delete?',
          message: 'This action is irreversible. Do you want to proceed?'
        }
      });

      dialogRef2.afterClosed().subscribe(result2 => {
        if (result2) {
          this.userDataStorageService.deleteUser().then(() => {
            this.router.navigate(['/login']).then(() =>
              this.cookieService.set("jwt", "")
            );
          })
        }
      });
    });
  }

}
