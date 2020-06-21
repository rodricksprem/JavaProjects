import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-dash-board',
  templateUrl: './dash-board.component.html',
  styleUrls: ['./dash-board.component.css']
})
export class DashBoardComponent implements OnInit {

  
    dashBoardFlagparent:boolean = true;
    dashBoard2Flagparent:boolean = false;
    logFlagparent:boolean = false;
    applicationFlagparent:boolean = false;
    userFlagparent:boolean = false;
    appInfoFlagparent:boolean = false;
    logDetailsFlag:any = null;
    bioLogSearchFlag:any = null;
    bioNotifySearchFlag:any = null;
    newInterfaceFlagparent:boolean = false;
    projectStatusFlagparent:boolean = false;
  info: { token: string; username: string; authorities: string[]; };
    //uname:any = null;
    
  //constructor(private _router:Router) {
    constructor(private _router:Router,  private route: ActivatedRoute) {
     console.log("************ "+this.route.queryParams.subscribe(params => { params['logDetailsFlag'] || false;}));
    /*console.log("*****BioTransId******* "+this.route.snapshot.paramMap.get('BioTransId'));
    console.log("*****notifyHistoryId******* "+this.route.snapshot.paramMap.get('NotifyHistoryId'));
    //console.log("*****uname******* "+this.route.snapshot.paramMap.get('uname'));

    this.logDetailsFlag = this.route.snapshot.paramMap.get('BioTransId');
    this.notificationDetailsFlag = this.route.snapshot.paramMap.get('NotifyHistoryId');
    //this.uname = this.route.snapshot.paramMap.get('uname');
*/
      //if(this.routeFlag != null && this.routeFlag == "true")
      if(this.logDetailsFlag != null)
      {
        console.log("******logDetails****** ");
        this.applicationFlagparent = false;
        this.userFlagparent = false;
        this.logFlagparent = false;
        this.appInfoFlagparent = false;
        this.dashBoardFlagparent = false;
        this.dashBoard2Flagparent = false;
        this.newInterfaceFlagparent = false;
        this.projectStatusFlagparent=false;
     }
     else
     {
       this.bioLogSearchFlag = this.route.snapshot.paramMap.get('logSearchFlag');
       this.bioNotifySearchFlag = this.route.snapshot.paramMap.get('notificationSearchFlag');

       if(this.bioLogSearchFlag != null && this.bioLogSearchFlag == "true")
       {
        console.log("******logSearch****** ");
        this.applicationFlagparent = false;
        this.userFlagparent = false;
        this.logFlagparent = true;
        this.appInfoFlagparent = false;
        this.dashBoardFlagparent = false;
        this.dashBoard2Flagparent = false;
        this.newInterfaceFlagparent = false;
        this.projectStatusFlagparent=false;
      }
     }
   }

  ngOnInit() {
    /*this.dashBoardFlagparent = false;
    this.notificationFlagparent = true;
    console.log("dashboard componenet ngOnInit():");
    this.receiveNotification(true);*/

   
  }
  receiveMessage($event) {
    //debugger;
        if($event=="true"){
        this.applicationFlagparent = false;
        this.userFlagparent = false;
        this.logFlagparent = true;
        this.appInfoFlagparent = false;
        this.dashBoardFlagparent = false;
        this.dashBoard2Flagparent = false;
        this.newInterfaceFlagparent = false;
        this.projectStatusFlagparent = false;  
      }
        else {
          //this.notificationFlagparent = true;
          //this.logFlagparent = false;
        }
  }

  
  receiveUser($event) {
    //debugger;
    if($event=="true"){
      this.applicationFlagparent = false;
      this.userFlagparent = true;
      this.logFlagparent = false;
      this.appInfoFlagparent = false;
      this.dashBoardFlagparent = false;
      this.dashBoard2Flagparent = false;
      this.newInterfaceFlagparent = false;
      this.projectStatusFlagparent = false;
      }
      else {
        //this.logFlagparent = true;
        //this.notificationFlagparent = false;
      }
  }

    

  
  receiveAppInfo($event) {
    //debugger;
    if($event=="true"){
      this.applicationFlagparent = false;
      this.userFlagparent = false;
      this.logFlagparent = false;
      this.appInfoFlagparent = false;
      this.dashBoardFlagparent = false;
      this.dashBoard2Flagparent = false;
      this.newInterfaceFlagparent = false;
      this.projectStatusFlagparent = false;
      }
      else {
        // this.logFlagparent = true;
        // this.notificationFlagparent = false;
      }
  }

  receiveDashBoard($event) {
    //debugger;
    if($event=="true"){
      this.applicationFlagparent = false;
      this.userFlagparent = false;
      this.logFlagparent = false;
      this.appInfoFlagparent = false;
      this.dashBoardFlagparent = true;
      this.dashBoard2Flagparent = false;
      this.newInterfaceFlagparent = false;
      this.projectStatusFlagparent = false;
      }
      else {
        // this.logFlagparent = true;
        // this.notificationFlagparent = false;
      }
  }

  receiveDashBoard2($event) {
    //debugger;
    if($event=="true"){
      this.applicationFlagparent = false;
      this.userFlagparent = false;
      this.logFlagparent = false;
      this.appInfoFlagparent = false;
      this.dashBoardFlagparent = false;
      this.dashBoard2Flagparent = true;
      this.newInterfaceFlagparent = false;
      this.projectStatusFlagparent = false;
      }
      else {
        // this.logFlagparent = true;
        // this.notificationFlagparent = false;
      }
  }
  receiveNewInterface($event) {
    //debugger;
    console.log("receiveNewInterface");
    if($event=="true"){
      this.applicationFlagparent = false;
      this.userFlagparent = false;
      this.logFlagparent = false;
      this.appInfoFlagparent = false;
      this.dashBoardFlagparent = false;
      this.dashBoard2Flagparent = false;
      this.newInterfaceFlagparent = true;
      this.projectStatusFlagparent = false;
      }
      else {
        // this.logFlagparent = true;
        // this.notificationFlagparent = false;
      }
  }

  receiveProjectStatus($event) {
    //debugger;
    console.log("receiveProjectStatus");
    if($event=="true"){
      this.applicationFlagparent = false;
      this.userFlagparent = false;
      this.logFlagparent = false;
      this.appInfoFlagparent = false;
      this.dashBoardFlagparent = false;
      this.dashBoard2Flagparent = false;
      this.newInterfaceFlagparent = false;
      this.projectStatusFlagparent = true;
      }
      else {
        // this.logFlagparent = true;
        // this.notificationFlagparent = false;
      }

    }
  
}
