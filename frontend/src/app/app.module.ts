import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { VplayerComponent } from './vplayer/vplayer.component';
import { VgCoreModule } from 'videogular2/core';
import { VgControlsModule } from 'videogular2/controls';
import { HttpClientModule } from '@angular/common/http';
import { GlobalService } from './services/global.service';
import { CustomService } from './services/custom.service';
import { LoginComponent } from './login/login.component';
import { RouterModule } from '@angular/router';
import { routes } from './routes';
import { AuthService } from './services/auth.service';
@NgModule({
  declarations: [
    AppComponent,
    VplayerComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    VgCoreModule,
    VgControlsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(
      routes)
  ],
  providers: [GlobalService, CustomService, AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
