import { Component, OnInit } from '@angular/core';
import { Subscriber } from 'rxjs';
import { UserSessionService } from '../../service/user-session.service';

@Component({
  selector: 'app-error-page',
  templateUrl: './error-page.component.html',
  styleUrls: ['./error-page.component.css']
})
export class ErrorPageComponent implements OnInit {

  hostName: String="";
  error: Error
  constructor(private _userSessionService:UserSessionService) {
    this.hostName = this._userSessionService.getHostName();
    console.log("error page constructor this.hostName: "+this.hostName);
   
   }

  ngOnInit() {
    this.hostName = this._userSessionService.getHostName();
    console.log("error page on load this.hostName: "+this.hostName);
    if(this.error != null){
console.log("Error:"+this.error);
    }
    
  }

}
