import {Component, OnInit} from '@angular/core';
import {HomizerStorageDto} from "../../service/homizer.models";
import {HomizerDataService} from "../../service/homizer-data.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-homizer-storage-mainpage',
  standalone: false,
  templateUrl: './homizer-storage-mainpage.component.html',
  styleUrls: [
    './homizer-storage-mainpage.component.css',
    '../../homizer-styles.css'
  ]
})
export class HomizerStorageMainpageComponent implements OnInit {
  homizerStorages: HomizerStorageDto[];
  showNoStoragesMessage = false;
  filterText: string;
  filteredHomizerStorages: HomizerStorageDto[];

  constructor(private dataStorage: HomizerDataService, private router: Router) {
  }

  ngOnInit() {
    this.loadHomizerStorages()
    if (!this.homizerStorages) {
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
    this.homizerStorages = storages;
    this.filteredHomizerStorages = storages;
    return storages;
  }

  applyFilter(): void {
    this.filteredHomizerStorages = this.homizerStorages.filter(storage =>
      storage.name.toLowerCase().includes(this.filterText.toLowerCase()))
  }

}
