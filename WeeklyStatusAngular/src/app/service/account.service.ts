import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/Observable/throw';
import { AppConstantService } from '../constant/app-constant.service';
import { TaskDetail } from '../dao/TaskDetail';
import { ProjectDetail } from '../dao/ProjectDetail';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  
  private baseurl:string;
  private options;
  private accountID;
  readonly optionsnew =    {   headers: new HttpHeaders({'Content-Type':'application/json'})}
  constructor(private _http:HttpClient, private _constant: AppConstantService) 
  { 
    this.baseurl = this._constant.HOST_NAME + "/bctaccount";
    this.options = this._constant.options;
  }

 
  getAccountList() {
    console.log("getAccountList "+this.baseurl+'/accountList');
    
    return this._http.post(this.baseurl+'/accountList', this.optionsnew).catch(this.errorHandler);
  }



  getAccountDetails()
  {
    console.log("getAccountDetails "+this.baseurl+'/accountDetails');
    return this._http.post(this.baseurl+'/accountDetails', this.optionsnew).catch(this.errorHandler);
  }

  getWeeklyDurationOfProject(projectId)
  {
    console.log("getWeeklyDurationOfProject "+'/bctprojectstatus/getWeeks/{projectId}');
    return this._http.get('/bctprojectstatus/getWeeks/'+projectId, this.optionsnew).catch(this.errorHandler);
  }


  getWeeklyDuration()
  {
    console.log("getWeeklyDuration "+this.baseurl+'/weekduration');
    return this._http.get(this.baseurl+'/weekduration', this.optionsnew).catch(this.errorHandler);
  }

 
  
  getProjectNameByAccount(accountID:String)
  {
    console.log(this.baseurl+'/name/'+accountID);
    return this._http.get(this.baseurl+'/name/'+accountID, this.optionsnew).catch(this.errorHandler);
  }
  getProjectFields(projectId:number)
  {
    console.log(this.baseurl+'/fields/'+projectId);
    return this._http.post(this.baseurl+'/fields/'+projectId, this.optionsnew).catch(this.errorHandler);
  }

   
  updateProjectStatusDetails(taskDetail:TaskDetail)
  {
    console.log("updateProjectStatusDetails :"+JSON.stringify(taskDetail));
    return this._http.post(this.baseurl+'/projectStatusDetail/update',JSON.stringify(taskDetail), this.optionsnew)
    .catch(this.errorHandler);
  }
  removeProjectStatusDetail(projectStatusIds:Array<number>)
  {
    console.log("removeProjectStatusDetail :"+JSON.stringify(projectStatusIds));
    return this._http.post(this.baseurl+'/projectStatusDetail/delete',JSON.stringify(projectStatusIds), this.optionsnew)
    .catch(this.errorHandler);
  }

  createTaskDetail(projectDetail:ProjectDetail)
  {
    console.log("createTaskDetail :"+JSON.stringify(projectDetail));
    return this._http.post<TaskDetail>(this.baseurl+'/taskDetail/create',JSON.stringify(projectDetail), this.optionsnew)
    .catch(this.errorHandler);
  }
  removeTaskDetail(taskId)
  {
    console.log("removeTaskDetail :"+taskId);
    return this._http.post(this.baseurl+'/taskDetail/delete/'+taskId, this.optionsnew)
    .catch(this.errorHandler);
  }
  
  createProjectDetail(projectDetail:ProjectDetail)
  {
    console.log("createProjectDetail :"+JSON.stringify(projectDetail));
    return this._http.post(this.baseurl+'/projectDetail/create',JSON.stringify(projectDetail), this.optionsnew)
    .catch(this.errorHandler);
  }

  updateProjectDetail(projectDetail:ProjectDetail)
  {
    console.log("updateProjectDetail :"+JSON.stringify(projectDetail));
    return this._http.post(this.baseurl+'/projectDetail/update',JSON.stringify(projectDetail), this.optionsnew)
    .catch(this.errorHandler);
  }
  
  removeWeekDuration(projectId:number ,weekDuration:string)
  {
    console.log("removeWeekDuration :"+weekDuration);
    return this._http.post(this.baseurl+'/weekDuration/delete/'+projectId+"/"+weekDuration, this.optionsnew)
    .catch(this.errorHandler);
  }
  errorHandler(error:Response)
  {
    return Observable.throw(error||"Server Error");
  }
}
