import { Component } from '@angular/core';
import { HomizerItem } from '../service/homizer.models';
import { DataStorageService } from '../../shared/data-storage.service';

@Component({
  selector: 'app-homizer-mainpage',
  templateUrl: './homizer-item-mainpage.component.html',
  styleUrl: './homizer-item-mainpage.component.css'
})
export class HomizerItemMainpageComponent {
  homizerItem: HomizerItem = new HomizerItem('');

  constructor(private dataStorageService: DataStorageService) {

  }

  onSaveHomizerItem(name: string, description?: string, image?: string, number?: number){
    console.log('test')
    const item: HomizerItem = new HomizerItem(name, description, image, number)
    this.dataStorageService.saveHomizerItem(item)
  }

}
