import {Component} from '@angular/core';
import {HomizerItem} from '../../service/homizer.models';
import {DataStorageService} from '../../service/homizer-data-storage.service';
import {Router} from '@angular/router';
import {Observable, ReplaySubject} from "rxjs";

@Component({
    selector: 'app-homizer-add-page',
    templateUrl: './homizer-add-page.component.html',
    styleUrl: './homizer-add-page.component.css',
    standalone: false
})
export class HomizerAddPageComponent {

  homizerItem: HomizerItem
  test: File

  constructor(private dataStorageService: DataStorageService, private router: Router) {
    this.homizerItem = new HomizerItem('');
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];
    const reader = new FileReader();
    reader.addEventListener('load', (event) => {
      if (typeof reader.result === 'string') {
        this.homizerItem.image = reader.result;
      }
    })
    reader.readAsDataURL(file);
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
