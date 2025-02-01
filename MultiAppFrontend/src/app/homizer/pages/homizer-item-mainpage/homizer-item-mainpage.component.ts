import { AfterContentChecked, Component, OnChanges, OnInit } from '@angular/core';
import { HomizerItemDto } from '../../service/homizer.models';
import { HomizerDataService } from '../../service/homizer-data.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
    selector: 'app-homizer-mainpage',
    templateUrl: './homizer-item-mainpage.component.html',
    styleUrls: ['./homizer-item-mainpage.component.css'],
    standalone: false
})
export class HomizerItemMainpageComponent implements OnInit {
  homizerItems: HomizerItemDto;

  constructor(private dataService: HomizerDataService, private activatedRoute: ActivatedRoute, private router: Router) {

  }

  ngOnInit() {
      this.loadHomizerItems();
  }

  async loadHomizerItems(): Promise<HomizerItemDto[]> {
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
