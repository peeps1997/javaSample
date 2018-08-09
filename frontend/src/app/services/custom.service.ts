import { Injectable } from '../../../node_modules/@angular/core';
import { Observable } from '../../../node_modules/rxjs';

@Injectable()
export class CustomService {
    url: any = 'http://localhost:8080/media/';
    getMediaByName(name: String): String {
        console.log(this.url + name);
        return this.url + name;
    }

    getAllMedia(): String {
        console.log(this.url + 'all');
        return this.url + 'all';
    }

    postMedia(): String {
        console.log(this.url + 'save');
        return this.url + 'save';
    }

    deleteMedia(name: String): String {
        console.log(this.url + 'delete/' + name);
        return this.url + 'delete/' + name;
    }
}
