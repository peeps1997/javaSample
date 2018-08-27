import { Component, OnInit, OnChanges, AfterContentInit } from '@angular/core';
import { VgAPI } from 'videogular2/core';
import { CustomService } from '../services/custom.service';
import { GlobalService } from '../services/global.service';
import { MediaFile } from '../File';
import { Observable } from 'rxjs';
import { MediaUser } from '../File';
import {
  FormControl,
  FormGroup,
  FormBuilder,
  Validators
} from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { Router } from '../../../node_modules/@angular/router';
import { HttpHeaders } from '../../../node_modules/@angular/common/http';
@Component({
  selector: 'app-vplayer',
  templateUrl: './vplayer.component.html',
  styleUrls: ['./vplayer.component.css']
})
export class VplayerComponent implements OnInit, AfterContentInit {
  userHeader: HttpHeaders;
  isLoggedIn = false;
  currentUser: MediaUser;
  hidden = true;
  playlist: MediaFile[];
  currentIndex = 0;
  respStatus = 0;
  currentItem: MediaFile = {
    name: '',
    url: new URL('http://techslides.com/demos/sample-videos/small.mp4')
  };
  api: VgAPI;
  // tslint:disable-next-line:max-line-length
  addedMedia: MediaFile = {
    name: '',
    url: new URL('http://techslides.com/demos/sample-videos/small.mp4')
  };
  mediaForm = new FormGroup({
    name: new FormControl(),
    url: new FormControl()
  });
  constructor(
    private router: Router,
    private authService: AuthService,
    private customService: CustomService,
    private globalService: GlobalService,
    private fb: FormBuilder
  ) { }
  isAdmin(): boolean {
    if (this.currentUser.role === 'ADMIN') {
      return true;
    } else {
      return false;
    }
  }
  onPlayerReady(api: VgAPI) {
    this.api = api;
    this.api
      .getDefaultMedia()
      .subscriptions.loadedMetadata.subscribe(this.playVideo.bind(this));
    this.api
      .getDefaultMedia()
      .subscriptions.ended.subscribe(this.nextVideo.bind(this));
  }

  onClickAddItem(hidden: boolean) {
    this.hidden = hidden;
    // console.log(this.hidden);
  }
  deleteItem(itemName: String) {
    this.globalService
      .deleteData(this.customService.deleteMedia(itemName))
      .subscribe(resp => {
        if (resp['status'] === 403) {
          this.currentUser.role = 'USER';
          console.log('USER ROLE' + this.currentUser.role);
        }
        this.getAllMedia();
      });
  }
  nextVideo() {
    this.currentIndex++;
    if (this.currentIndex === this.playlist.length) {
      this.currentIndex = 0;
    }
    this.currentItem = this.playlist[this.currentIndex];
  }
  isLogIn(): boolean {
    return this.isLoggedIn;
  }

  playVideo() {
    this.api.play();
  }

  onClickPlaylistItem(itemName: String, index: number) {
    this.globalService
      .getData(this.customService.getMediaByName(itemName))
      .subscribe(resp => {
        this.currentItem = resp['body'];
        // console.log(resp.toString);
      });
    // console.log('Current Item' + this.currentItem.name);
  }
  getAllMedia(): void {
    // console.log('INSIDE GET ALL MEDIA');
    this.globalService.getMedia(this.customService.getAllMedia()).subscribe(
      resp => {
        this.respStatus = resp['status'];
        // console.log('STATUS ' + this.respStatus);
        this.playlist = resp['body'];
        // console.log('PLaylist:' + this.playlist);
        // console.log(resp);
        // console.log('CURRENT USER ROLE' + resp['headers'].get['Role']);
        // else {
        //   this.router.navigate(['/login']);
        // }
      },
      () => {
        if (this.respStatus !== 200) {

          // console.log('INSIDE IF' + this.respStatus);
          this.router.navigate(['/login']);
        }
      });
  }
  onSubmit() {
    if (this.addedMedia.name !== 'Random') {
      // console.log(this.addedMedia.name + ' has been added');
      this.globalService
        .postData(this.customService.postMedia(), this.addedMedia)
        .subscribe(media => this.getAllMedia());
    } else {
      // console.log('media not added');
    }
  }
  logout() {
    this.router.navigate(['/login']);
  }
  ngAfterContentInit() {
    // console.log(this.playlist);
    this.hidden = false;
    // console.log('In After Content Init');
  }
  ngOnInit() {
    this.authService.isLoggedInObs.subscribe(isLoggedIn => this.isLoggedIn = isLoggedIn);
    // console.log('Logged In: ' + this.isLoggedIn);
    if (this.isLoggedIn === false) {
      // console.log('Redirecting : ' + this.isLoggedIn);
      this.logout();
    }
    this.authService.currentObs.subscribe(currentUser => {
      this.currentUser = currentUser;
      // console.log(currentUser);
    });
    // console.log('Inside Vplayer' + this.currentUser.id);
    this.getAllMedia();
  }
}
