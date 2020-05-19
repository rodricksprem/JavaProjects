import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { AuthLoginInfo } from '../dao/AuthLoginInfo';
import { JwtResponse } from '../dao/jwt-response';
import { Observable } from 'rxjs';
import { SignUpInfo } from '../dao/SignUpInfo';
const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
@Injectable({ providedIn: 'root'})
export class AuthenticationService {
    private loginUrl = '/weeklystatus/bctuser/signin';
    private signupUrl = '/weeklystatus/bctuser/signup';
    constructor(private http: HttpClient) {  console.log(" AuthenticationService")}

   

  
    attemptAuth(credentials: AuthLoginInfo): Observable<JwtResponse> {
        return this.http.post<JwtResponse>(this.loginUrl, credentials, httpOptions);
      }
     
      signUp(info: SignUpInfo): Observable<string> {
        return this.http.post<string>(this.signupUrl, info, httpOptions);
      }
      
}