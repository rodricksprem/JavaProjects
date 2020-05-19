import { Component, OnInit, ViewChild } from '@angular/core';
import { UserService } from '../../service/user.service';
import { Bctloguser } from '../../dao/bctloguser';
import { UserType } from '../../dao/user-type';
import { NgForm } from '@angular/forms';
import { AccountService } from '../../service/account.service';
import { Subscriber } from 'rxjs';
import { GridOptions } from 'ag-grid/main';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';
import { ProjectDetail } from 'src/app/dao/ProjectDetail';
@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  @ViewChild('bctLogUserForm') public createBctLogUserForm: NgForm;
  dropdownSettings = {};
  projectDropdownSettings = {};
  bctloguser: Bctloguser[];
  rowData: any = [];
  columnDefs: any = [];
  userTypeList: UserType[];

  selectedUserType: UserType;
  private bctLogUser = new Bctloguser();
  overlayLoadingTemplate: any;
  private gridApi;
  title = 'BctLogUser Details';
  bunitList: any = [];
  targetRole: any = [];
  targetProject: any = [];
  userListCons: any = [];
  userType = "";
  buList = [];
  selectedItems: any = [];
  selectedProjectItems: any = [];
  saveClicked=false;
  roleList: string;
  projectList:ProjectDetail[];
  constructor(private _businessUnitService: AccountService, private _userService: UserService, private spinnerService: Ng4LoadingSpinnerService) { }

  ngOnInit() {
    this.saveClicked=false;
    this.spinnerService.show();
    this.overlayLoadingTemplate =
      '<span class="ag-overlay-loading-center">Please wait while your rows are loading</span>';

    this._userService.getUserTypeList().subscribe((userTypesListValues:UserType[]) => {
      console.log(userTypesListValues);
      this.userTypeList = userTypesListValues;
      console.log("====== "+this.userTypeList);
    }), (error) => {
      console.log(error);
    }

    this._userService.getProjectList().subscribe((projectListValues:ProjectDetail[]) => {
     
      console.log(projectListValues);
      this.projectList=projectListValues;
      console.log("====== "+this.projectList);
    }), (error) => {
      console.log(error);
    }

    this.columnDefs = [
      { headerName: 'UserId', field: 'userId', width: 130 },
      { headerName: 'UserType', field: 'userType', width: 150 },
      {headerName: 'EmailID', field: 'emailID', width:160 },
      {headerName: 'UserName', field: 'UserName', width:160},
      {headerName: 'LastName', field: 'lastName', width:160}, 
      { headerName: 'ProjectName', field: 'projectName', width: 300 },
      { headerName: 'UserRole', field: 'userRole', width: 160 },
      ];

    this._userService.getAllBctLogUsers().subscribe((bctLogUser) =>
     {
      //debugger;
      // console.log(JSON.stringify(bctLogUser));
      this.rowData = bctLogUser;
      for (let user of this.rowData) {
        this.userListCons.push(user.userId);
      }

    }), (error) => {
      console.log(error);
    }

    this.projectDropdownSettings = {
      singleSelection: true,
      idField: 'projectId',
      textField: 'projectName',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 3,
      allowSearchFilter: false
    };
    this.dropdownSettings = {
      singleSelection: false,
      idField: 'roleType',
      textField: 'roleType',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 3,
      allowSearchFilter: false
    };

  }

  onGridReady(params): void {
    this.gridApi = params.api;
    this.gridApi.showLoadingOverlay();
  }

  clear() {
    this.createBctLogUserForm.reset();
  }
  onItemSelect(item: any) {
   console.log(" UserType selected:"+item);
    this.targetRole.push(item);
    console.log("onItemSelect this.targetRole="+this.targetRole);    

  }
  onProjectSelect(item: any) {
    console.log(" Project selected:"+JSON.stringify(item));
    this.targetProject.push(item);
    console.log("onProjectSelect this.targetProject="+this.targetProject);    

  }
  onSelectAll(items: any) {
    // console.log(items);
    this.targetRole = [];
    for (let item of items) {

      this.targetRole.push(item);
    }

    //console.log(" onSelectAll --> this.targetBUAPPId="+this.targetRole);
  }
  onSelectAllProjects(items: any) {
    // console.log(items);
    this.targetProject = [];
    for (let item of items) {

      this.targetProject.push(item);
    }

    //console.log(" onSelectAll --> this.targetBUAPPId="+this.targetRole);
  }
  onDeSelectAll(items: any) {
    //    console.log(items);
    this.targetRole = [];
    //  console.log("onDeSelectAll --> this.targetRole="+this.targetRole);

  }
  onDeSelectAllProjects(items: any) {
    //    console.log(items);
    this.targetProject = [];
    //  console.log("onDeSelectAll --> this.targetRole="+this.targetRole);

  }
  onItemDeSelect(item: any) {
    //console.log(item);
    var newItems = [];
    for (let itemId of this.targetRole) {
      if (item != itemId) {
        newItems.push(itemId);
      }
    }
    this.targetRole = newItems;
    //console.log("onItemDeSelect--> this.targetRole="+this.targetRole);    
  }

  onProjectDeSelect(item: any) {
    //console.log(item);
    var newItems = [];
    for (let itemId of this.targetProject) {
      if (item != itemId) {
        newItems.push(itemId);
      }
    }
    this.targetProject = newItems;
    //console.log("onItemDeSelect--> this.targetRole="+this.targetRole);    
  }

  onSaveClick(){
    this.saveClicked=true;
  }
  onDeleteClick(){
    this.saveClicked=false;
  }
  saveOrDeleteUser(data){
    if(this.saveClicked){
      console.log("saveuser");
      this.saveUser(data);
    }else{
      console.log("deleteUser");
      this.deleteUser(data);

    }
  }
  deleteUser(data) {
    this.spinnerService.show();
    console.log("deleteUser ",data);
    this.bctLogUser.userId = data.userId;
     console.log(data.userId);var errorMsg = "";

    if (data.userId == null ) {
      errorMsg = errorMsg + "Please Enter User Id<br/>";
    }
    if (this.targetProject == null || this.targetProject == "") {
      errorMsg = errorMsg + "Please Selct Associated Projects<br/>";
    }

    if (errorMsg != "") {
      document.getElementById("successStatus").innerHTML = "";
      document.getElementById("errorStatus").innerHTML = errorMsg;
      return;
    }
    this._userService.deleteBctLogUser(this.bctLogUser.userId).subscribe((response) => {
      console.log("Success :: " + response);

      if (response) {
        document.getElementById("successStatus").innerHTML = "User has been Deleted successfully!!";
        document.getElementById("errorStatus").innerHTML = "";
        this.userType = "";
        this.targetRole = [];
        this.userListCons = [];
        this._userService.getAllBctLogUsers().subscribe((bctLogUser) => {
          this.rowData = bctLogUser;
          for (let user of this.rowData) {
            this.userListCons.push(user.userId);
          }
          this.spinnerService.hide();
        }, () => this.spinnerService.hide()), (error) => {
          console.log(error);
        }
      }

    }),
      (error) => {
        console.log(error);
      }

  }
  saveUser(data) {
    this.spinnerService.show();
    console.log("saveuser ",data);
    this.bctLogUser.userId = data.userId;
    this.bctLogUser.userType = this.targetRole;
    console.log("targetRole:" + this.bctLogUser.userType);
    this.bctLogUser.projectDetails = this.targetProject;
    
    console.log("targetProject:" + this.targetProject);
    this.bctLogUser.emailId=data.email;
    this.bctLogUser.userName = data.username;
    this.bctLogUser.password = data.password;
    console.log("userName:" + this.bctLogUser.userName);
    var errorMsg = "";

    if (data.userId == null || data.userId.trim() == "") {
      errorMsg = errorMsg + "Please Enter Network User Name<br/>";
    }
    if (this.targetRole == null) {
      errorMsg = errorMsg + "Please Select one or more User Type<br/>";
    }
    if (this.targetProject == null) {
      errorMsg = errorMsg + "Please Selct Associated Projects<br/>";
    }

    if (data.email == null || data.email == "") {
      errorMsg = errorMsg + "Please provide Email ID<br/>";
    }
    
    if (data.password == null || data.password == "") {
      this.bctLogUser.password="system123"
    }
    if (data.username == null || data.username== "") {
      errorMsg = errorMsg + "Please provide User Name<br/>";
    }
    if (errorMsg != "") {
      document.getElementById("successStatus").innerHTML = "";
      document.getElementById("errorStatus").innerHTML = errorMsg;
      return;
    }
    this._userService.createBctLogUser(this.bctLogUser).subscribe((response) => {
      console.log("Success :: " + response);

      if (response) {
        document.getElementById("successStatus").innerHTML = "User has been created successfully!!";
        document.getElementById("errorStatus").innerHTML = "";

     
        this.userType = "";
        this.targetRole = [];
        this.userListCons = [];
        this._userService.getAllBctLogUsers().subscribe((bctLogUser) => {
          this.rowData = bctLogUser;
          for (let user of this.rowData) {
            this.userListCons.push(user.userId);
          }
          this.spinnerService.hide();
        }, () => this.spinnerService.hide()), (error) => {
          console.log(error);
        }
      }

    }),
      (error) => {
        console.log(error);
      }

  }

  onUserSelcted(data) {
    console.log("selected userId:" + data);
    var userTemp = null;
    this.targetRole = [];
    for (let user of this.rowData) {
      if (user.userId == data) {
        userTemp = user;
        //console.log("userDetails:"+JSON.stringify(user));
        break;
      }

    }
    this.selectedItems = [];
    this.selectedProjectItems=[]

    // console.log("userBUList:"+JSON.stringify(userTemp));
    if (userTemp != null && userTemp != "") {
      for (let userType of this.userTypeList) {
        if (userType.userType == userTemp.userType) {
//          this.userType = userType;
        }
      }
      //  console.log("this.userType:"+this.userType);

      for (let bu of userTemp.buList) {
        console.log("bu:" + JSON.stringify(bu));
        this.selectedItems.push({ id: bu.appId, name: bu.appName });
        this.onItemSelect({ id: bu.appId, name: bu.appName });
      }
    }
    //console.log("selcted Items:"+JSON.stringify(this.selectedItems));
  }

}
