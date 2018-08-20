import { Component, OnInit, OnChanges, AfterContentInit } from '@angular/core';
import { VgAPI } from 'videogular2/core';
import { CustomService } from '../services/custom.service';
import { GlobalService } from '../services/global.service';
import { MediaFile } from '../File';
import { Observable } from '../../../node_modules/rxjs';
import {
  FormControl,
  FormGroup,
  FormBuilder,
  Validators
} from '@angular/forms';
@Component({
  selector: 'app-vplayer',
  templateUrl: './vplayer.component.html',
  styleUrls: ['./vplayer.component.css']
})
export class VplayerComponent implements OnInit, AfterContentInit {
  hidden = true;
  playlist: MediaFile[];
  currentIndex = 0;
  currentItem: MediaFile = {
    name: 'Random',
    url: new URL('http://techslides.com/demos/sample-videos/small.mp4')
  };
  api: VgAPI;
  // tslint:disable-next-line:max-line-length
  addedMedia: MediaFile = {
    name: 'Random',
    url: new URL('http://techslides.com/demos/sample-videos/small.mp4')
  };
  mediaForm = new FormGroup({
    name: new FormControl(),
    url: new FormControl()
  });
  constructor(
    private customService: CustomService,
    private globalService: GlobalService,
    private fb: FormBuilder
  ) { }

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
    this.globalService.getMedia().subscribe(data => {
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
  ngAfterContentInit() {
    // console.log(this.playlist);
    this.hidden = false;
  }
  ngOnInit() {
    this.getAllMedia();
  }
}
