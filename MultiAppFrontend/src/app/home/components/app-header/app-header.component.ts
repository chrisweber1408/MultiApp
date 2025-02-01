import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './app-header.component.html',
  styleUrl: './app-header.component.css',
  standalone: false
})
export class HomeHeaderComponent {

  @Input() title: string;

}
