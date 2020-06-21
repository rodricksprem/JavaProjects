import { Component, OnInit ,Input,Output,EventEmitter} from '@angular/core';
import { Router } from '@angular/router';
import { UserSessionService } from '../../service/user-session.service';
declare var  $:any;

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css']
})
export class SideBarComponent implements OnInit {
  @Output() loggingEvent = new EventEmitter<string>();
  @Output() notificationEvent = new EventEmitter<string>();
  @Output() applicationEvent = new EventEmitter<string>();
  @Output() userEvent = new EventEmitter<string>();
  @Output() appInfoEvent = new EventEmitter<string>();
  @Output() dashBoardEvent = new EventEmitter<string>();
  @Output() dashBoard2Event = new EventEmitter<string>();
  @Output() generalInstructionEvent = new EventEmitter<string>();
  @Output() newInterfaceEvent = new EventEmitter<string>();
  @Output() projectStatusEvent = new EventEmitter<string>();
  @Output() dataPurgeEvent = new EventEmitter<string>();
  userType: number;


  constructor(private _router:Router,private _userSessionService:UserSessionService) {
    console.log("sidebar constructor");
    this.userType = this._userSessionService.getUserType();
 
   }

  ngOnInit() {
    this.userType = this._userSessionService.getUserType();
    console.log("userType:"+this.userType);
    

  
    
    $(document).ready(function(){
      $('.sidebar ul li.parent').click(function(){
      $('li.parent').removeClass("active");
      $(this).addClass("active");
    });
    });
 
    $('.treeview').click(function(){
      $(this).addClass("sub_menu");
    });

    $('.sidebar-menu li.parent').click(function(){
      $('.treeview').removeClass("sub_menu");
    });
  }


  showLogging() {
    //console.log("dfdfgs");
    //debugger;
    this.loggingEvent.emit("true");
    this.notificationEvent.emit("false");
    this.applicationEvent.emit("false");
    this.userEvent.emit("false");
    this.appInfoEvent.emit("false");
    this.dashBoardEvent.emit("false");
    this.dashBoard2Event.emit("false");
    this.generalInstructionEvent.emit("false");
    this.newInterfaceEvent.emit("false");
    
  this.projectStatusEvent.emit("false");
    this.dataPurgeEvent.emit("false");
      }
  
  showNotification(){
    //debugger;
    console.log("showNotification in sidebar component")
    this.loggingEvent.emit("false");
    this.notificationEvent.emit("true");
    this.applicationEvent.emit("false");
    this.userEvent.emit("false");
    this.appInfoEvent.emit("false");
    this.dashBoardEvent.emit("false");
    this.dashBoard2Event.emit("false");
    this.generalInstructionEvent.emit("false");
    this.newInterfaceEvent.emit("false");
    this.projectStatusEvent.emit("false");
    this.dataPurgeEvent.emit("false");
  }

  showApplication(){
    this.loggingEvent.emit("false");
    this.notificationEvent.emit("false");
    this.applicationEvent.emit("true");
    this.userEvent.emit("false");
    this.appInfoEvent.emit("false");
    this.dashBoardEvent.emit("false");
    this.dashBoard2Event.emit("false");
    this.generalInstructionEvent.emit("false");
    this.newInterfaceEvent.emit("false");
    this.projectStatusEvent.emit("false");
    this.dataPurgeEvent.emit("false");
  }

  showUser(){
    this.loggingEvent.emit("false");
    this.notificationEvent.emit("false");
    this.applicationEvent.emit("false");
    this.userEvent.emit("true");
    this.appInfoEvent.emit("false");
    this.dashBoardEvent.emit("false");
    this.dashBoard2Event.emit("false");
    this.generalInstructionEvent.emit("false");
    this.newInterfaceEvent.emit("false");
    this.projectStatusEvent.emit("false");
    this.dataPurgeEvent.emit("false");
 }

