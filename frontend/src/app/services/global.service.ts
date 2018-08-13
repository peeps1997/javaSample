import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from '../../../node_modules/rxjs';
import {MediaFile} from '../File';
@Injectable()
export class GlobalService {
    constructor(private http: HttpClient) {
    }
    getData(url: any): Observable<MediaFile> {
        return this.http.get<MediaFile>(url);
    }

    postData(url: any, media: MediaFile): Observable<MediaFile> {
        return this.http.post<MediaFile>(url, media);
    }

    deleteData(url: any, options): Observable<any> {
        return this.http.delete(url, options);
    }
    getMedia(): Observable<Array<MediaFile>> {
        // console.log(this.http.get<MediaFile[]>('http://localhost:8080/media/all'));
        return this.http.get<Array<MediaFile>>('http://localhost:8080/media/all', {responseType: 'json'});
    }
}
