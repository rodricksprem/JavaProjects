import { Component, OnInit } from '@angular/core';
import { FormGroup, FormArray, FormBuilder, Validators, FormControl } from '@angular/forms';
import { ApplicationService } from '../../service/application.service';
import { AccountService } from '../../service/account.service';
import { ProjectStatusDetailNew } from '../../dao/ProjectStatusDetailNew';

import { ResponseOptions } from '@angular/http';
import { Account } from "../../dao/account";
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';
import { ProjectDetail } from 'src/app/dao/ProjectDetail';
import { CompleterService, CompleterData } from 'ng2-completer';
import { TaskDetail } from 'src/app/dao/TaskDetail';
import { Fields } from 'src/app/dao/fields';
import { WeekStatus } from 'src/app/dao/WeekStatus';
import { templateSourceUrl } from '@angular/compiler';
import { first } from 'rxjs/operators';

declare var $: any;
@Component({
  selector: 'app-new-interface',
  templateUrl: './task-status.component.html',
  styleUrls: ['./task-status.component.css']
})
export class NewInterfaceComponent implements OnInit {
  dropdownSettings = {};
  selectedFile: File;
  rowData: any = [];
  columnDefs: any = [];
  projectDetailList: ProjectDetail[];
  selectedProjectName: ProjectDetail;
  projectId: number;
  public statusSystemForm: FormGroup;
  public statusSystemFormArray: FormGroup;


  dataService: CompleterData;

  selectedProject = new ProjectDetail();
  projectStatusDetail = new ProjectStatusDetailNew();
  projectStatusDetailList: ProjectStatusDetailNew[];
  projectList: any = [];
  updateflag: string = "0";
  taskDetails: TaskDetail[];
  taskId: any;
  taskDetail = new TaskDetail();
  public fields : any[][];
  unsubcribe: any = [];
  fieldsCtrls: any = [];
  public fieldsList: FormArray;
  weeks: string[];
  selectedWeek: any;
  showDeleteButton: boolean;
  fieldsset: Fields;
  saveClicked: boolean;
  public projectStatusIdsList: number[];
  
  constructor(private _fb: FormBuilder, private completerService: CompleterService, private _applicationService: ApplicationService, private _businessUnitService: AccountService, private spinnerService: Ng4LoadingSpinnerService) {
  }


  ngOnInit() {
    this.spinnerService.show();
    this.updateflag = "0";
    let fieldsCtrls = [];
    this.fields=[];
    this.projectStatusIdsList=[];
    this.fields[0]=[];
    for (let f of this.fields[0]) {
      if (f.type != 'checkbox') {
        fieldsCtrls[f.name] = ['']
        fieldsCtrls[f.value] = ['']
      } else {
        let opts = {};
        for (let opt of f.options) {
          opts[opt.key] = new FormControl(opt.value);
        }
        fieldsCtrls[f.name] = ['']
      }
    }
    this.statusSystemForm = this._fb.group(fieldsCtrls);


    this.statusSystemFormArray = this._fb.group({
      statusRow: this._fb.array([this.statusSystemForm])
    })


    this.dropdownSettings = {
      singleSelection: false,
      idField: 'id',
      textField: 'name',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 3,
      allowSearchFilter: true

    };


    this._applicationService.getAllProjectDetails().subscribe((projectDetailList:ProjectDetail[]) => {
      //console.log(application);
      
      this.projectDetailList = projectDetailList;
      console.log("getAllProjectDetails ", this.projectDetailList);
      for (let projectDetail of this.projectDetailList) {
        console.log(" projectDetail name ", projectDetail.projectName);

        this.projectList.push(projectDetail.projectName);

        this.dataService = this.completerService.local(this.projectDetailList, "projectName", "projectName");
      }
      this.spinnerService.hide();
      console.log("getAllProjectDetails done ");
    }, () => this.spinnerService.hide()), (error) => {
      console.log(error);
      console.log("getAllProjectDetails error ");
    }

  }


  get formStatusystemArr(): FormArray {
    return this.statusSystemFormArray.get('statusRow') as FormArray;
  }
  addmoreStatusSystem() {
    console.log("addmorestatus ",this.fields.length);
    console.log("addmorestatus initially ",this.formStatusystemArr.length);
    this.constructForm(this.fieldsset, true,this.fields.length);   
    console.log("addmorestatus finally ",this.formStatusystemArr.length);

  }
  deleteStatusSystemRow(index: number) {
    var arrayControl = this.statusSystemFormArray.get("statusRow") as FormArray;
    var count =0;
    arrayControl.controls.forEach(item => {
    if(count==index){
      if(item.value.id!=null){
      this.projectStatusIdsList.push(item.value.id)
    }

    }
    
    count++;
    }
     
    )
    this.formStatusystemArr.removeAt(index);
   
  }


