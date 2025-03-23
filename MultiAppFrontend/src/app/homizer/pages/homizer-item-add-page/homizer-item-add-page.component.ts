import {Component, OnInit} from '@angular/core';
import {HomizerItemDto, HomizerStorageDto} from '../../service/homizer.models';
import {HomizerDataService} from '../../service/homizer-data.service';
import {Router} from '@angular/router';
import imageCompression from 'browser-image-compression';

@Component({
  selector: 'app-homizer-item-add-page',
  templateUrl: './homizer-item-add-page.component.html',
  styleUrl: './homizer-item-add-page.component.css',
  standalone: false
})
export class HomizerItemAddPageComponent implements OnInit {

  homizerItem: HomizerItemDto
  homizerStorage: HomizerStorageDto[] = [];
  selectedHomizerStorageId: string


  constructor(private homizerDataService: HomizerDataService, private router: Router) {
    this.homizerItem = new HomizerItemDto('');
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
          this.homizerItem.image = reader.result;
        }
      });
      reader.readAsDataURL(compressedFile);
    } catch (error) {
      console.error('Bildkomprimierung fehlgeschlagen:', error);
    }
  }

  onSaveHomizerItem(name: string, description: string, image: string, number: number, homizerStorageId?: string) {
    this.homizerItem.name = name
    this.homizerItem.description = description
    this.homizerItem.image = image
    this.homizerItem.number = number
    this.homizerItem.homizerStorageId = homizerStorageId
    this.homizerDataService.saveHomizerItem(this.homizerItem).then(
      (response) => {
        this.router.navigate(['/homizer-item'])
      }
    )
  }

  ngOnInit(): void {
    this.loadHomizerStorages()
  }

  async loadHomizerStorages(): Promise<HomizerStorageDto[]> {
    const storages = await this.homizerDataService.loadHomizerStorages()
      .catch(error => {
        if (error.response?.status === 403) {
          this.router.navigate(['/login'])
        }
      })
    this.homizerStorage = storages
    return storages
  }
}
