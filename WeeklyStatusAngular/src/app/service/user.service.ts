import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/Observable/throw';
import {User} from '../dao/user';
import {Bctloguser} from '../dao/bctloguser';
import { AppConstantService } from '../constant/app-constant.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService{

  private baseurl:string;
  private options;
  private user = new User();
  private bctloguser = new Bctloguser();
  readonly optionsnew =    {   headers: new HttpHeaders({'Content-Type':'application/json'})}
  
  constructor(private _http:HttpClient, private _constant: AppConstantService) 
  { 
    this.baseurl = this._constant.HOST_NAME + "/bctuser";
    this.options = this._constant.options;
  }

  getBctLogUsers()
  {
    return this._http.get(this.baseurl+'/users', this.optionsnew)
    .catch(this.errorHandler);
  }

  getAllBctLogUsers()
  {
    return this._http.get(this.baseurl+'/all/bctlogusers', this.optionsnew)
    .catch(this.errorHandler);
  }

  getBctLogUser(userId:String)
  {
    return this._http.get(this.baseurl+'/user/'+userId, this.optionsnew)
    .catch(this.errorHandler);
  }

  getUsers()
  {
    return this._http.get(this.baseurl+'/users', this.optionsnew)
    .catch(this.errorHandler);
  }

  getUser(id:number)
  {
    return this._http.get(this.baseurl+'/user/'+id, this.optionsnew)
    .catch(this.errorHandler);
  }

  deleteUser(id:number)
  {
    return this._http.delete(this.baseurl+'/user/'+id, this.optionsnew)
    .catch(this.errorHandler);
  }

  createUser(user:User)
  {
    return this._http.post(this.baseurl+'/user',JSON.stringify(user), this.optionsnew)
    .catch(this.errorHandler);
  }

  updateUser(user:User)
  {
    return this._http.put(this.baseurl+'/user',JSON.stringify(user), this.optionsnew)
    .catch(this.errorHandler);
  }

  getUserTypeList()
  {
    return this._http.get(this.baseurl+'/roles', this.optionsnew)
    .catch(this.errorHandler);
  }

  getProjectList()
  {
    console.log("getProjectList");
     
    return this._http.get(this.baseurl+'/projectlist', this.optionsnew)
    .catch(this.errorHandler);
  }
  createBctLogUser(bctloguser:Bctloguser)
  {
    console.log("createBctLogUser "+JSON.stringify(bctloguser));
    return this._http.post(this.baseurl+'/user/save',JSON.stringify(bctloguser), this.optionsnew)
    .catch(this.errorHandler);
  }
  deleteBctLogUser(userid:string)
  {
    return this._http.delete(this.baseurl+'/user/delete/'+userid, this.optionsnew)
    .catch(this.errorHandler);
  }

  logOut()
  {
    return this._http.post('/weeklystatusui/login',JSON.stringify(""), this.optionsnew)
    .catch(this.errorHandler);
  }

  errorHandler(error:Response)
  {
    return Observable.throw(error||"Server Error");
  }

  setter(user:User){
    this.user = user;
  }

  getter(){
    return this.user;
  }
}
