import { ErrorHandler, Injectable, Injector } from '@angular/core';
import { Router } from '@angular/router';
//import * as StackTrace from 'stacktrace-js';
@Injectable()
export class GlobalErrorHandler implements ErrorHandler {
constructor(private injector: Injector) { }
handleError(error ) {
  
       const router = this.injector.get(Router);
 console.log("redirecting to error page");
     //  router.navigate(['/error'],{ queryParams: error });
     // router.navigate(['/error'],);
   // Log the error anyway
   console.error('It happens: ', error);
 }
     
  }

