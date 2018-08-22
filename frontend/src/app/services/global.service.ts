import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MediaFile } from '../File';
import { AuthService } from '../services/auth.service';
import { HttpResponse } from '../../../node_modules/@types/selenium-webdriver/http';
@Injectable()
export class GlobalService {
  token: String = '';
  constructor(private http: HttpClient, private authService: AuthService) { }
  getData(url: any): Observable<HttpResponse> {
    return this.http.get<MediaFile>(url, {
      headers: this.getAuthHeader(),
      observe: 'response'
    });
  }

  postData(url: any, media: MediaFile): Observable<HttpResponse> {
    return this.http.post<MediaFile>(url, media, {
      headers: this.getAuthHeader(),
      observe: 'response'
    });
  }

  deleteData(url: any): Observable<HttpResponse> {
    return this.http.delete<MediaFile>(url, {
      headers: this.getAuthHeader(),
      observe: 'response'
    });
  }
  getMedia(url: any): Observable<HttpResponse> {
    // tslint:disable-next-line:no-unused-expression
    // console.log(this.getAuthHeader().get('Authorization'));
    // console.log(this.http.get<MediaFile[]>('http://localhost:8080/media/all'));
    return this.http.get<HttpResponse>(url, {
      headers: this.getAuthHeader(),
      observe: 'response'
    });
  }

  getAuthHeader() {
    this.authService.currentAuthObs.subscribe(token => this.token = token);
    this.token = 'Basic ' + this.token;
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': String(this.token),
    });
  }
}
