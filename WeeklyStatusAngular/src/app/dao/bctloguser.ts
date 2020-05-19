import { UserType } from "./user-type";
import { ProjectDetail } from "./ProjectDetail";

export class Bctloguser {
    userId:string;
    userName:string;
    password:string;
    emailId:string;
    userType:string[];
    projectDetails:ProjectDetail[];
}
