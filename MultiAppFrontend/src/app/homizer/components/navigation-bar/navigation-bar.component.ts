// navigation-bar.component.ts
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-navigation-bar',
  standalone: false,
  templateUrl: './navigation-bar.component.html',
  styleUrl: './navigation-bar.component.css'
})
export class NavigationBarComponent implements OnInit {
  navigationItems = []

  constructor(private route: ActivatedRoute) {

  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      console.log(this.route.snapshot.url.join("/"))
      switch (this.route.snapshot.url.join("/")) {
        case 'homizer-item': {
          this.navigationItems = [
            {icon: 'add_circle_outline', link: '/homizer-add-page'},
            {icon: 'add_circle_outline', link: '/homizer-add-page'},
            {icon: 'edit', link: '/homizer-edit-page'},
          ]
          break
        }
        case 'homizer-add-page': {
          this.navigationItems = [
            {icon: 'add_circle_outline', link: '/homizer-add-page'},
            {icon: 'add_circle_outline', link: '/homizer-add-page'},
            {icon: 'edit', link: '/homizer-edit-page'},
          ]
          break
        }
      }
    })
    //this.route.snapshot.url.join("/")
  }
}
