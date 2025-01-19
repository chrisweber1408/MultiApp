import { AfterContentChecked, Component, OnChanges, OnInit } from '@angular/core';
import { HomizerItem } from '../../service/homizer.models';
import { DataService } from '../../service/homizer-data.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
    selector: 'app-homizer-mainpage',
    templateUrl: './homizer-item-mainpage.component.html',
    styleUrls: ['./homizer-item-mainpage.component.css'],
    standalone: false
})
export class HomizerItemMainpageComponent implements OnInit {
  homizerItems: HomizerItem;

  constructor(private dataService: DataService, private activatedRoute: ActivatedRoute, private router: Router) {

  }

  ngOnInit() {
      this.loadHomizerItems();
  }

  async loadHomizerItems(): Promise<HomizerItem[]> {
    const items = await this.dataService.loadHomizerItems()
      .catch(error => {
        if(error.response?.status === 403) {
          this.router.navigate(['/login'])
        }
      });
    this.homizerItems = items
    return items;
  }


}
