import { Component, OnInit } from '@angular/core';
import { FormGroup, FormArray, FormBuilder, Validators, FormControl} from '@angular/forms';

import { ApplicationService } from '../../service/application.service';
import { AccountService } from '../../service/account.service';
import { ProjectStatusDetailNew } from '../../dao/ProjectStatusDetailNew';
import {Account} from "../../dao/account";
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';

import { ProjectDetail } from 'src/app/dao/ProjectDetail';
import { CompleterService, CompleterData } from 'ng2-completer';
import { TaskDetail } from 'src/app/dao/TaskDetail';
import { WeekStatus } from 'src/app/dao/WeekStatus';

declare var  $:any;
@Component({
  selector: 'app-project-status',
  templateUrl: './project-status.component.html',
  styleUrls: ['./project-status.component.css']
})
export class ProjectStatusComponet implements OnInit {
  dropdownSettings = {};
  rowData:any = [];
  columnDefs:any=[];
  notifyPropsNameList:any = [];
  projectDetailList:ProjectDetail[];
  
  selectedProjectName: ProjectDetail;
  appId:number;
  projectId:number;
  
  public statusSystemForm: FormGroup;
  public statusSystemFormArray: FormGroup;
  
  
  dataService:CompleterData;
  
  selectedProject = new ProjectDetail();
 weeks:string[];
 statuslist: string[];
  projectDetail: ProjectDetail;
  projectList:any=[];

  constructor(private _fb: FormBuilder,private completerService: CompleterService,private _applicationService:ApplicationService, private _businessUnitService:AccountService,private spinnerService: Ng4LoadingSpinnerService) {
   }
   
  
  ngOnInit() {
    this.spinnerService.show();
    
  
    this.dropdownSettings = {
      singleSelection: false,
      idField: 'id',
      textField: 'name',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 3,
      allowSearchFilter: true
    };
    this._applicationService.getWeeks().subscribe((weeksList:string[]) => {
      this.weeks=weeksList;
      console.log("this.weeks "+this.weeks);
    }), (error) => {
      console.log(error);
    
    }
    this._applicationService.getAllProjectDetails().subscribe((projectDetailList:ProjectDetail[])=>
    {
      //console.log(application);
      this.projectDetailList = projectDetailList;
      console.log("getAllProjectDetails ",this.projectDetailList);
    for(let projectDetail of  this.projectDetailList){
      console.log(" projectDetail name ",projectDetail.projectName);
      this.projectList.push(projectDetail.projectName);

      this.dataService=this.completerService.local(this.projectDetailList,"projectName","projectName");
    }
    this.spinnerService.hide();
    console.log("getAllProjectDetails done ");
  },()=>this.spinnerService.hide()),(error)=>{
  console.log(error);
  console.log("getAllProjectDetails error ");
  }
    
    }

    
  
    onProjectSelcted(data:any){

      this.weeks=[];
      console.log(data);
      
       this.selectedProject=data.originalObject;
       this.projectId =this.selectedProject.projectId;
       
       console.log("projectId:"+this.projectId);
       if(this.projectId >=0){
      // this._applicationService.getAppplicationDetails(this.projectId).subscribe((bioLogApplicationData)=>
       //{
         this.statuslist= ["GREEN","ORANGE","RED"] ;
         this.selectedProject.weekDuration.forEach(wd => {
        this.weeks.push(wd.weekduration);
         });
        
        console.log(this.weeks);

        document.getElementById("successStatus").innerHTML = "";
        document.getElementById("errorStatus").innerHTML = "";
        
       // }),(error)=>{
       // console.log(error);
       // }
       }
     }

   
  
   
 
  saveProjectStatus(data){
  console.log("saveProjectStatus: ",data);
  console.log("projectId:"+this.selectedProject.projectId);
  document.getElementById("successStatus").innerHTML = "";
  document.getElementById("errorStatus").innerHTML = "";
  //console.log("saveEntity description:"+data.entityDescription);

    var errorMsg="";
    if(data.weekDuration == null || data.weekDuration.trim() == "")
    {
      errorMsg = errorMsg+  "Please Select Week Duration</br>";

    }
    if(data.projectStatus == null || data.projectStatus.trim() == "")
    {
      errorMsg = errorMsg+ "Please Select Health Status";
      
    }
    var isPresent = false;

    this.selectedProject.weekDuration.forEach( week => {
      
      if(week.weekduration == data.weekDuration){
        isPresent=true;
        console.log("isPresent "+isPresent)
       
        if(data.projectStatus=="GREEN"){
            console.log(data.projectStatus);
            week.projectStatus=1;
      
          }else if(data.projectStatus=="ORANGE"){
            
            console.log(data.projectStatus);
            week.projectStatus=2;
      
          }else if(data.projectStatus=="RED"){
            
            console.log(data.projectStatus);
            week.projectStatus=0;
          }
            }
            index=index+1;
      
          });
    if(isPresent){
     

      var index:number=0;
    this._businessUnitService.createProjectDetail(this.selectedProject).subscribe((flag:Boolean)=>
    {

       console.log("Entity create status :: "+flag);
       
       if(flag){
         
        
       console.log("processed")
       document.getElementById("successStatus").innerHTML = "";
       document.getElementById("successStatus").innerHTML = "Project Health Status is updated Successfully.";
      } else {
       document.getElementById("errorStatus").innerHTML = "Error while updating Project health status.";
      }
    }),
    (error)=>{
      console.log(error);
    }
  }else{
    document.getElementById("errorStatus").innerHTML = "No records found for selected week , unable to update the status.";
  }
 }
   
   getWeeksInMonth(){
     

     
    var weeks=[];
    var weeksofyear=[weeks];
    for(var i=1;i<=12;i++) {
      var month =i;
     var   firstDate=new Date(2020, month, 1);
      var  lastDate=new Date(2020, month+1, 0);
       var numDays= lastDate.getDate();
    
    var start=1;
    var end=7-firstDate.getDay();
    while(start<=numDays){
        weeks.push({start:start,end:end});
        start = end + 1;
        end = end + 7;
        if(end>numDays)
            end=numDays;    
    } 
    weeksofyear.push(weeks); 
  }     
    console.log(weeksofyear);
     return weeksofyear;
 }
}