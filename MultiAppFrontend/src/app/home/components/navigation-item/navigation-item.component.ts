// navigation-item.component.ts
import {Component, Input} from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import {NgClass} from "@angular/common";

@Component({
  selector: 'app-navigation-item',
  templateUrl: './navigation-item.component.html',
  imports: [
    NgClass,
    RouterLink
  ],
  styleUrls: ['./navigation-item.component.css']
})
export class NavigationItemComponent {
  @Input() item: { icon: string; link: string };

  constructor(private router: Router) {
  }

  isActive() {
    return this.router.url === this.item.link;
  }
}
