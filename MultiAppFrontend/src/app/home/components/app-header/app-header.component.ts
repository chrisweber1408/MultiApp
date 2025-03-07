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

  isLoggedIn(): boolean {
    return this.document.cookie.includes('jwt='); // Check if JWT cookie exists
  }

  logout() {
    document.cookie = 'jwt=; Path=/; Expires=Thu, 01 Jan 1970 00:00:00 UTC;'; // Delete JWT cookie
    this.router.navigate(['/login']);
  }
}