  onUpload(e) {
    console.log(e);

  }

  getFields(i: number) {


    //  console.log("initStatusSystemRows() ",this.initStatusSystemRows().value);
    //  return this.initStatusSystemRows().value
    console.log("getFields ",i);
    return this.fields
  }

  selectTaskOption(taskId: number) {
    console.log("selectTaskOPtion", taskId);

    this.taskId = taskId;
    this.taskDetail = this.taskDetails.find(function (taskDetail1: TaskDetail) {
      return taskDetail1.taskId == taskId;
    });
    console.log("selectedtask ", this.taskDetail);
    var isNew: boolean = this.taskDetail.projectStatusDetails ==null || this.taskDetail.projectStatusDetails.length==0?true:false;
    console.log("isNew ", isNew)
    this._businessUnitService.getProjectFields(this.projectId).subscribe((fieldsres: Fields) => {
      console.log("fields ");
     this.fieldsset = fieldsres;
      this.constructForm(fieldsres, isNew,0);
      this.showDeleteButton = true;
      //   $('#createStatus').enabled();
      console.log(this.fields);
    }), (error) => {
      console.log(error);
    }



  }
  constructForm(fieldres: Fields, isNew: boolean,indexarg) {
     var index = indexarg;
    do {
      var value;
      this.fields[index] =[];
      
      if(!isNew){
      this.fields[index].push(
        {
          type: 'text',
          name: 'id',
          label: 'id',
          value:  this.taskDetail.projectStatusDetails[index].id,
          disabled: true,
          required: true,
          
        });
      }
      if (fieldres.appAvailablityFlag) {
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].appAvailablity
        this.fields[index].push(
          {
            type: 'text',
            name: 'appAvailablity',
            label: 'appAvailablity',
            value: value,
            placeholder: 'please enter AppAvailablity %',

            required: true,
          });

      }
      if (fieldres.approvedByFlag) {
        console.log(fieldres.approvedByFlag , index);
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].approvedBy;
        this.fields[index].push(
          {
            type: 'text',
            name: 'approvedBy',
            label: 'approvedBy',
            value: value,
            placeholder: 'please enter Approver Name',

            required: true,
          });

      }

