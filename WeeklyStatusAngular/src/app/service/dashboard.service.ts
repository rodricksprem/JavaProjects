import { Injectable } from '@angular/core';

import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/Observable/throw';
import {BUSearch} from '../dao/busearch';
import { AppConstantService } from '../constant/app-constant.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  private baseurl:string;
  private options;
  readonly optionsnew =    {   headers: new HttpHeaders({'Content-Type':'application/json'})}
  constructor(private _http:HttpClient, private _constant: AppConstantService) 
  { 
    this.baseurl = this._constant.HOST_NAME + "/bctdashboard";
    this.optionsnew = this._constant.options;
  }
  getWeeks()
  {
    console.log("  getWeeks() "+this.baseurl+'/chartData/getWeeks');
    return this._http.get(this.baseurl+'/chartData/getWeeks', this.optionsnew).catch(this.errorHandler)
  }

  getChartDataTesla()
  {
    console.log("  getChartDataTesla() "+this.baseurl+'/chartData/tesla');
    return this._http.post(this.baseurl+'/chartData/tesla', this.optionsnew)
    .catch(this.errorHandler);
  }

  getChartDataMeeza()
  {
    console.log("  getChartDataMeeza()"+this.baseurl+'/chartData/meeza');
    return this._http.post(this.baseurl+'/chartData/meeza', this.optionsnew)
    .catch(this.errorHandler);
  }

  getChartDataCGTechImpl()
  {
    console.log("  getChartDataCGTechImpl()"+this.baseurl+'/chartData/cgtechimpl');
    return this._http.post(this.baseurl+'/chartData/cgtechimpl', this.optionsnew)
    .catch(this.errorHandler);
  }

  getChartDataIVMSIMpl()
  {
    console.log("  getChartDataIVMSIMpl()"+this.baseurl+'/chartData/ivmsimpl');
    return this._http.post(this.baseurl+'/chartData/ivmsimpl', this.optionsnew)
    .catch(this.errorHandler);
  }

  

  getAllDonutChartData()
  {
    console.log("getAllDonutChartData:");
    return this._http.get(this.baseurl+'/donut', this.optionsnew)
    .catch(this.errorHandler);
  }

  getAllGraphDetails()
  {
    console.log("getAllGraphDetails:");
    return this._http.get(this.baseurl+'/dashboardDetails', this.optionsnew)
    .catch(this.errorHandler);
  }

  getAllBarChartData()
  {
    return this._http.get(this.baseurl+'/logging/bar', this.optionsnew)
    .catch(this.errorHandler);
  }

  getNotificationBarChartData()
  {
    return this._http.get(this.baseurl+'/notification/bar', this.optionsnew)
    .catch(this.errorHandler);
  }
  getAllSankeyGraphData()
  {
    console.log("getAllSankeyGraphData:");
    return this._http.get(this.baseurl+'/graph/sankygraph',  this.optionsnew).catch(this.errorHandler);
  }
  getAllDonutChartDataBySearch(buSearch : BUSearch)
  {
    return this._http.post(this.baseurl+'/donut', JSON.stringify(buSearch),this.optionsnew)
    .catch(this.errorHandler);
  }
  getAllBarChartDataBySearch(buSearch : BUSearch)
  {
    return this._http.post(this.baseurl+'/logging/bar', JSON.stringify(buSearch),this.optionsnew)
    .catch(this.errorHandler);
  }
  getNotificationBarChartDataBySearch(buSearch : BUSearch)
  {
    return this._http.post(this.baseurl+'/notification/bar', JSON.stringify(buSearch),this.optionsnew)
    .catch(this.errorHandler);
  }
  getSankeyGraphDataBySearch(buSearch : BUSearch)
  {
    return this._http.post(this.baseurl+'/graph/sankygraph/search', JSON.stringify(buSearch),this.optionsnew)
    .catch(this.errorHandler);
  }
 
  
 /* getAllDonutChartData()
  {
    return this._http.get(this.baseurl+'/donut', this.optionsnew)
    .catch(this.errorHandler);
  }

  getDonutChartDataByBUnit(bunit:String)
  {
    return this._http.get(this.baseurl+'/donut/'+bunit, this.optionsnew)
    .catch(this.errorHandler);
  }

  getDonutChartDataByBUnitAndAppName(bunit:String, appName:String)
  {
    return this._http.get(this.baseurl+'/donut/'+bunit+'/'+appName, this.optionsnew)
    .catch(this.errorHandler);
  }

  getAllBarChartData()
  {
    return this._http.get(this.baseurl+'/bar', this.optionsnew)
    .catch(this.errorHandler);
  }

  getBarChartDataByBUnit(bunit:String)
  {
    return this._http.get(this.baseurl+'/bar/'+bunit, this.optionsnew)
    .catch(this.errorHandler);
  }

  getBarChartDataByBUnitAndAppName(bunit:String, appName:String)
  {
    return this._http.get(this.baseurl+'/bar/'+bunit+'/'+appName, this.optionsnew)
    .catch(this.errorHandler);
  }

  getNotificationBarChartData()
  {
    return this._http.get(this.baseurl+'/notification/bar', this.optionsnew)
    .catch(this.errorHandler);
  }

  getNotificationBarChartDataByBUnit(bunit:String)
  {
    return this._http.get(this.baseurl+'/notification/bar/'+bunit, this.optionsnew)
    .catch(this.errorHandler);
  }

  getNotificationBarChartDataByBUnitAndAppName(bunit:String, appName:String)
  {
    return this._http.get(this.baseurl+'/notification/bar/'+bunit+'/'+appName, this.optionsnew)
    .catch(this.errorHandler);
  }*/

  errorHandler(error:Response)
  {
    return Observable.throw(error||"Server Error");
  }
}
