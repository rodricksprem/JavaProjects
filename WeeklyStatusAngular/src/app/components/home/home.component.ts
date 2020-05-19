import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';

import { TokenStorageService } from 'src/app/_services';

@Component({templateUrl: 'home.component.html'})
export class HomeComponent implements OnInit {
    currentUser: string;
    constructor(private tokenService: TokenStorageService) {
        console.log(" HomeComponent ")
      //  this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
      this.currentUser =tokenService.getUsername();
    }

    ngOnInit() {
//        this.loadAllUsers();
    }

}