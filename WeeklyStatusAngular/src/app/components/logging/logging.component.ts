import { Component, OnInit ,Input,Output,EventEmitter, ViewChild, SystemJsNgModuleLoader } from '@angular/core';
import { LogService } from '../../service/log.service';
import { BctLog } from '../../dao/bct-log';
import { ApplicationService } from '../../service/application.service';
import { AccountService } from '../../service/account.service';
import { BctLogSearch } from '../../dao/bct-log-search';
import { Router, ActivatedRoute, ParamMap  } from '@angular/router';
import { BctLogDetails } from '../../dao/bct-log-details';
import { BUSearch } from '../../dao/busearch';
import { DatePipe } from '@angular/common';
import { Subscriber } from 'rxjs';
import {GridOptions} from 'ag-grid/main';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';
import { UserSessionService } from '../../service/user-session.service';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { ProjectDetail } from 'src/app/dao/ProjectDetail';
import { e } from '@angular/core/src/render3';
import { Account } from 'src/app/dao/account';
import { Fields } from 'src/app/dao/fields';
import { TaskDetail } from 'src/app/dao/TaskDetail';
import { ProjectStatusDetailNew } from 'src/app/dao/ProjectStatusDetailNew';
import { WeekStatus } from 'src/app/dao/WeekStatus';
//declare var  $:any;

@Component({
  selector: 'app-logging',
  templateUrl: './logging.component.html',
  styleUrls: ['./logging.component.css']
})

export class LoggingComponent implements OnInit {
 
