import { Component, OnInit } from '@angular/core';
import { VgAPI } from 'videogular2/core';
@Component({
  selector: 'app-vplayer',
  templateUrl: './vplayer.component.html',
  styleUrls: ['./vplayer.component.css']
})

export class VplayerComponent {
  playlist: Array<MediaFile> = [
    {
        name: 'Pale Blue Dot',
        url:  new URL( 'http://static.videogular.com/assets/videos/videogular.mp4')
    },
    {
      name: 'Big Buck Bunny',
      url: new URL('http://static.videogular.com/assets/videos/big_buck_bunny_720p_h264.mov')
    }
];

currentIndex = 0;
currentItem: MediaFile = this.playlist[ this.currentIndex ];
api: VgAPI;

constructor() {
}

onPlayerReady(api: VgAPI) {
    this.api = api;
    this.api.getDefaultMedia().subscriptions.loadedMetadata.subscribe(this.playVideo.bind(this));
    this.api.getDefaultMedia().subscriptions.ended.subscribe(this.nextVideo.bind(this));
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
}

onClickPlaylistItem(item: MediaFile, index: number) {
    this.currentIndex = index;
    this.currentItem = item;
}

}
