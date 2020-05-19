import { ProjectDetail } from "./ProjectDetail";
import { ProjectStatusDetailNew } from "./ProjectStatusDetailNew";

export class TaskDetail {
		
    taskId:number;
    taskName:string;
	taskType:string;
	createdBy:string;
	taskCreationDate:Date;
	lastUpdatedBy:string;
	projectStatusDetails:ProjectStatusDetailNew[];
}
	
		
	