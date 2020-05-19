import { BctLog } from "./bct-log";
import { Bctloguser } from "./bctloguser";
import { TaskDetail } from "./TaskDetail";
import { Account } from "./Account";
import { ProjectStatusDetailNew } from "./ProjectStatusDetailNew";
import { WeekStatus } from "./WeekStatus";

export class ProjectDetail {
		
    projectId:number;
    projectName:string;
    projectOwner:string;
   
    users:Bctloguser[];  
    taskDetails:TaskDetail[];
   // projectStatusDetails:ProjectStatusDetailNew[];
    account:Account;
    projectStartDate:string;
    projectEndDate:string;
    createdBy:string;
    updatedBy:string;
    weekDuration:WeekStatus[];

    
}
