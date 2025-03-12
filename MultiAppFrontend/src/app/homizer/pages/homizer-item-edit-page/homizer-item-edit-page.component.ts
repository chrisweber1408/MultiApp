import {Component, OnInit} from '@angular/core';
import {HomizerItemDto} from '../../service/homizer.models';
import {HomizerDataService} from '../../service/homizer-data.service';
import {ActivatedRoute, Router} from '@angular/router';
import {AxiosResponse} from 'axios';

@Component({
  selector: 'app-homizer-item-edit-page',
  templateUrl: './homizer-item-edit-page.component.html',
  styleUrl: './homizer-item-edit-page.component.css',
  standalone: false
})
export class HomizerItemEditPageComponent implements OnInit {

  homizerItem: HomizerItemDto

  constructor(
    private dataStorageService: HomizerDataService,
    private route: ActivatedRoute,
    private router: Router
  ) {

  }

  ngOnInit(): void {
    this.loadHomizerItem(this.route.snapshot.paramMap.get('id'))
  }

  loadHomizerItem(id: string): void {
    this.dataStorageService.loadHomizerItem(id)
      .then((item: AxiosResponse<HomizerItemDto, any>) => {
        this.homizerItem = item.data
      });
  }

  onEditHomizerItem(id: string, name: string, description: string, image: string, number: number) {
    this.homizerItem.id = id
    this.homizerItem.name = name
    this.homizerItem.description = description
    this.homizerItem.image = image
    this.homizerItem.number = number
    this.dataStorageService.saveHomizerItem(this.homizerItem).then(
      () => {
        this.router.navigate(['/homizer-item'])
      }
    )
  }

  onDeleteHomizerItem(id: string) {
    this.dataStorageService.deleteHomizerItem(id).then(
      () => {
        this.router.navigate(['/homizer-item'])
      }
    )
  }


}
