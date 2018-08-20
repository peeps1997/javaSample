import { Component, OnInit, OnChanges } from '@angular/core';
import { MediaUser } from '../File';
import { md5 } from './md5';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnChanges {
  currentUser: MediaUser = { id: '', password: '', media: [], role: 'ADMIN' };

  constructor() { }
  onSubmit() {
    console.log(this.currentUser.id);
    this.currentUser.password = md5(this.currentUser.password);
    console.log(btoa(String(this.currentUser.id + ':' + this.currentUser.password)));
  }
  getEncodedHeader(): string {
    return btoa(String(this.currentUser.id + ':' + this.currentUser.password));
  }

  ngOnInit() {
  }
  ngOnChanges() {
    console.log(this.currentUser);
  }

}