      if (fieldres.availMemSpaceFlag) {
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].availMemSpace;
        this.fields[index].push(
          {
            type: 'text',
            name: 'availMemSpace',
            label: 'availMemSpace',
            value: value,
            placeholder: 'please enter Available Mem Space in %',

            required: true,
          });

      }
      if (fieldres.availStorageSpaceFlag) {
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].availStorageSpace;
        this.fields[index].push(
          {
            type: 'text',
            name: 'availStorageSpace',
            label: 'availStorageSpace',
            value: value,
            placeholder: 'please enter Available Storage Space in %',

            required: true,
          });

      }

      if (fieldres.cpuLoadFlag) {
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].cpuLoad;
        this.fields[index].push(
          {
            type: 'text',
            name: 'cpuLoad',
            label: 'cpuLoad',
            value: value,
            placeholder: 'please enter CPU Load',

            required: true,
          });

      }

      if (fieldres.customerNameFlag) {
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].customerName;
        this.fields[index].push(
          {
            type: 'text',
            name: 'customerName',
            label: 'customerName',
            value: value,
            placeholder: 'please enter CustomerName',

            required: true,
          });

      }


      if (fieldres.dbAvailablityFlag) {
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].dbAvailablity;
        this.fields[index].push(
          {
            type: 'text',
            name: 'dbAvailablity',
            label: 'DB Availablity',
            value: value,
            placeholder: 'please Enter DB Availablity %',
            required: true,
          });

      }


      if (fieldres.descriptionFlag) {
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].description;
        this.fields[index].push(
          {
            type: 'text',
            name: 'description',
            label: 'Description',
            value: value,
            placeholder: 'please Enter Description',
            required: true,
          });

      }


      if (fieldres.developmentCompletionFlag) {
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].developmentCompletion;
        this.fields[index].push(
          {
            type: 'text',
            name: 'developmentCompletion',
            label: 'Development Completion',
            value: value,
            placeholder: 'please Enter Development completion',
            required: true,
          });

      }


      if (fieldres.issueNumberFlag) {
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].issueNumber;
        this.fields[index].push(
          {
            type: 'text',
            name: 'issueNumber',
            label: 'Issue Number',
            value: value,
            placeholder: 'please Enter IssueNumber',
            required: true,
          });

      }

      if (fieldres.l1IssuesOpenedFlag) {
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].l1IssuesOpened;
        this.fields[index].push(
          {
            type: 'text',
            name: 'l1IssuesOpened',
            label: 'l1IssuesOpened',
            value: value,
            placeholder: 'please Enter L1IssuesOpened',
            required: true,
          });

      }

      if (fieldres.l1IssuesClosedFlag) {
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].l1IssuesClosed;
        this.fields[index].push(
          {
            type: 'text',
            name: 'l1IssuesClosed',
            label: 'L1IssuesClosed',
            value: value,
            placeholder: 'please Enter L1IssuesClosed',
            required: true,
          });

      }


      if (fieldres.l2IssuesOpenedFlag) {
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].l2IssuesOpened;
        this.fields[index].push(
          {
            type: 'text',
            name: 'l2IssuesOpened',
            label: 'l2IssuesOpened',
            value: value,
            placeholder: 'please Enter L2IssuesOpened',
            required: true,
          });

      }
      if (fieldres.l2IssuesClosedFlag) {
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].l2IssuesOpened;
        this.fields[index].push(
          {
            type: 'text',
            name: 'l2IssuesOpened',
            label: 'L2IssuesClosed',
            value: value,
            placeholder: 'please Enter L2IssuesClosed',
            required: true,
          });

      }

      if (fieldres.locationFlag) {
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].location;
        this.fields[index].push(
          {
            type: 'text',
            name: 'location',
            label: 'location',
            value: value,
            placeholder: 'please Enter location',
            required: true,
          });

      }
      if (fieldres.moduleFlag) {
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].module;
        this.fields[index].push(
          {
            type: 'text',
            name: 'module',
            label: 'module',
            placeholder: 'please enter module name',
            value: ''
          });

      }

      if (fieldres.numBuildSprintWorkdFlag) {
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].numBuildSprintWorkd;
        this.fields[index].push(
          {
            type: 'text',
            name: 'numBuildSprintWorkd',
            label: 'numBuildSprintWorkd',
            placeholder: 'please enter Num of Sprint Worked',
            value: ''
          });

      }



      if (fieldres.numberBuildSprintCompletedFlag) {
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].numberBuildSprintCompleted;
        this.fields[index].push(
          {
            type: 'text',
            name: 'numberBuildSprintCompleted',
            label: 'numberBuildSprintCompleted',
            placeholder: 'please enter Num of Sprint Completed',
            value: ''
          });

      }

      if (fieldres.numClientsFlag) {
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].numClients;
        this.fields[index].push(
          {
            type: 'text',
            name: 'numClients',
            label: 'numClients',
            placeholder: 'please enter Num of Clients',
            value: ''
          });

      }


      if (fieldres.numOfDBsFlag) {
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].numOfDBs;
        this.fields[index].push(
          {
            type: 'text',
            name: 'numOfDBs',
            label: 'numOfDB',
            placeholder: 'please enter No. of DBs',
            value: ''
          });

      }


      if (fieldres.numOfServersFlag) {
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].numOfServers;
        this.fields[index].push(
          {
            type: 'text',
            name: 'numOfServers',
            label: 'numOfServers',
            placeholder: 'please enter No. of Servers',
            value: ''
          });

      }


      if (fieldres.numServiceFlag) {
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].numService;
        this.fields[index].push(
          {
            type: 'text',
            name: 'numService',
            label: 'numService',
            placeholder: 'please enter No. of Services',
            value: ''
          });

      }


      if (fieldres.numVechiclesFlag) {
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].numVechicles;
        this.fields[index].push(
          {
            type: 'text',
            name: 'numVechicles',
            label: 'numVechicles',
            placeholder: 'please enter No. of Vechicles',
            value: ''
          });

      }


      if (fieldres.remarksFlag) {
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].remarks;
        this.fields[index].push(
          {
            type: 'text',
            name: 'remarks',
            label: 'remarks',
            value: value,
            placeholder: 'please enter remarks'
          });

      }

      if (fieldres.requestedByFlag) {
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].requestedBy;
        this.fields[index].push(
          {
            type: 'text',
            name: 'requestedBy',
            label: 'requestedBy',
            value: value,
            placeholder: 'please enter Requestor Name'
          });

      }
      if (fieldres.resolutionFlag) {
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].resolution;
        this.fields[index].push(
          {
            type: 'text',
            name: 'resolution',
            label: 'resolution',
            value: value,
            placeholder: 'please enter resolution'
          });

      }

      if (fieldres.resourceFlag) {
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].resource;
        this.fields[index].push(
          {
            type: 'textarea',
            name: 'resource',
            label: 'resource',
            value: value,
            placeholder: 'please enter resourcenames'
          });

      }

      if (fieldres.serverAvailablityFlag) {
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].serverAvailablity;
        this.fields[index].push(
          {
            type: 'text',
            name: 'serverAvailablity',
            label: 'serverAvailablity',
            value: value,
            placeholder: 'please enter serverAvailablity'
          });

      }


      if (fieldres.serverNameFlag) {
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].serverName;
        this.fields[index].push(
          {
            type: 'text',
            name: 'serverName',
            label: 'serverName',
            value: value,
            placeholder: 'please enter server name'
          });

      }

      if (fieldres.statusFlag) {

        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].status;
        this.fields[index].push(
          {
            type: 'text',
            name: 'status',
            label: 'status',
            value: value,
            placeholder: 'please enter current status',
            required: true
          });

      }
      if (fieldres.dateSelectionFlag) {
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].dateSelection;
        this.fields[index].push(
          {
            type: 'date',
            name: 'dateSelection',
            label: 'Date seletion',
            value: value,
            placeholder: 'please select Date',
            required: true,
          });

      }



      if (fieldres.targetDateFlag) {
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].targetDate;
        this.fields[index].push(
          {
            type: 'date',
            name: 'targetDate',
            label: 'targetDate',
            value: value,
            placeholder: 'please enter Date Of Completion',
            required: true
          });

      }

      if (fieldres.taskTypeFlag) {
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].taskType;
        this.fields[index].push(
          {
            type: 'text',
            name: 'taskType',
            label: 'taskType',
            value: value,
            placeholder: 'please enter current TaskType',
            required: true
          });

      }


      if (fieldres.testsPerDayFlag) {
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].approvedBy;
        this.fields[index].push(
          {
            type: 'text',
            name: 'testsPerDay',
            label: 'testsPerDay',
            value: value,
            placeholder: 'please enter Tests Per day count',
            required: true
          });

      }


      if (fieldres.virtualFarmsFlag) {
        value = isNew ? '' :  this.taskDetail.projectStatusDetails[index].approvedBy;
        this.fields[index].push(
          {
            type: 'text',
            name: 'virtualFarms',
            label: 'virtualFarms',
            value: value,
            placeholder: 'please enter virtualFarms',
            required: true
          });

      }

      let fieldsCtrls = []
      for (let f of this.fields[index]) {
        if (f.type != 'checkbox') {
          if(f.value!=null){
            fieldsCtrls[f.name] = [f.value]
            console.log("setvalue... ");
            }else{
              fieldsCtrls[f.name] = ['']
            }
        } else {
          let opts = {};
          for (let opt of f.options) {
            opts[opt.key] = new FormControl(opt.value);
          }
          fieldsCtrls[f.name] = ['']
        }
      }
      
      this.statusSystemForm = this._fb.group(fieldsCtrls);

      if(index==0){
      this.statusSystemFormArray = this._fb.group({
        statusRow: this._fb.array([this.statusSystemForm])
      })
    }else{
      this.formStatusystemArr.push(this.statusSystemForm);

    }
    
    index=index+1;
    } while (this.taskDetail.projectStatusDetails!=null && index < this.taskDetail.projectStatusDetails.length)




  }
  onProjectSelcted(data: any) {
    console.log(data);

    this.fields = [];
    this.weeks = [];
    this.selectedProject = data.originalObject;
    this.projectId = this.selectedProject.projectId;

    console.log("projectId:" + this.projectId);
    if (this.projectId >= 0) {
      // this._applicationService.getAppplicationDetails(this.projectId).subscribe((bioLogApplicationData)=>
      //{

      // this.taskDetails = this.selectedProject.taskDetails;

      //console.log(this.selectedProject.taskDetails);
      this.selectedProject.weekDuration.forEach(wd => {
        this.weeks.push(wd.weekduration);
      });
      console.log("projectId:" + this.projectId + " - " + this.selectedWeek);


      document.getElementById("successStatus").innerHTML = "";
      document.getElementById("errorStatus").innerHTML = "";

      // }),(error)=>{
      // console.log(error);
      // }
    }
  }

  onWeekChange(data: any) {
    console.log("onWeekChange", data);

    this.fields = [];

    this.selectedWeek = data;
    console.log("projectId:" + this.projectId + " - " + this.selectedWeek);
    if (this.projectId >= 0) {
      // this._applicationService.getAppplicationDetails(this.projectId).subscribe((bioLogApplicationData)=>
      //{

      this.taskDetails = [];
      this.selectedProject.taskDetails.forEach(td => {
        console.log(td.taskCreationDate);
        var weekDuration = this.formWeek(td.taskCreationDate);
        console.log("task detail weekduration" + weekDuration);
        if (this.selectedProject.weekDuration.indexOf(this.selectedWeek) && weekDuration == this.selectedWeek) {
          this.taskDetails.push(td);
        }
      })


      console.log(this.taskDetails);

      document.getElementById("successStatus").innerHTML = "";
      document.getElementById("errorStatus").innerHTML = "";

      // }),(error)=>{
      // console.log(error);
      // }
    }
  }


  removeTask() {
    console.log("removeTask...");

    if (window.confirm('Are sure you want to delete this task ?')) {
      this._businessUnitService.removeTaskDetail(this.taskId).subscribe((flag: Boolean) => {
        if (flag == true) {

          document.getElementById("successStatus").innerHTML = "";
          document.getElementById("successStatus").innerHTML = "Task Detail is deleted  Successfully.";
        } else {
          document.getElementById("errorStatus").innerHTML = "Error while delete Task Detail or task is already deleted or selected task id may not exists .";
        }

      }), (error) => {
        console.log(error);
        console.log("removeTask error ");
      }

    }

  }
  removeWeek() {
    console.log("removeWeek...");

    if (window.confirm('Are sure you want to delete this week ?')) {
      var foundpos=-1;
      for( var i = this.selectedProject.weekDuration.length-1; i--;){
        console.log("this.weeks... "+this.selectedProject.weekDuration[i].weekduration);
        if (this.selectedProject.weekDuration[i].weekduration == this.selectedWeek) {
            console.log("removed week ... "+this.selectedWeek);
            foundpos=i;
        }
      }
      this.selectedProject.weekDuration.splice(foundpos, 1);
      this.weeks=[];
      this.selectedProject.weekDuration.forEach(wd => {
        this.weeks.push(wd.weekduration);
      });
      this._businessUnitService.removeWeekDuration(this.selectedProject.projectId,this.selectedWeek).subscribe((flag:Boolean) => {
        if (flag == true) {

          document.getElementById("successStatus").innerHTML = "";
          document.getElementById("successStatus").innerHTML = "Week  is deleted  Successfully.";
        } else {
          document.getElementById("errorStatus").innerHTML = "Error while delete Week or week is already deleted or selected week may not exists .";
        }

      }), (error) => {
        console.log(error);
        console.log("removeWeek error ");
      }

    }

  }

  onSaveClick(){
    this.saveClicked=true;
  }
  onDeleteClick(){
    this.saveClicked=false;
  }
  
  saveOrDeleteStatus(data){
    if(this.saveClicked){
      if (window.confirm('Are sure you want to save the changes ?')){
      console.log("saveStatus");
      this.saveStatus(data);
       }
    }else{
    
      console.log("deleteStatus");
      this.deleteStatus(data);
      

    }
  }
  deleteStatus(data){
    console.log("update flag:", data);
    document.getElementById("successStatus").innerHTML = "";
    document.getElementById("errorStatus").innerHTML = "";
    // console.log(this.statusSystemForm);
    var arrayControl = this.statusSystemFormArray.get("statusRow") as FormArray;
    console.log(" arrayControl ", arrayControl);
    if (window.confirm('Are sure you want to delete the status of ids ?'+this.projectStatusIdsList)){
   this._businessUnitService.removeProjectStatusDetail(this.projectStatusIdsList).subscribe((flag:Boolean) => {

      console.log("Entity  deleteProjectDetails :: " + flag);

      if (flag) {

        document.getElementById("successStatus").innerHTML = "";
        document.getElementById("successStatus").innerHTML = "Weekly Status for the given task Deleted  Successfully.";
      } else {
        document.getElementById("errorStatus").innerHTML = "Error while Deleting selected weekly status.";
      }
    }),
      (error) => {
        console.log(error);
      }
  
    }
  }
  saveStatus(data) {

    //debugger;

    console.log("update flag:", data);
    document.getElementById("successStatus").innerHTML = "";
    document.getElementById("errorStatus").innerHTML = "";
    // console.log(this.statusSystemForm);
    var arrayControl = this.statusSystemFormArray.get("statusRow") as FormArray;
    console.log(" arrayControl ", arrayControl);

    console.log(this.selectedProject.projectId, this.taskDetail.taskId, this.selectedProject.weekDuration);
      
    for (let item of arrayControl.controls) {
      
     console.log("inside saveStatus ");
     if(item.value.id!=null && item.value.id != ''){
      console.log(" Status Id ",item.value.id);
      this.projectStatusDetail = this.taskDetail.projectStatusDetails. find(function (pd: ProjectStatusDetailNew) {
        return pd.id == item.value.id;
      });
        
     }
     else{
      console.log(" new status ");
      this.projectStatusDetail = new ProjectStatusDetailNew();
     }
      //this.bioLOVsData.id=item.value.sourceAppName

      if (item.value.resource != null && item.value.resource != '') {
        console.log("resource ",item.value.resource);
        this.projectStatusDetail.resource = item.value.resource;
        item.value.resource = "";
      }

    


      if (item.value.dateSelection != null && item.value.dateSelection != '') {
        console.log("dateSelection ",item.value.dateSelection);
        this.projectStatusDetail.dateSelection = item.value.dateSelection;
      }
      if (item.value.description != null && item.value.description != '') {
        console.log("description ",item.value.description);
        this.projectStatusDetail.description = item.value.description;
      }
      if (item.value.l1IssuesOpened != null) {
        console.log("l1IssuesOpened ",item.value.l1IssuesOpened);
        this.projectStatusDetail.l1IssuesOpened = item.value.l1IssuesOpened;
      }
      if (item.value.l2IssuesOpened != null) {
        console.log("l2IssuesOpened ",item.value.l2IssuesOpened);
        this.projectStatusDetail.l2IssuesOpened = item.value.l2IssuesOpened;
      }

      if (item.value.l1IssuesClosed != null) {
        console.log("l1IssuesClosed ",item.value.l1IssuesClosed);
        this.projectStatusDetail.l1IssuesClosed = item.value.l1IssuesClosed;
      }
      if (item.value.l2IssuesClosed != null) {
        console.log("l2IssuesClosed ",item.value.l2IssuesClosed);
        this.projectStatusDetail.l2IssuesClosed = item.value.l2IssuesClosed;
      }
      if (item.value.remarks != null && item.value.remarks != '') {
        console.log("remarks ",item.value.remarks);
        this.projectStatusDetail.remarks = item.value.remarks;
      }
      if (item.value.developmentCompletion != null) {
        console.log('developmentCompletion ',item.value.developmentCompletion);
        this.projectStatusDetail.developmentCompletion = item.value.developmentCompletion;
      }

      if (item.value.numBuildSprintWorkd != null) {
        console.log('numBuildSprintWorkd ',item.value.numBuildSprintWorkd);
        this.projectStatusDetail.numBuildSprintWorkd = item.value.numBuildSprintWorkd;
      }
      if (item.value.numberBuildSprintCompleted != null) {
        console.log('numberBuildSprintCompleted ',item.value.numBuildSprintWorkdFlag);
        this.projectStatusDetail.numberBuildSprintCompleted = item.value.numberBuildSprintCompleted;
      }
      if (item.value.testsPerDay != null && item.value.testsPerDay != '') {
        console.log('testsPerDay ',item.value.testsPerDay);
        this.projectStatusDetail.testsPerDay = item.value.testsPerDay;
      }
      if (item.value.serverName != null && item.value.serverName != '') {
        console.log('serverName ',item.value.serverName);
        this.projectStatusDetail.serverName = item.value.serverName;
      }
      if (item.value.issueNumber != null && item.value.issueNumber != '') {
        console.log('issueNumber ',item.value.issueNumber);
        this.projectStatusDetail.issueNumber = item.value.issueNumber;
      }
      if (item.value.customerName != null && item.value.customerName != '') {
        console.log('customerName ',item.value.customerName);
        this.projectStatusDetail.customerName = item.value.customerName;
      }
      if (item.value.module != null && item.value.module != '') {
        console.log('module ',item.value.module);
        this.projectStatusDetail.module = item.value.module;
      }
      if (item.value.status != null && item.value.status != '') {
        console.log(' status  ',item.value.status);
        this.projectStatusDetail.status = item.value.status;
      }

      if (item.value.targetDate != null && item.value.targetDate != '') {
        console.log('targetDate ',item.value.targetDate);
        this.projectStatusDetail.targetDate = item.value.targetDate;
      }
      if (item.value.virtualFarms != null && item.value.virtualFarms != '') {
        console.log('virtualFarms ',item.value.virtualFarms);
        this.projectStatusDetail.virtualFarms = item.value.virtualFarms;
      }
      if (item.value.numOfServers != null) {
        console.log('numOfServers ',item.value.numOfServers);
        this.projectStatusDetail.numOfServers = item.value.numOfServers;
      }
      if (item.value.numOfDBs != null) {
        console.log('numOfDBs ',item.value.numOfDBs);
        this.projectStatusDetail.numOfDBs = item.value.numOfDBs;
      }
      if (item.value.serverAvailablity != null) {
        console.log('serverAvailablity ',item.value.serverAvailablity);
        this.projectStatusDetail.serverAvailablity = item.value.serverAvailablity;
      }

      if (item.value.cpuLoad != null) {
        console.log('cpuLoad ',item.value.cpuLoad);
        this.projectStatusDetail.cpuLoad = item.value.cpuLoad;
      }
      if (item.value.availMemSpace != null) {
        console.log('availMemSpace ',item.value.availMemSpace);
        this.projectStatusDetail.availMemSpace = item.value.availMemSpace;
      }
      if (item.value.availStorageSpace != null) {
        console.log('availStorageSpace ',item.value.availStorageSpace);
        this.projectStatusDetail.availStorageSpace = item.value.availStorageSpace;
      }
      if (item.value.dbAvailablity != null) {
        console.log('dbAvailablity ',item.value.dbAvailablity);
        this.projectStatusDetail.dbAvailablity = item.value.dbAvailablity;
      }
      if (item.value.appAvailablity != null) {
        console.log('appAvailablity ',item.value.appAvailablity);
        this.projectStatusDetail.appAvailablity = item.value.appAvailablity;
      }
      if (item.value.numClients != null) {
        console.log('numClients ',item.value.numClients);
        this.projectStatusDetail.numClients = item.value.numClients;
      }
      if (item.value.numService != null) {
        console.log('numService ',item.value.numService);
        this.projectStatusDetail.numService = item.value.numService;
      }
      if (item.value.numVechicles != null) {
        console.log('numVechicles ',item.value.numVechicles);
        this.projectStatusDetail.numVechicles = item.value.numVechicles;
      }
      if (item.value.location != null && item.value.location != '') {
        console.log('location ',item.value.location);
        this.projectStatusDetail.location = item.value.location;
      }

      if (item.value.requestedBy != null && item.value.requestedBy != '') {
        console.log('requestedBy ',item.value.requestedBy);
        this.projectStatusDetail.requestedBy = item.value.requestedBy;
      }

      if (item.value.resolution != null && item.value.resolution != '') {
        console.log('resolution ',item.value.resolution);
        this.projectStatusDetail.resolution = item.value.resolution;
      }
      if (item.value.approvedBy != null && item.value.approvedBy != '') {
        console.log('approvedBy ',item.value.approvedBy);
        this.projectStatusDetail.approvedBy = item.value.approvedBy;
      }
      console.log(this.taskDetail);
      if (this.taskDetail.projectStatusDetails != null) {
        var isFound=false;
        this.taskDetail.projectStatusDetails.forEach(pd => {
          if(this.projectStatusDetail.id!= null && pd.id==this.projectStatusDetail.id){
            console.log(" update the existing projectstatusdetail ",this.projectStatusDetail.id);
            isFound=true; 
            pd = this.projectStatusDetail; // update the existing projectstatusdetail
            return;  
          }
        });
        if(!isFound){ // new row need to be added to the existing projectstatusdetail
          console.log("new row need to be added to the existing projectstatusdetail");
            this.taskDetail.projectStatusDetails.push(this.projectStatusDetail);
            
        }
      } else {
        this.taskDetail.projectStatusDetails = [];
        console.log("new  projectstatusdetail");
        this.taskDetail.projectStatusDetails.push(this.projectStatusDetail);
      }

    }


    console.log(this.selectedProject);
    this._businessUnitService.updateProjectStatusDetails(this.taskDetail).subscribe((flag) => {

      console.log("Entity  updateProjectStatusDetails :: " + flag);

      if (flag) {

        document.getElementById("successStatus").innerHTML = "";
        document.getElementById("successStatus").innerHTML = "Weekly Status for the given task Created / Updated Successfully.";
      } else {
        document.getElementById("errorStatus").innerHTML = "Error while creatimg /updating weekly status.";
      }
    }),
      (error) => {
        console.log(error);
      }
      
  }

  formWeek(inputDate: Date) {
    var startDate: Date = new Date(inputDate);
    var firstday = startDate.getDate() - startDate.getDay() + (startDate.getDay() === 0 ? -6 : 1);
    var firstDate = new Date(startDate.setDate(firstday));
    //  console.log( "firstDate "+firstDate)
    //Apr 20,2020-Apr 26,2020
    var lastday = startDate.getDate() - (startDate.getDay() - 1) + 6;
    var lastDate = new Date(startDate.setDate(lastday));
    // console.log( "lastDate "+ lastDate)

    var monthFirstDate = firstDate.toLocaleString('default', { month: 'short' });
    var monthLastDate = lastDate.toLocaleString('default', { month: 'short' });
    var dayFirstDate = firstDate.getDate() < 10 ? "0" + firstDate.getDate() : firstDate.getDate();
    var dayLastDate = lastDate.getDate() < 10 ? "0" + lastDate.getDate() : lastDate.getDate();
    var weekDuration = monthFirstDate + " " + dayFirstDate + "," + firstDate.getFullYear() + "-" + monthLastDate + " " + dayLastDate + "," + lastDate.getFullYear()
    return weekDuration;
  }
  saveWeek(data) {

    console.log("saveWeek");
    console.log(data.selectedDate);
    var weekDuration = this.formWeek(data.selectedDate);
    console.log(weekDuration);
    var newWeek:WeekStatus = new WeekStatus();
    newWeek.weekduration=weekDuration;
    newWeek.projectStatus=0;
    this.selectedProject.weekDuration.push(newWeek);
    
    this._businessUnitService.updateProjectDetail(this.selectedProject).subscribe((flag) => {

      console.log("Entity  updateProject :: " + flag);

      if (flag) {

        document.getElementById("successStatus").innerHTML = "";
        document.getElementById("successStatus").innerHTML = "Week is added  Successfully.";
        this.weeks.push(weekDuration);
      } else {
        document.getElementById("errorStatus").innerHTML = "Error while adding WeeK.";
      }
      /* this._businessUnitService.getTasks(this.bioLOVsData.type).subscribe((entityListValues)=>
    {
  //    console.log(entityListValues);
      this.task = entityListValues;
      }),(error)=>{
      console.log(error);
    }*/

    }),
      (error) => {
        console.log(error);
      }

      
    $('#addWeek').click();
  }
  getMonth(monthStr) {

    monthStr = monthStr.replace(/^./, monthStr[0].toUpperCase());
    console.log(monthStr);

    var monthNames = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];

    var month: number = monthNames.indexOf(monthStr);
    console.log(month);
    return month;
  }

  pickDateFromWeek(week: string) {
    console.log(week);
    //Mar 30,2020-Apr 05,2020
    var dates = week.split("-");
    console.log(dates);
    var firstDate_Month = dates[0].split(" ");
    console.log(firstDate_Month);
    if (firstDate_Month.length == 2) {
      console.log(parseInt(firstDate_Month[0]));
      var firstDate_DayYear = firstDate_Month[1].split(",")
      return new Date(parseInt(firstDate_DayYear[1]), this.getMonth(firstDate_Month[0]), parseInt(firstDate_DayYear[0]), 13, 0, 0)
    } else {
      return new Date();
    }
  }
  saveTask(data) {

    document.getElementById("successStatus").innerHTML = "";
    document.getElementById("errorStatus").innerHTML = "";

    var taskCreationDate = this.pickDateFromWeek(this.selectedWeek);
    console.log("saveTask:" + data.taskName + " creationDate " + taskCreationDate);
    console.log("projectId:" + this.selectedProject.projectId);
    //console.log("saveEntity description:"+data.entityDescription);

    var errorMsg = "";
    if (data.taskName == null || data.taskName.trim() == "") {
      errorMsg = errorMsg + "Please Enter Task Description</br>";

    }
    if (data.taskType == null || data.taskType.trim() == "") {
      errorMsg = errorMsg + "Please Enter Task Type";

    }
    this.taskDetail = new TaskDetail();
    this.taskDetail.taskName = data.taskName;
    this.taskDetail.taskType = data.taskType;
    this.taskDetail.taskCreationDate = taskCreationDate;
    var tempProjectDetail = new ProjectDetail();
    tempProjectDetail.projectId = this.selectedProject.projectId;
    var temptaskDetails: any = [];
    temptaskDetails.push(this.taskDetail);
    tempProjectDetail.taskDetails = temptaskDetails;
    this._businessUnitService.createTaskDetail(tempProjectDetail).pipe(first()).subscribe((flag) => {

      console.log("Entity create Task Detail :: ");
      console.log(flag);
      $('#addTask').click();
      if (flag) {


        this.taskDetails.push(flag);
        document.getElementById("successStatus").innerHTML = "";
        document.getElementById("successStatus").innerHTML = "Task Created Successfully.";
      }
      else {
        document.getElementById("errorStatus").innerHTML = "Error while creating Task.";
      }



    }),
      (error) => {
        console.log(error);
      }
  }

}