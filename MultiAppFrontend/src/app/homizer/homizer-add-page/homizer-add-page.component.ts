import { Component } from '@angular/core';
import { HomizerItem } from '../service/homizer.models';
import { DataStorageService } from '../../shared/data-storage.service';

@Component({
  selector: 'app-homizer-add-page',
  templateUrl: './homizer-add-page.component.html',
  styleUrl: './homizer-add-page.component.css'
})
export class HomizerAddPageComponent {

  homizerItem: HomizerItem

  constructor(private dataStorageService: DataStorageService) { // Injektion des DataStorageService über den Konstruktor
    this.homizerItem = new HomizerItem('', '', ''); // Initialisierung des homizerItem-Objekts
  }

  onSaveHomizerItem(name: string, description: string, image: string, number: number) {
    this.homizerItem.name = name
    this.homizerItem.description = description
    this.homizerItem.image = image
    this.homizerItem.number = number
    this.dataStorageService.saveHomizerItem(this.homizerItem)
    }

}