  showAppType(){
    this.loggingEvent.emit("false");
    this.notificationEvent.emit("false");
    this.applicationEvent.emit("false");
    this.userEvent.emit("false");
    this.appInfoEvent.emit("false");
    this.dashBoardEvent.emit("false");
    this.dashBoard2Event.emit("false");
    this.generalInstructionEvent.emit("false");
    this.newInterfaceEvent.emit("false");
    this.projectStatusEvent.emit("false");
    this.dataPurgeEvent.emit("false");
  }

  
  showAppInfo(){
    this.loggingEvent.emit("false");
    this.notificationEvent.emit("false");
    this.applicationEvent.emit("false");
    this.userEvent.emit("false");
    this.appInfoEvent.emit("true");
    this.dashBoardEvent.emit("false");
    this.dashBoard2Event.emit("false");
    this.generalInstructionEvent.emit("false");
    this.newInterfaceEvent.emit("false");
    this.projectStatusEvent.emit("false");
    this.dataPurgeEvent.emit("false");
  }

  showDashboard(){
    this.loggingEvent.emit("false");
    this.notificationEvent.emit("false");
    this.applicationEvent.emit("false");
    this.userEvent.emit("false");
    this.appInfoEvent.emit("false");
    this.dashBoardEvent.emit("true");
    this.dashBoard2Event.emit("false");
    this.generalInstructionEvent.emit("false");
    this.newInterfaceEvent.emit("false");
    this.projectStatusEvent.emit("false");
    this.dataPurgeEvent.emit("false");
  }
  showDashboard2(){
    this.loggingEvent.emit("false");
    this.notificationEvent.emit("false");
    this.applicationEvent.emit("false");
    this.userEvent.emit("false");
    this.appInfoEvent.emit("false");
    this.dashBoardEvent.emit("false");
    this.dashBoard2Event.emit("true");
    this.generalInstructionEvent.emit("false");
    this.newInterfaceEvent.emit("false");
    this.projectStatusEvent.emit("false");
    this.dataPurgeEvent.emit("false");
  }
  showGeneralinfo(){
    this.loggingEvent.emit("false");
    this.notificationEvent.emit("false");
    this.applicationEvent.emit("false");
    this.userEvent.emit("false");
    this.appInfoEvent.emit("false");
    this.dashBoardEvent.emit("false");
    this.dashBoard2Event.emit("false");
    this.generalInstructionEvent.emit("true");
    this.newInterfaceEvent.emit("false");
    this.projectStatusEvent.emit("false");
    this.dataPurgeEvent.emit("false");
  }

  showNewInterface(){
    this.loggingEvent.emit("false");
    this.notificationEvent.emit("false");
    this.applicationEvent.emit("false");
    this.userEvent.emit("false");
    this.appInfoEvent.emit("false");
    this.dashBoardEvent.emit("false");
    this.dashBoard2Event.emit("false");
    this.generalInstructionEvent.emit("false");
    this.newInterfaceEvent.emit("true");
    this.projectStatusEvent.emit("false");
    this.dataPurgeEvent.emit("false");
 }
 showProjectStatus(){
    this.loggingEvent.emit("false");
    this.notificationEvent.emit("false");
    this.applicationEvent.emit("false");
    this.userEvent.emit("false");
    this.appInfoEvent.emit("false");
    this.dashBoardEvent.emit("false");
    this.dashBoard2Event.emit("false");
    this.generalInstructionEvent.emit("false");
    this.newInterfaceEvent.emit("false");
    this.projectStatusEvent.emit("true");
    console.log("showProjectStatus ");
    this.dataPurgeEvent.emit("false");
}

 showDataPurge(){
   console.log("showDataPurge");
  this.loggingEvent.emit("false");
  this.notificationEvent.emit("false");
  this.applicationEvent.emit("false");
  this.userEvent.emit("false");
  this.appInfoEvent.emit("false");
  this.dashBoardEvent.emit("false");
  this.dashBoard2Event.emit("false");
  this.generalInstructionEvent.emit("false");
  this.newInterfaceEvent.emit("false");
  this.projectStatusEvent.emit("false");
  this.dataPurgeEvent.emit("true");
 }
}
