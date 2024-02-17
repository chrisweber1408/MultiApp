import { Component } from '@angular/core';
import { homizerItem } from '../service/homizer.models';

@Component({
  selector: 'app-homizer-mainpage',
  templateUrl: './homizer-item-mainpage.component.html',
  styleUrl: './homizer-item-mainpage.component.css'
})
export class HomizerItemMainpageComponent {
  homizerItem: homizerItem[] = [
    new homizerItem('Handy', 'Iphone 6', 'assets/images/home.png', 1)
  ]

}
