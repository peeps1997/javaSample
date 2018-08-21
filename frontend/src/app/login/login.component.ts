import { Component, OnInit, OnChanges } from '@angular/core';
import { MediaUser } from '../File';
import { md5 } from './md5';
import { AuthService } from '../services/auth.service';
import { Router } from '../../../node_modules/@angular/router';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnChanges {
  currentUser: MediaUser = { id: '', password: '', media: [], role: 'ADMIN' };
  isLoggedIn = false;

  constructor(private authService: AuthService, private router: Router) { }
  onSubmit() {
    console.log(this.currentUser.id);
    this.currentUser.password = md5(this.currentUser.password);
    console.log(btoa(String(this.currentUser.id + ':' + this.currentUser.password)));
    this.authService.login(this.currentUser);
    this.router.navigate(['/user/' + this.currentUser.id]);
  }
  getEncodedHeader(): string {
    return btoa(String(this.currentUser.id + ':' + this.currentUser.password));
  }

  ngOnInit() {
  }
  ngOnChanges() {
    console.log('UserChange' + this.currentUser.id);
  }

}
