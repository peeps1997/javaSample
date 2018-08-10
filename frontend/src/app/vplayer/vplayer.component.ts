import { Component, OnInit } from '@angular/core';
import { VgAPI } from 'videogular2/core';
import { CustomService } from '../services/custom.service';
import { GlobalService } from '../services/global.service';
import {MediaFile} from '../File';
import { Observable } from '../../../node_modules/rxjs';
import { FormControl, FormGroup, FormBuilder, Validators  } from '@angular/forms';
@Component({
  selector: 'app-vplayer',
  templateUrl: './vplayer.component.html',
  styleUrls: ['./vplayer.component.css']
})

export class VplayerComponent implements OnInit {
hidden = true;
playlist: MediaFile[];
currentIndex = 0;
currentItem: MediaFile = null;
api: VgAPI;
// tslint:disable-next-line:max-line-length
addedMedia: MediaFile = null;
mediaForm = new FormGroup({
    name: new FormControl(),
    url: new FormControl()
});
constructor(private customService: CustomService, private globalService: GlobalService, private fb: FormBuilder) {
}

onPlayerReady(api: VgAPI) {
    this.api = api;
    this.api.getDefaultMedia().subscriptions.loadedMetadata.subscribe(this.playVideo.bind(this));
    this.api.getDefaultMedia().subscriptions.ended.subscribe(this.nextVideo.bind(this));
}

onClickAddItem(hidden: boolean) {
    this.hidden = hidden;
    console.log(this.hidden);
}
nextVideo() {
    this.currentIndex++;
    if (this.currentIndex === this.playlist.length) {
        this.currentIndex = 0;
    }
    this.currentItem = this.playlist[ this.currentIndex ];
}

playVideo() {
    this.api.play();
    // console.log(this.currentItem);
    // console.log(this.playlist);
}

onClickPlaylistItem(itemName: String, index: number) {
    // tslint:disable-next-line:no-unused-expression
   // console.log(itemName);
    this.globalService.getData(this.customService.getMediaByName(itemName)).subscribe(data => {this.currentItem = data; } );
    console.log('Current Item' + this.currentItem.name);
   // this.currentItem = item;
}
getAllMedia(): void {
 this.globalService.getMedia().subscribe(data => this.playlist = data);

// this.globalService.getData().subscribe(data => this.currentItem = data);
console.log(this.currentItem);
}
onSubmit() {
    if (this.addedMedia != null) {
    console.log(this.addedMedia.name + ' has been added');
}   else {
    console.log('fuck u m8');
}
}



ngOnInit(): void {
this.hidden = false;
this.getAllMedia();
this.currentItem =  this.playlist[ this.currentIndex ];

}
}
