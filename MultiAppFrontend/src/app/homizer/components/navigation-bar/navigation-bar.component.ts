import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-navigation-bar',
  standalone: false,
  templateUrl: './navigation-bar.component.html',
  styleUrl: './navigation-bar.component.css'
})
export class NavigationBarComponent implements OnInit {
  @Input() title: string;
  navigationItems = []

  constructor(private route: ActivatedRoute) {

  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      switch (this.title) {
        case 'homizer-item': {
          this.navigationItems = [
            {icon: 'home', link: '/home'},
            {icon: 'category', link: '/homizer-item'},
            {icon: 'folder', link: '/homizer-storage'},
            {icon: 'add_circle', link: '/homizer-item-add-page'},
          ]
          break
        }
        case 'homizer-storage': {
          this.navigationItems = [
            {icon: 'home', link: '/home'},
            {icon: 'category', link: '/homizer-item'},
            {icon: 'folder', link: '/homizer-storage'},
            {icon: 'add_circle', link: '/homizer-storage-add-page'},
          ]
          break
        }
      }
    })
  }
}
