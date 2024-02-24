import { Component, OnInit } from '@angular/core';
import { HomizerItem } from '../service/homizer.models';
import { DataStorageService } from '../../shared/data-storage.service';
import { Observable } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-homizer-item-edit-page',
  templateUrl: './homizer-item-edit-page.component.html',
  styleUrl: './homizer-item-edit-page.component.css'
})
export class HomizerItemEditPageComponent implements OnInit {

  homizerItem: HomizerItem
  constructor(
    private dataStorageService: DataStorageService,
    private route: ActivatedRoute
    ) {

  }

  ngOnInit(): void {
    this.loadHomizerItem(this.route.snapshot.paramMap.get('id'))
  }

  loadHomizerItem(id: string): void {
    this.dataStorageService.loadHomizerItem(id)
    .subscribe((item: HomizerItem) => {
      this.homizerItem = item
    });
  }
  

}
