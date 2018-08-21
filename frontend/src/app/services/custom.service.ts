import { Injectable, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { MediaFile, MediaUser } from '../File';
import { AuthService } from './auth.service';

@Injectable()
export class CustomService implements OnInit {
    constructor(private authService: AuthService) {
        this.authService.getCurrentUser().subscribe(currentUser => {
            this.currentUser = currentUser;
            console.log(this.currentUser);
        });
    }
    currentUser: MediaUser = { id: '', password: '', media: [], role: 'ADMIN' };
    baseUrl: any = 'http://localhost:8080/';
    getMediaByName(name: String): String {
        console.log(this.baseUrl + this.currentUser.id + '/media/' + name);
        return this.baseUrl + this.currentUser.id + '/media/' + name;
    }

    getAllMedia(): String {
        console.log(this.baseUrl + this.currentUser.id + '/media/' + 'all');
        return this.baseUrl + this.currentUser.id + '/media/' + 'all';
    }

    postMedia(): String {
        console.log(this.baseUrl + this.currentUser.id + '/media/' + 'save');
        return this.baseUrl + this.currentUser.id + '/media/' + 'save';
    }

    deleteMedia(name: String): String {
        console.log(this.baseUrl + this.currentUser.id + '/' + 'delete/' + name);
        return this.baseUrl + this.currentUser.id + '/' + 'delete/' + name;
    }

    ngOnInit() {
        this.authService.getCurrentUser().subscribe(currentUser => {
            this.currentUser = currentUser;
            console.log(this.currentUser);
        });
        //  this.authService.getCurrentUser();
        console.log('Current User Inside custom Service ' + this.currentUser.id);
    }

}
