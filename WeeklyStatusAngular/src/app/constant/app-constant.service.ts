import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AppConstantService {
  readonly HOST_NAME: string = '/weeklystatus';
  readonly headers = new HttpHeaders({'Content-Type':'application/json'});
  readonly  options = {
   headers: new HttpHeaders({'Content-Type':'application/json'}),
     observe: 'body' 
 }

  constructor() { }
}
