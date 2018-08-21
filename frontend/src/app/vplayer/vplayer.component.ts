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
@Component({
  selector: 'app-vplayer',
  templateUrl: './vplayer.component.html',
  styleUrls: ['./vplayer.component.css']
})
export class VplayerComponent implements OnInit, AfterContentInit {
  isLoggedIn = false;
  currentUser: MediaUser;
  hidden = true;
  playlist: MediaFile[];
  currentIndex = 0;
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
    console.log(this.hidden);
  }
  deleteItem(itemName: String) {
    this.globalService
      .deleteData(this.customService.deleteMedia(itemName))
      .subscribe(media => this.getAllMedia());
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
      .subscribe(data => {
        this.currentItem = data;
      });
    console.log('Current Item' + this.currentItem.name);
  }
  getAllMedia(): void {
    this.globalService.getMedia(this.customService.getAllMedia()).subscribe(data => {
      this.playlist = data;
      console.log('DATA: ' + this.playlist);
      console.log(this.playlist.length);
    });
  }
  onSubmit() {
    if (this.addedMedia.name !== 'Random') {
      console.log(this.addedMedia.name + ' has been added');
      this.globalService
        .postData(this.customService.postMedia(), this.addedMedia)
        .subscribe(media => this.getAllMedia());
    } else {
      console.log('media not added');
    }
  }
  logout() {
    this.router.navigate(['/login']);
  }
  ngAfterContentInit() {
    // console.log(this.playlist);
    this.hidden = false;
  }
  ngOnInit() {
    this.authService.isLoggedInObs.subscribe(isLoggedIn => this.isLoggedIn = isLoggedIn);
    console.log('Logged In: ' + this.isLoggedIn);
    if (this.isLoggedIn === false) {
      console.log('Redirecting : ' + this.isLoggedIn);
      this.router.navigate(['/login']);
    }
    this.authService.currentObs.subscribe(currentUser => {
      this.currentUser = currentUser;
      console.log(currentUser);
    });
    console.log('Inside Vplayer' + this.currentUser.id);
    this.getAllMedia();

  }
}
