import {Component, OnInit} from '@angular/core';
import {HomizerItemDto, HomizerStorageDto} from '../../service/homizer.models';
import {HomizerDataService} from '../../service/homizer-data.service';
import {ActivatedRoute, Router} from '@angular/router';
import {AxiosResponse} from 'axios';
import {MatDialog} from "@angular/material/dialog";
import {ImageViewDialogComponent} from "../../components/image-view-dialog/image-view-dialog.component";
import imageCompression from "browser-image-compression";

@Component({
  selector: 'app-homizer-item-edit-page',
  templateUrl: './homizer-item-edit-page.component.html',
  standalone: false,
  styleUrls: [
    './homizer-item-edit-page.component.css',
    '../../homizer-styles.css'
  ]
})
export class HomizerItemEditPageComponent implements OnInit {

  homizerItem: HomizerItemDto
  homizerStorageDtos: HomizerStorageDto[] = [];
  homizerStorage: HomizerStorageDto;


  constructor(
    private homizerDataService: HomizerDataService,
    private route: ActivatedRoute,
    private router: Router,
    private dialog: MatDialog
  ) {

  }

  ngOnInit(): void {
    this.loadHomizerItem(this.route.snapshot.paramMap.get('id')).then(() => {
      this.loadHomizerStorages().then(() => {
        if (this.homizerItem?.homizerStorageId) {
          this.loadHomizerStorage(this.homizerItem.homizerStorageId);
        }
      });
    });
  }

  async loadHomizerItem(id: string): Promise<void> {
    try {
      const item = await this.homizerDataService.loadHomizerItem(id);
      this.homizerItem = item.data;
    } catch (error) {
      console.error('Failed to load Homizer item:', error);
    }
  }


  onEditHomizerItem(id: string, name: string, description: string, image: string, number: number, homizerStorageId: string): void {
    this.homizerItem.id = id
    this.homizerItem.name = name
    this.homizerItem.description = description
    this.homizerItem.image = image
    this.homizerItem.number = number
    this.homizerItem.homizerStorageId = homizerStorageId
    this.homizerDataService.saveHomizerItem(this.homizerItem).then(
      () => {
        this.router.navigate(['/homizer-item'])
      }
    )
  }

  onDeleteHomizerItem(id: string) {
    this.homizerDataService.deleteHomizerItem(id).then(
      () => {
        this.router.navigate(['/homizer-item'])
      }
    )
  }


  async loadHomizerStorages(): Promise<HomizerStorageDto[]> {
    const storages = await this.homizerDataService.loadHomizerStorages()
      .catch(error => {
        if (error.response?.status === 403) {
          this.router.navigate(['/login'])
        }
      })
    this.homizerStorageDtos = storages
    return storages
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

  openImageDialog(imageUrl: string): void {
    this.dialog.open(ImageViewDialogComponent, {
      data: {image: imageUrl},
      panelClass: 'full-screen-dialog'
    });
  }

  loadHomizerStorage(id: string): void {
    this.homizerDataService.loadHomizerStorage(id)
      .then((storage: AxiosResponse<HomizerStorageDto, any>) => {
        this.homizerStorage = storage.data
      });
  }

}