  bioLog:BctLog[];
  rowData:any = [];
  columnDefs:any=[];
  statusList:any = [];
  keyList:any = [];
  private bioLogSearch = new BctLogSearch();
  bctLogDetails1:BctLogDetails;
  title = 'Logging Search';
  public keyFlag:boolean = false;
  public keyBtnFlag:boolean = false;
  public buttonName:any = 'Quick Search';
  dropdownSettings: any = {};
  showFilter = false;
  status:string = "";
  overlayLoadingTemplate:any;
  private gridApi;
  projectStatusDetailsList:ProjectStatusDetailNew[];
  taskDetailList:TaskDetail[];
  fields:Fields;
  private busearch = null;
  public maxDate:Date = new Date();
  limitSelection = false;
  @Output() dashBoardEvent = new EventEmitter<string>();
  @Output() dashBoard2Event = new EventEmitter<string>();
  accountList:any= [];
  projectList:ProjectDetail[];
  projectNameList: any=[];
  projectStatusColumns: string[];
  projectID: any;
  projectName: any;
  weekDurationList: string[];
  frameworkComponents:any;
  selectedProject:ProjectDetail= new ProjectDetail();
    //private  sideBarComponent:SideBarComponent;
    projectDetail = new ProjectDetail();
     account = new Account();
  constructor(private _userSessionService:UserSessionService,private spinnerService: Ng4LoadingSpinnerService,private _logService:LogService, private _applicationService:ApplicationService, private _accountService:AccountService, private _router:Router, private _datePipe: DatePipe,private route: ActivatedRoute) { }
  setDefaultValue(name){
  
 }
 setDefaultValues(){

}
toogleShowFilter() {
  this.showFilter = !this.showFilter;
  this.dropdownSettings = Object.assign({}, this.dropdownSettings, { allowSearchFilter: this.showFilter });
}
handleLimitSelection() {
  if (this.limitSelection) {
      this.dropdownSettings = Object.assign({}, this.dropdownSettings, { limitSelection: 2 });
  } else {
      this.dropdownSettings = Object.assign({}, this.dropdownSettings, { limitSelection: null });
  }
}
  ngOnInit() {
    this.spinnerService.show();
    this.dropdownSettings = {
      singleSelection: false,
      idField: 'status',
      textField: 'status',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      allowSearchFilter: this.showFilter,
      itemsShowLimit: 2
  };
  this.frameworkComponents ={
 
    statusCellRenderer: StatusCellRenderer,
  }
  
    this.columnDefs = [
      {headerName: 'AccountID', field: 'accountID', width:100  },
      {headerName: 'Account Name', field: 'accountName', width:100 },
      {headerName: 'Project Name', field: 'projectName', width:100 ,
      
      cellRenderer: function (params) {
        var projectName = params.data.projectName;
       /* if (params.data.serviceInvokerStatus !="MULTI") {
            var html = ""+status;
        } else {
          var html = '<a  data-toggle="modal" data-target="#targetSystemStatus" href="#">click</a>';
        }*/
        var html =  '<a data-toggle="modal" data-target="#loggingDetailsModal" href="#">'+projectName+'</a>';
        return html;
    
    }
  },
      {headerName: 'Status', field: 'projectStatus', width:100 ,
      cellRenderer:  function (params) {
        
        if (params.value !== "" || params.value !== undefined || params.value !== null) {
        console.log("here ...");
        var imgForMood = params.value === 1 ? './assets/img/green.png' :params.value === 2 ? './assets/img/orange.png' :'./assets/img/red.png';
        
        var html =  '<img width="20px" src="' + imgForMood + '" />';
          
        return html;  
      }else{
        return params.value;
      }
    }
  },
      {headerName: 'ProjectType', field: 'projectType', width:100 },
      {headerName: 'Week Duration', field: 'weekDuration', width:100,editable: true},
  {headerName: 'Remarks', field: 'remarks', width:100,editable: true},
      /*{headerName: 'View', field: '', width:80,
        template:
      },*/
      
            
           
      
    
    ];
  
    function StatusCellRenderer() {
      }
      StatusCellRenderer.prototype.getGui = function () {
        return this.eGui;
    };
      StatusCellRenderer.prototype.init = function (params) {
         console.log("here inside init ..");
          this.eGui = document.createElement('span');
          if (params.value !== "" || params.value !== undefined || params.value !== null) {
              var imgForMood = params.value === 1 ? 'https://raw.githubusercontent.com/ag-grid/ag-grid/master/grid-packages/ag-grid-docs/src/images/smiley.png' : 'https://raw.githubusercontent.com/ag-grid/ag-grid/master/grid-packages/ag-grid-docs/src/images/smiley-sad.png';
              this.eGui.innerHTML = '<img width="20px" src="' + imgForMood + '" />';
          }
          console.log(params.value);
      };      
    this.overlayLoadingTemplate =
    '<span class="ag-overlay-loading-center">Please wait while your rows are loading</span>';
   // if(this._accountService.getAccountId()!=null && this._accountService.getAccountId().trim() != "") {
      console.log("here ...");
    
  //    }
  
  
  this._accountService.getAccountList().subscribe((accountListValues)=>
   {
   //console.log("bunitListValues : "+bunitListValues);
   console.log("here getAccountList ..");
     this.accountList = accountListValues;
     console.log(this.accountList);
    this.setDefaultValue("ALL");
     }),(error)=>{
     console.log(error);
   }
  
  
 
  }


  onGridReady(params): void {
    this.gridApi = params.api;
  // this.gridApi.showLoadingOverlay();
  
  }

  

  onCellClicked(event: any) 
  {
    console.log("onCellClicked "+event.colDef.headerName);
    /*if(event.colDef.headerName == "View")
    {
      this.status = "View";
    }
    else if(event.colDef.headerName == "View Tab")
    {
      this.status = "View Tab";
    }*/
    if(event.colDef.headerName == "Status")
    {
      this.status = "Status";
      console.log("here ..")
    }
    /*else if(event.colDef.headerName == "Status Tab")
    {
      this.status = "Status Tab";
    }
    if(event.colDef.headerName == "Notification")
    {
      this.status = "Notification";
    }*/
  }

  formWeek(inputDate:Date){
    var startDate:Date = new Date(inputDate);
    var firstday =startDate.getDate() - startDate.getDay() + (startDate.getDay() === 0 ? -6 : 1); 
    var firstDate = new Date(startDate.setDate(firstday));
  //  console.log( "firstDate "+firstDate)
   //Apr 20,2020-Apr 26,2020
    var lastday = startDate.getDate() - (startDate.getDay() - 1) + 6;
    var lastDate = new Date(startDate.setDate(lastday));
   // console.log( "lastDate "+ lastDate)
    
    var monthFirstDate = firstDate.toLocaleString('default', { month: 'short' });   
   var monthLastDate = lastDate.toLocaleString('default', { month: 'short' });   
   var dayFirstDate=firstDate.getDate()<10?"0"+firstDate.getDate():firstDate.getDate();
   var dayLastDate=lastDate.getDate()<10?"0"+lastDate.getDate():lastDate.getDate();
    var weekDuration= monthFirstDate+" "+ dayFirstDate+ ","+firstDate.getFullYear()+"-"+monthLastDate+" "+ dayLastDate+ ","+lastDate.getFullYear()
    return weekDuration;
  }
 
