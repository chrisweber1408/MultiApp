import {Component, OnInit} from '@angular/core';
import {HomizerItemDto, HomizerStorageDto} from '../../service/homizer.models';
import {HomizerDataService} from '../../service/homizer-data.service';
import {Router} from '@angular/router';

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
