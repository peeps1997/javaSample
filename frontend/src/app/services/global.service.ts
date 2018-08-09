import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from '../../../node_modules/rxjs';
import {MediaFile} from '../File';
@Injectable()
export class GlobalService {
    constructor(private http: HttpClient) {
    }
    getData(url: any): Observable<MediaFile> {
        return this.http.get<MediaFile>(url);
    }

    postData(url: any, options: any): Observable<any> {
        return this.http.post(url, options);
    }

    deleteData(url: any, options): Observable<any> {
        return this.http.delete(url, options);
    }
    getMedia(): Observable<MediaFile[]> {
        console.log(this.http.get<MediaFile[]>('http://localhost:8080/media/all'));
        return this.http.get<MediaFile[]>('http://localhost:8080/media/all', {responseType: 'json'});
    }
}
