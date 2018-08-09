import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { VplayerComponent } from './vplayer/vplayer.component';
import { VgCoreModule } from 'videogular2/core';
import { VgControlsModule } from 'videogular2/controls';
import { HttpClientModule } from '@angular/common/http';
import {GlobalService} from './services/global.service';
import { CustomService } from './services/custom.service';
@NgModule({
  declarations: [
    AppComponent,
    VplayerComponent
  ],
  imports: [
    BrowserModule,
    VgCoreModule,
    VgControlsModule,
    HttpClientModule
  ],
  providers: [GlobalService, CustomService],
  bootstrap: [AppComponent]
})
export class AppModule { }
