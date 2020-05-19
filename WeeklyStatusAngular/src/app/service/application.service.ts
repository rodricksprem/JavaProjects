import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/Observable/throw';
import {Account} from '../dao/account';
import { AppConstantService } from '../constant/app-constant.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { ProjectDetail } from '../dao/ProjectDetail';
  
@Injectable({
  providedIn: 'root'
})
export class ApplicationService {

  private baseurl:string;
  private options;
  private account = new Account();
  
  readonly optionsnew =    {   headers: new HttpHeaders({'Content-Type':'application/json'})}
  

  constructor(private _http:HttpClient, private _constant: AppConstantService) 
  { 
    this.baseurl = this._constant.HOST_NAME + "/bctprojectstatus";
    this.options= this._constant.options;
  }
  getWeeks()
  {
    console.log("  getWeeks() "+'bctdashboard/chartData/getWeeks/');
    return this._http.get(this._constant.HOST_NAME+'/bctdashboard/chartData/getWeeks', this.optionsnew)
    .catch(this.errorHandler);
  }

  getAllProjectDetails()
  {
    console.log("getAllProjectDetails ..");
  
   
   
    return this._http.get(this.baseurl+'/allprojects',this.optionsnew).catch(this.errorHandler);
   
  }
 

  getProjectList()
  {
    return this._http.get(this.baseurl+'/allprojects', this.optionsnew)
    .catch(this.errorHandler);
  }

  errorHandler(error:Response)
  {
    return Observable.throw(error||"Server Error");
  }

  setter(account:Account){
    this.account = account;
  }

  getter(){
    return this.account;
  }
}
