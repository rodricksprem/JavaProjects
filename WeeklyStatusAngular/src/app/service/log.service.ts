import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/Observable/throw';
import {BctLog} from '../dao/bct-log';
import {BctLogSearch} from '../dao/bct-log-search';
import {BUSearch} from '../dao/busearch';
import { AppConstantService } from '../constant/app-constant.service';
import { ProjectDetail } from '../dao/ProjectDetail';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LogService {

  private baseurl:string;
  private options;
  private bctLog = new BctLog();
  private bctLogSearch = new BctLogSearch();
  readonly optionsnew =    {   headers: new HttpHeaders({'Content-Type':'application/json'})}
  
  constructor(private _http:HttpClient, private _constant: AppConstantService) 
  { 
    this.baseurl = this._constant.HOST_NAME + "/bctprojectstatus";
    this.options = this._constant.options;
  }

  getDetailedLogDetails(projectID:String)
  {
    return this._http.get(this.baseurl+'/statusdetail/'+projectID, this.optionsnew)
    .catch(this.errorHandler);
  }
  

  getSearchLogDetails(projectDetail : ProjectDetail)
  {
    console.log("getSearchLogDetails ");
    console.log(projectDetail);
    return this._http.post(this._constant.HOST_NAME +'/bctaccount/seachproject/weekly',JSON.stringify(projectDetail),this.optionsnew)
    .catch(this.errorHandler);
  }

  errorHandler(error:Response)
  {
    return Observable.throw(error||"Server Error");
  }

  setter(bctLog:BctLog){
    this.bctLog = bctLog;
  }

  getter(){
    return this.bctLog;
  }
}
