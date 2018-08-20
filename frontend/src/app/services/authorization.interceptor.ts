import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '../../../node_modules/@angular/common/http';
import { Observable } from '../../../node_modules/rxjs';
import { LoginComponent } from '../login/login.component';
export class UploadInterceptor implements HttpInterceptor {
    login: LoginComponent;
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (this.login.getEncodedHeader()) {
            return next.handle(req); // do nothing
        }
        const delay = 300;
        // Create simulation of upload event stream

    }
}

