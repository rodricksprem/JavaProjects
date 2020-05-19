import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes} from '@angular/router';
import { JsonpModule } from '@angular/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { SideBarComponent } from './components/side-bar/side-bar.component';
import { DashBoardComponent } from './components/dash-board/dash-board.component';
import { DashBoardContentComponent } from './components/dash-board-content/dash-board-content.component';
import { LoggingComponent } from './components/logging/logging.component';
import { UserService } from './service/user.service';
import { LogService } from './service/log.service';
import { AppConstantService } from './constant/app-constant.service';
import { AgGridModule } from 'ag-grid-angular';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import { MorrisJsModule } from 'angular-morris-js';
import { UserComponent } from './components/user/user.component';
import { LogDetailsModelComponent } from './components/log-details-model/log-details-model.component';
import { DatePipe } from '@angular/common';
import { GlobalErrorHandler } from './service/GlobalErrorHandler';
import { ErrorHandler } from '@angular/core';
import { ErrorPageComponent } from './components/error-page/error-page.component';
import { NewInterfaceComponent } from './components/task_status/task-status.component';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { Ng2CompleterModule } from 'ng2-completer';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Ng4LoadingSpinnerModule } from 'ng4-loading-spinner';
import { ProjectStatusComponet } from './components/project-status/project-status.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AuthenticationService, AlertService, TokenStorageService } from './_services';
import { AuthGuard } from './_guards';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AlertComponent } from './components/alerts';
import { HomeComponent } from './components/home';
import { JwtInterceptor } from './_helpers/jwt.interceptor';
import {  ErrorInterceptor } from './_helpers/error.interceptor';

const appRoutes:Routes=[
   // otherwise redirect to home
  {path:'error', component: ErrorPageComponent},
   {path:'dashboard', component: DashBoardComponent},
   {path:'logging', component: LoggingComponent},
     {path:'user', component: UserComponent},
   {path:'newInterface', component: NewInterfaceComponent},
   {path:'projectStatus', component: ProjectStatusComponet},
   { path: '', component: DashBoardComponent, canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
   // otherwise redirect to home
    { path: '**', redirectTo: '' }
  ];

@NgModule({
  declarations: [
    AppComponent,
    AlertComponent,
    HomeComponent,
      HeaderComponent,
    SideBarComponent,
    DashBoardComponent,
    DashBoardContentComponent,
    //NotificationComponent,
    LoggingComponent,
      UserComponent,
      LogDetailsModelComponent,
    //NotificationDetailsModelComponent,
      //GeneralInstructionComponent,
    ErrorPageComponent,
    NewInterfaceComponent,
    ProjectStatusComponet,
    LoginComponent,
    RegisterComponent

    
    //DataPurgeComponent,
    //DataPurgeDetailsComponent,   
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    JsonpModule,
    ReactiveFormsModule,
    HttpClientModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    Ng4LoadingSpinnerModule.forRoot(),
    RouterModule.forRoot(
      appRoutes
      ,{useHash: true} // <-- debugging purposes only
    ),
    AgGridModule.withComponents([]),
    ChartsModule,
    MorrisJsModule,
    Ng2CompleterModule,
    NgMultiSelectDropDownModule.forRoot()

  ],
  providers: [
    AuthGuard,
        AlertService,
        AuthenticationService,
        TokenStorageService,
    UserService,
    LogService,
    AppConstantService,
    GlobalErrorHandler,
   { provide: ErrorHandler, useClass: GlobalErrorHandler },
   { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
   { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
   
    DatePipe],

  // providers: [UserService,BioNotifyService,BioLogService,AppConstantService,DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
