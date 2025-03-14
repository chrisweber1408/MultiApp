import {Component, OnInit} from '@angular/core';
import {HomizerStorageDto} from "../../service/homizer.models";
import {HomizerDataService} from "../../service/homizer-data.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-homizer-storage-mainpage',
  standalone: false,
  templateUrl: './homizer-storage-mainpage.component.html',
  styleUrl: './homizer-storage-mainpage.component.css'
})
export class HomizerStorageMainpageComponent implements OnInit {
  homizerStorageDto: HomizerStorageDto[];
  showNoStoragesMessage = false;

  constructor(private dataStorage: HomizerDataService, private router: Router) {
  }

  ngOnInit() {
    this.loadHomizerStorages()
    if (!this.homizerStorageDto) {
      setTimeout(() => {
        this.showNoStoragesMessage = true;
      }, 250);
    }
  }

  async loadHomizerStorages(): Promise<HomizerStorageDto[]> {
    const storages = await this.dataStorage.loadHomizerStorages()
      .catch(error => {
        if (error.response?.status === 403) {
          this.router.navigate(['/login']);
        }
      })
    this.homizerStorageDto = storages;
    return storages;
  }

}
