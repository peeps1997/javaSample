import { LoginComponent } from './login/login.component';
import { VplayerComponent } from './vplayer/vplayer.component';
import { Routes } from '@angular/router';
export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'user/:id', component: VplayerComponent },
    {
        path: '',
        redirectTo: '/login',
        pathMatch: 'full'
    },
    { path: '**', component: LoginComponent }
];
