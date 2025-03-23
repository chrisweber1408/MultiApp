import {Component} from '@angular/core';
import {HomizerStorageDto} from "../../service/homizer.models";
import {HomizerDataService} from "../../service/homizer-data.service";
import {Router} from "@angular/router";
import imageCompression from "browser-image-compression";

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

  async onFileSelected(event: any) {
    const file = event.target.files[0];

    try {
      const compressedFile = await imageCompression(file, {
        maxSizeMB: 0.8,
        maxWidthOrHeight: 1920,
        useWebWorker: true,
      });

      const reader = new FileReader();
      reader.addEventListener('load', (event) => {
        if (typeof reader.result === 'string') {
          this.homizerStorage.image = reader.result;
        }
      });
      reader.readAsDataURL(compressedFile);
    } catch (error) {
      console.error('Bildkomprimierung fehlgeschlagen:', error);
    }
  }

  onSavehomizerStorage(name: string, description: string, image: string) {
    this.homizerStorage.name = name
    this.homizerStorage.description = description
    this.homizerStorage.image = image
    this.homizerDataService.saveHomizerStorage(this.homizerStorage).then(
      (response) => {
        this.router.navigate(['/homizer-storage'])
      }
    )
  }

}
