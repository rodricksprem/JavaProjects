import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenStorageService } from '../_services/token-storage.service';
import { AuthenticationService } from '../_services';
const TOKEN_HEADER_KEY = 'Authorization';
@Injectable()
export class JwtInterceptor implements HttpInterceptor {
    constructor(private authenticationService: AuthenticationService,private token: TokenStorageService) { console.log(" in JwtInterceptor ",this.token)}
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        // add authorization header with jwt token if available
        let authReq = request;
        const token = this.token.getToken();
        console.log("inside JwtInterceptor ..");
        if (token!=null) {
            console.log("add authorization header ..");
            authReq =request.clone({ headers: request.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token) });;
        }

        return next.handle(authReq);
    }
}
