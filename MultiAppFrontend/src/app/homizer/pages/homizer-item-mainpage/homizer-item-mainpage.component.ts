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
  homizerItems: HomizerItemDto[] = [];
  showNoItemsMessage = false;
  filteredHomizerItems: HomizerItemDto[] = [];
  filterText: string = '';
  isLoading: boolean = true;

  constructor(private dataService: HomizerDataService, private router: Router) {
  }

  ngOnInit() {
    this.loadHomizerItems().then(() => {
      this.isLoading = false;
    })
  }

  async loadHomizerItems(): Promise<void> {
    try {
      this.homizerItems = await this.dataService.loadHomizerItems();
      if (!this.homizerItems || this.homizerItems.length === 0) {
        this.showNoItemsMessage = true;
        this.filteredHomizerItems = [];
        return;
      }
      this.filteredHomizerItems = [...this.homizerItems];
    } catch (error) {
      if (error.response?.status === 403) {
        await this.router.navigate(['/login']);
      } else {
        console.error('Error while loading items: ', error);
      }
    } finally {
      this.isLoading = false;
    }
  }

  applyFilter(): void {
    this.filteredHomizerItems = this.homizerItems.filter(item =>
      item.name.toLowerCase().includes(this.filterText.toLowerCase()));
    this.showNoItemsMessage = this.filteredHomizerItems.length === 0;
  }
}
