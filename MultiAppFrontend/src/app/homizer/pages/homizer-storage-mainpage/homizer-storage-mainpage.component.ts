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
  homizerStorages: HomizerStorageDto[] = [];
  showNoStoragesMessage = false;
  filterText: string = '';
  filteredHomizerStorages: HomizerStorageDto[] = [];
  isLoading: boolean = true;

  constructor(private dataStorage: HomizerDataService, private router: Router) {
  }

  ngOnInit() {
    this.loadHomizerStorages().then(() => {
      this.isLoading = false;
    });
  }

  async loadHomizerStorages(): Promise<void> {
    try {
      this.homizerStorages = await this.dataStorage.loadHomizerStorages();
      console.log(this.homizerStorages.length)
      if (!this.homizerStorages || this.homizerStorages.length === 0) {
        this.showNoStoragesMessage = true;
        this.filteredHomizerStorages = [];
        return;
      }
      this.filteredHomizerStorages = [...this.homizerStorages];
    } catch (error) {
      if (error?.response?.status === 403) {
        await this.router.navigate(['/login']);
      } else {
        console.error('Error while loading storages: ', error);
      }
    } finally {
      this.isLoading = false;
    }
  }

  applyFilter(): void {
    this.filteredHomizerStorages = this.homizerStorages.filter(storage =>
      storage.name.toLowerCase().includes(this.filterText.toLowerCase()));
    this.showNoStoragesMessage = this.filteredHomizerStorages.length === 0;
  }

}

