import {Component, OnInit} from '@angular/core';
import {HomizerItemDto} from '../../service/homizer.models';
import {HomizerDataService} from '../../service/homizer-data.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-homizer-mainpage',
  templateUrl: './homizer-item-mainpage.component.html',
  styleUrls: [
    './homizer-item-mainpage.component.css',
    '../../homizer-styles.css'
  ],
  standalone: false
})
export class HomizerItemMainpageComponent implements OnInit {
  homizerItems: HomizerItemDto[];
  showNoItemsMessage = false;
  filteredHomizerItems: HomizerItemDto[] = [];
  filterText: string = '';

  constructor(private dataService: HomizerDataService, private router: Router) {

  }

  ngOnInit() {
    this.loadHomizerItems();
    if (!this.homizerItems) {
      setTimeout(() => {
        this.showNoItemsMessage = true;
      }, 250);
    }
  }

  async loadHomizerItems(): Promise<HomizerItemDto[]> {
    const items = await this.dataService.loadHomizerItems()
      .catch(error => {
        if (error.response?.status === 403) {
          this.router.navigate(['/login'])
        }
      });
    this.homizerItems = items
    this.homizerItems = items.sort((a: { name: string; }, b: { name: any; }) => a.name.localeCompare(b.name));
    this.filteredHomizerItems = [...this.homizerItems];
    return items;
  }

  applyFilter(): void {
    if (!this.filterText) {
      this.filteredHomizerItems = [...this.homizerItems];
    } else {
      this.filteredHomizerItems = this.homizerItems.filter(item =>
        item.name.toLowerCase().includes(this.filterText.toLowerCase())
      );
    }
  }
}
