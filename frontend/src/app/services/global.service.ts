import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MediaFile } from '../File';
import { AuthService } from '../services/auth.service';
@Injectable()
export class GlobalService {
  token: String = '';
  constructor(private http: HttpClient, private authService: AuthService) { }
  getData(url: any): Observable<MediaFile> {
    return this.http.get<MediaFile>(url, this.getAuthHeader());
  }

  postData(url: any, media: MediaFile): Observable<MediaFile> {
    return this.http.post<MediaFile>(url, media, this.getAuthHeader());
  }

  deleteData(url: any): Observable<MediaFile> {
    return this.http.delete<MediaFile>(url, this.getAuthHeader());
  }
  getMedia(url: any): Observable<Array<MediaFile>> {
    console.log(this.getAuthHeader().headers.get('Authorization'));
    // console.log(this.http.get<MediaFile[]>('http://localhost:8080/media/all'));
    return this.http.get<Array<MediaFile>>(url, this.getAuthHeader());
  }

  getAuthHeader() {
    this.authService.currentAuthObs.subscribe(token => this.token = token);
    this.token = 'Basic ' + this.token;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': String(this.token)
      })
    };
    return httpOptions;
  }
}
