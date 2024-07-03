import { AfterContentChecked, Component, OnChanges, OnInit } from '@angular/core';
import { HomizerItem } from '../../service/homizer.models';
import { DataStorageService } from '../../service/homizer-data-storage.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-homizer-mainpage',
  templateUrl: './homizer-item-mainpage.component.html',
  styleUrls: ['./homizer-item-mainpage.component.css']
})
export class HomizerItemMainpageComponent implements OnInit {
  homizerItems: HomizerItem;

  constructor(private dataStorageService: DataStorageService, private activatedRoute: ActivatedRoute, private router: Router) {

  }

  ngOnInit(): void {
      this.loadHomizerItems();
  }

  onSaveHomizerItem(name: string, description?: string, image?: string, number?: number, id?: string){
    const item: HomizerItem = new HomizerItem(name, description, image, number, id)
    this.dataStorageService.saveHomizerItem(item)
  }

  async loadHomizerItems(): Promise<HomizerItem[]> {
    const items = await this.dataStorageService.loadHomizerItems()
      .catch(error => {
        if(error.response?.status === 403) {
          this.router.navigate(['/login'])
        }
      });
    this.homizerItems = items
    return items;
  }
  
  
}
