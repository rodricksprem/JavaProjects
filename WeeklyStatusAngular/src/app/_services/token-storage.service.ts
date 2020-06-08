import { Injectable } from '@angular/core';
 
const TOKEN_KEY = 'AuthToken';
const USERNAME_KEY = 'AuthUsername';
const NAME_KEY = 'AuthName';

const AUTHORITIES_KEY = 'AuthAuthorities';
 
@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
  private roles: Array<string> = [];
 private token:string;
  constructor() { 
    //console.log(" TokenStorageService ") 
  }
 
 
  signOut() {
    console.log("signOut called ..");
    window.sessionStorage.clear();
  }
 
  public saveToken(token: string) {
 //   console.log("saveToken ",token);
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);

  }
 
  public getToken(): string {
   
  //  console.log("getToken ",window.sessionStorage.getItem(TOKEN_KEY));

    
    return window.sessionStorage.getItem(TOKEN_KEY);
  }
 
  public saveUsername(username: string) {
    window.sessionStorage.removeItem(USERNAME_KEY);
    window.sessionStorage.setItem(USERNAME_KEY, username);
  }
 
  public getUsername(): string {
    //console.log("getUserName ", sessionStorage.getItem(USERNAME_KEY));
    return sessionStorage.getItem(USERNAME_KEY);
  }
 
  public saveName(name: string) {
    window.sessionStorage.removeItem(NAME_KEY);
    window.sessionStorage.setItem(NAME_KEY, name);
  }
 
  public getName(): string {
    return sessionStorage.getItem(NAME_KEY);
  }
  public saveAuthorities(authorities: string[]) {
    window.sessionStorage.removeItem(AUTHORITIES_KEY);
    window.sessionStorage.setItem(AUTHORITIES_KEY, JSON.stringify(authorities));
  }
 
  public getAuthorities(): string[] {
    this.roles = [];
    console.log("getAuthorities "+sessionStorage.getItem(AUTHORITIES_KEY));
    if (sessionStorage.getItem(TOKEN_KEY)) {
      JSON.parse(sessionStorage.getItem(AUTHORITIES_KEY)).forEach(authority => {
        this.roles.push(authority.authority);
      });
    }
 
    return this.roles;
  }
}