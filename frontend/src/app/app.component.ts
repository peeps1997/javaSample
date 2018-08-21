import { Component, AfterViewInit, OnChanges } from '@angular/core';
import { MediaUser } from './File';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnChanges {
  constructor(private authService: AuthService) { }
  title = 'Welcome';
  currentUser: MediaUser;
  isLoggedIn = false;
  ngOnChanges() {
    this.authService.currentObs.subscribe(currentUser => this.currentUser = currentUser);
    this.authService.isLoggedInObs.subscribe(isLoggedIn => this.isLoggedIn = isLoggedIn);
    console.log('AppComponent : Current User : ' + this.currentUser.id + this.isLoggedIn);
    if (this.isLoggedIn === true) {
      this.title = String(this.currentUser.id);
    }
  }
}
