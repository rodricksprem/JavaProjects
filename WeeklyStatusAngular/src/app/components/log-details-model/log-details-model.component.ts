import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { BctLogDetails } from '../../dao/bct-log-details';
import FileSaver from 'file-saver';
import { ProjectDetail } from 'src/app/dao/ProjectDetail';
import { ApplicationService } from 'src/app/service/application.service';
import { AccountService } from 'src/app/service/account.service';
import { Fields } from 'src/app/dao/fields';
import { TaskDetail } from 'src/app/dao/TaskDetail';

@Component({
  selector: 'app-log-details-model',
  templateUrl: './log-details-model.component.html',
  styleUrls: ['./log-details-model.component.css']
})
export class LogDetailsModelComponent implements OnInit {

  @Input('projectName') projectName: string;
  @Input('projectID') projectID: string;
  @Input('taskDetailList') taskDetailList:TaskDetail[];
  @Input('fields') fields:Fields[];
  constructor(private _accountService:AccountService) {}
	
  ngOnInit() {
    console.log("LogDetailsModelComponent ngOnInit():");
    }

    

  downloadPayload(data)
  {
console.log("data direct:"+data.value);
 var blob = new Blob([data.value], {type: "text/plain;charset=utf-8"});
 FileSaver.saveAs(blob, "payload.xml");
 
  }

  /* hideModel()
  {
    document.getElementById('myModal').style.display = "none";
  } */
  closeModel()
  {
    
  }
}
