import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/Observable/throw';
import { AppConstantService } from '../constant/app-constant.service';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserSessionService {
  private baseurl:string;
  private options;
  public uname : string;
  public userType : number;
  public hostName : string;
  public msg:string='';
  public fileURL : string;

  
  constructor(private _http:HttpClient, private _constant: AppConstantService) 
  { 
    this.baseurl = this._constant.HOST_NAME + "/biolog";
    this.options = this._constant.options;
  }
  setUserName(uname:string){
    this.uname = uname;
  }
  setFileURL(fileURL:string){
    this.fileURL = fileURL;
  }
  getFileURL(){
    return this.fileURL;
  }

  getUserName(){
    return this.uname;
  }

  setUserType(userType:number){
    this.userType = userType;
  }

  getUserType(){
    return this.userType;
  }

  setHostName(hostName:string){
    this.hostName = hostName;
  }

  getHostName(){
    return this.hostName;
  }

  getMsg(){
    return this.msg;
  }
setMsg(msg:string){
  this.msg = msg;
}

  getUserNameByUserId()
  {
     console.log("inside the getUserNameByUserId method in user-session.service.ts:");
    return this._http.get(this.baseurl+'/networkUserName', this.options)
        .catch(this.GlobalErrorHandler);
  }

  // getUserNameByUserId(userId:String)
  // {
  //   console.log("inside the getUserNameByUserId method in user-session.service.ts");
  //   return this._http.get(this.baseurl+'/networkUserName', this.options)
  //       .catch(this.errorHandler);
  // }

  logout(userId:String)
  {
    console.log("inside the logOut method in user-session.service.ts:");
    return this._http.get(this.baseurl+'/logOut/'+userId, this.options)
    .catch(this.GlobalErrorHandler);

  }

  getServiceName()
  {
    console.log("inside the getServiceName method in user-session.service.ts:");
    return this._http.get(this.baseurl+'/getName', this.options)
    .catch(this.GlobalErrorHandler);

  }
  
  GlobalErrorHandler(error:Response)
  {
    
    return Observable.throw(error||"Server Error");
  }

}
