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
  getWeeks(projectId:Number)
  {
    console.log("  getWeeks() for "+this.baseurl+'/chartData/getWeeks/'+projectId);
    return this._http.get(this.baseurl+'/chartData/getWeeks/'+projectId, this.optionsnew).catch(this.errorHandler)
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

  getChartDataTeslaNwOp()
  {
    console.log("  getChartDataTeslaNwOp()"+this.baseurl+'/chartData/teslanetworkop');
    return this._http.post(this.baseurl+'/chartData/teslanetworkop', this.optionsnew)
    .catch(this.errorHandler);
  }

  
  getChartDataOAB()
  {
    console.log("  getChartDataTeslaNwOp()"+this.baseurl+'/chartData/oabsupport');
    return this._http.post(this.baseurl+'/chartData/oabsupport', this.optionsnew)
    .catch(this.errorHandler);
  }

  errorHandler(error:Response)
  {
    return Observable.throw(error||"Server Error");
  }
}
