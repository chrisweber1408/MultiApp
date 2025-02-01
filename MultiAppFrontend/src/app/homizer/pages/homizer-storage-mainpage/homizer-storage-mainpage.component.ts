import {Component, OnInit} from '@angular/core';
import {HomizerStorage} from "../../service/homizer.models";
import {HomizerDataService} from "../../service/homizer-data.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-homizer-storage-mainpage',
  standalone: false,
  templateUrl: './homizer-storage-mainpage.component.html',
  styleUrl: './homizer-storage-mainpage.component.css'
})
export class HomizerStorageMainpageComponent implements OnInit {
  homizerStorage: HomizerStorage;

  constructor(private dataStorage: HomizerDataService, private activatedRoute: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {
    this.loadHomizerStorages()
  }

  async loadHomizerStorages(): Promise<HomizerStorage[]> {
    const storages = await this.dataStorage.loadHomizerStorages()
      .catch(error => {
        if (error.response?.status === 403) {
          this.router.navigate(['/login']);
        }
      })
    this.homizerStorage = storages;
    return storages;
  }

}
