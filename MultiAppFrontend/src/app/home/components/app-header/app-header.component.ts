import {Component, Inject, Input} from '@angular/core';
import {Router} from '@angular/router';
import {DOCUMENT} from "@angular/common";

@Component({
  selector: 'app-header',
  templateUrl: './app-header.component.html',
  styleUrl: './app-header.component.css',
  standalone: false
})
export class HomeHeaderComponent {

  @Input() title: string;

  constructor(@Inject(DOCUMENT) private document: Document, private router: Router) {
  }

}
