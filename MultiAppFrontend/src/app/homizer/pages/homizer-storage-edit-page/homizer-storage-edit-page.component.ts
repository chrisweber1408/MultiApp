import {Component, OnInit} from '@angular/core';
import {HomizerDataService} from '../../service/homizer-data.service';
import {AxiosResponse} from "axios";
import {HomizerItemDto, HomizerStorageDto} from "../../service/homizer.models";
import {ActivatedRoute, Router} from "@angular/router";
import {ImageViewDialogComponent} from "../../components/image-view-dialog/image-view-dialog.component";
import {MatDialog} from "@angular/material/dialog";
import imageCompression from "browser-image-compression";


@Component({
  selector: 'app-homizer-storage-edit-page',
  standalone: false,
  templateUrl: './homizer-storage-edit-page.component.html',
  styleUrls: [
    './homizer-storage-edit-page.component.css',
    '../../homizer-styles.css'
    ]
})
export class HomizerStorageEditPageComponent implements OnInit {

  homizerStorage: HomizerStorageDto
  showNoItemsMessage = false;
  homizerItems: HomizerItemDto[]

  constructor(
    private dataStorageService: HomizerDataService,
    private route: ActivatedRoute,
    private router: Router,
    private dialog: MatDialog
  ) {

  }

  ngOnInit(): void {
    this.loadHomizerStorage(this.route.snapshot.paramMap.get('id'))
    this.loadHomizerItemsForStorage(this.route.snapshot.paramMap.get('id'))
  }

  loadHomizerStorage(id: string): void {
    this.dataStorageService.loadHomizerStorage(id)
      .then((storage: AxiosResponse<HomizerStorageDto, any>) => {
        this.homizerStorage = storage.data
      });
  }

  onEditHomizerStorage(id: string, name: string, description: string, image: string) {
    this.homizerStorage.id = id
    this.homizerStorage.name = name
    this.homizerStorage.description = description
    this.homizerStorage.image = image
    this.dataStorageService.saveHomizerStorage(this.homizerStorage).then(
      () => {
        this.router.navigate(['/homizer-storage'])
      }
    )
  }

  onDeleteHomizerStoragge(id: string) {
    this.dataStorageService.deleteHomizerStorage(id).then(
      () => {
        this.router.navigate(['/homizer-storage'])
      }
    )
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

  openImageDialog(imageUrl: string): void {
    this.dialog.open(ImageViewDialogComponent, {
      data: {image: imageUrl},
      panelClass: 'full-screen-dialog'
    });
  }

  async loadHomizerItemsForStorage(id: string): Promise<HomizerItemDto[]> {
    const items = await this.dataStorageService.loadHomizerItemsForStorage(id)
      .catch(error => {
        if (error.response?.status === 403) {
          this.router.navigate(['/login'])
        }
      });
    this.homizerItems = items
    this.homizerItems = items.sort((a: { name: string; }, b: { name: any; }) => a.name.localeCompare(b.name));
    return items;
  }

}
