import {Component} from '@angular/core';
import {HomizerStorageDto} from "../../service/homizer.models";
import {HomizerDataService} from "../../service/homizer-data.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-homizer-storage-add-page',
  standalone: false,

  templateUrl: './homizer-storage-add-page.component.html',
  styleUrl: './homizer-storage-add-page.component.css'
})
export class HomizerStorageAddPageComponent {

  homizerStorage: HomizerStorageDto

  constructor(private homizerDataService: HomizerDataService, private router: Router) {
    this.homizerStorage = new HomizerStorageDto('');
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];
    const reader = new FileReader();
    reader.addEventListener('load', (event) => {
      if (typeof reader.result === 'string') {
        this.homizerStorage.image = reader.result;
      }
    })
    reader.readAsDataURL(file);
  }

  onSavehomizerStorage(name: string, description: string, image: string) {
    this.homizerStorage.name = name
    this.homizerStorage.description = description
    this.homizerStorage.image = image
    this.homizerDataService.saveHomizerStorage(this.homizerStorage).then(
      (response) => {
        this.router.navigate(['/homizer-item'])
      }
    )
  }

}