  onRowClicked(event: any) 
  { 
    console.log("onRowClicked === "+event.data.projectID+ " weekduration "+event.data.weekDuration);
    this._logService.getDetailedLogDetails(event.data.projectID).subscribe((logDetails:TaskDetail[])=>
    {
     //debugger;
     console.log(logDetails);
      this.projectID=event.data.projectID;
      this.projectName=event.data.projectName;
     //this.taskDetailList=logDetails;
     this.taskDetailList=[];
     logDetails.forEach(td=>{
      console.log("task detail date"+td.taskCreationDate);
      var weekDuration = this.formWeek(td.taskCreationDate);
      console.log("task detail weekduration"+weekDuration);
     if(event.data.weekDuration==weekDuration  ){
    
        this.taskDetailList.push(td);
      
     }
     }) 
      var index =0;
     
   
      console.log("************* "+this.taskDetailList.length);
   
     }),(error)=>{
     console.log(error);
     }
     this._accountService.getProjectFields(event.data.projectID).subscribe((fieldsres:Fields)=>
     {
       console.log("fields ");
       this.fields=fieldsres;
      console.log(this.fields);
      }),(error)=>{
        console.log(error);
        }
  
     this.status = "";
   }
   
       
  
  onItemSelect(item: any) {
    console.log('onItemSelect', item);
    this.bioLogSearch.status=item;
    }
 onSelectAll(items: any) {
    console.log('onSelectAll', items);
    this.bioLogSearch.status=items;
}

receiveProjectStatusDetailEvent1($event) {
  //debugger;
  console.log("receiveProjectStatusDetailEvent1");
  if($event=="true"){
    
  }

}
onAccChange(accountName) {
  console.log("accountName");
  this.projectList=[];
  this.projectNameList=[];
  this.weekDurationList=[];
  this._accountService.getProjectNameByAccount(accountName).subscribe((projectListValues:ProjectDetail[])=>
    {
      this.rowData=[]
      this.selectedProject=new ProjectDetail();
      this.projectList= projectListValues;
      
     console.log(projectListValues);
      this.projectNameList = projectListValues;
     
      }),(error)=>{
      console.log(error);
    }
  }

  onProjectChange(projectID) {
     console.log("onProjectChange ",projectID);
     this.weekDurationList=[];
     this.rowData =[]
     this.projectList.forEach(element => {
       if(element.projectId == projectID )
       this.selectedProject =element;
       element.weekDuration.forEach(wd => {
       this.weekDurationList.push(wd.weekduration);
       });
     });
    // this.weekDurationList= Array.from(new Set(this.weekDurationList.map((item: string) => item)))
      
      
  }

  
  buSearch(data) {
    //debugger;
    this.spinnerService.show();
    console.log("buSearch ",data)
    let accountId= (<HTMLInputElement>document.getElementById('accountID')).value;
    let projectId = (<HTMLInputElement>document.getElementById('projectId')).value;
    let weekDuration = (<HTMLInputElement>document.getElementById('weekDuration')).value;

    var tempAccount:Account = new Account();
    tempAccount.accountID=data.accountID
    this.selectedProject.account=tempAccount
    this.selectedProject.projectId=data.projectId;
    this.selectedProject.weekDuration=[];
    var tempWeekStatus = new WeekStatus();
    tempWeekStatus.weekduration=data.weekDuration;
  
    this.selectedProject.weekDuration.push(tempWeekStatus);
   
    this._logService.getSearchLogDetails(this.selectedProject).subscribe((bioLog:ProjectDetail)=>
    {
     //debugger;
     
      
      console.log(bioLog);
      this.rowData = bioLog;
     
     this.spinnerService.hide();
     },()=>this.spinnerService.hide()),(error)=>{
     console.log(error);
     }

     
  }
    
  
  
}

