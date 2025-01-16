import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {CookieService} from 'ngx-cookie-service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
  standalone: false
})
export class HomeComponent {

  constructor(private router: Router, private cookieService: CookieService) {

  }

  ngOnInit() {
    if (this.cookieService.get("jwt") === null || this.cookieService.get("jwt") === "") {
      this.router.navigate(['/login']);
    }
  }

}
