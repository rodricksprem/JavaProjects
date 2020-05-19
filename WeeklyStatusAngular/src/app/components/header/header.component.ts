import { Component, OnInit, Input } from '@angular/core';
import { UserSessionService } from '../../service/user-session.service';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  //@Input('uname') uname: string;
  username: String;
  
  info: { token: any; username: any; name: any; authorities: any; };
  constructor(private _userSessionService:UserSessionService, private _router:Router,private token: TokenStorageService) { }

  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      name: this.token.getName(),
      authorities: this.token.getAuthorities()
    };
    
 
  }
 
  logout() {
    this.token.signOut();
    window.location.reload();
  }
 }
