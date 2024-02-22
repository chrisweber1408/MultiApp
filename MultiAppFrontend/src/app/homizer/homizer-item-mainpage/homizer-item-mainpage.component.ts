import { Component, OnInit } from '@angular/core';
import { HomizerItem } from '../service/homizer.models';
import { DataStorageService } from '../../shared/data-storage.service';

@Component({
  selector: 'app-homizer-mainpage',
  templateUrl: './homizer-item-mainpage.component.html',
  styleUrls: ['./homizer-item-mainpage.component.css']
})
export class HomizerItemMainpageComponent implements OnInit {
  homizerItems: HomizerItem;
  constructor(private dataStorageService: DataStorageService) {

  }

  ngOnInit(): void {
    this.loadHomizerItems()
  }

  onSaveHomizerItem(name: string, description?: string, image?: string, number?: number){
    console.log('test')
    const item: HomizerItem = new HomizerItem(name, description, image, number)
    this.dataStorageService.saveHomizerItem(item)
  }

  loadHomizerItems(): void {
    this.dataStorageService.loadHomizerItems().subscribe((items: HomizerItem) => {
      this.homizerItems = items;
    });
  }
  
}
