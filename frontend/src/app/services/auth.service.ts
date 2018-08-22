import { Injectable, Output, EventEmitter } from '../../../node_modules/@angular/core';
import { MediaUser } from '../File';
import { Subject } from '../../../node_modules/rxjs/internal/Subject';
import { Observable, BehaviorSubject } from '../../../node_modules/rxjs';

@Injectable()
export class AuthService {
    currentUser: MediaUser = { id: '', password: '', media: [], role: 'USER' };
    private subject = new BehaviorSubject<MediaUser>(this.currentUser);
    currentObs = this.subject.asObservable();
    isLoggedIn = new BehaviorSubject<boolean>(false);
    isLoggedInObs = this.isLoggedIn.asObservable();
    encodedUserMeta: String;
    private authSubject = new BehaviorSubject<String>('');
    currentAuthObs = this.authSubject.asObservable();
    @Output() change: EventEmitter<String> = new EventEmitter();
    @Output() user: EventEmitter<MediaUser> = new EventEmitter();
    login(loginUser: MediaUser) {
        // console.log(loginUser);
        this.currentUser = loginUser;
        // console.log('Current User :' + this.currentUser.id);
        //  this.user.emit(this.currentUser);
        this.subject.next(this.currentUser);
        this.authSubject.next(btoa(String(this.currentUser.id + ':' + this.currentUser.password)));
        this.isLoggedIn.next(true);
        // console.log('BTOA: ' + btoa(String(this.currentUser.id + ':' + this.currentUser.password)));
    }
    getCurrentUser(): Observable<any> {
        // console.log('Inside getCurrentUser() ' + this.currentUser.id);
        return this.subject.asObservable();
    }

}
