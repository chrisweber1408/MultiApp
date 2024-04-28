import { Component } from '@angular/core';
import { HomizerItem } from '../../service/homizer.models';
import { DataStorageService } from '../../service/homizer-data-storage.service';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-homizer-add-page',
  templateUrl: './homizer-add-page.component.html',
  styleUrl: './homizer-add-page.component.css'
})
export class HomizerAddPageComponent {

  homizerItem: HomizerItem

  constructor(private dataStorageService: DataStorageService, private router: Router) {
    this.homizerItem = new HomizerItem('', '', '');
  }

  onSaveHomizerItem(name: string, description: string, image: string, number: number) {
    this.homizerItem.name = name
    this.homizerItem.description = description
    this.homizerItem.image = image
    this.homizerItem.number = number
    this.dataStorageService.saveHomizerItem(this.homizerItem).then(
      (response) => {
        this.router.navigate(['/homizer-item'])
      }
    )
    }

}
