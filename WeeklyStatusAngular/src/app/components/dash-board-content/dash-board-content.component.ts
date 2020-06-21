import { Component, ViewChild, ElementRef, OnInit } from '@angular/core';
import { Runtime, Inspector } from "@observablehq/notebook-runtime";
import { DashboardService } from '../../service/dashboard.service';
import { Router } from '@angular/router';
import { BUSearch } from '../../dao/busearch';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';
import { UserSessionService } from '../../service/user-session.service';
import * as  Highcharts from 'highcharts';

import More from 'highcharts/highcharts-more.js';
More(Highcharts);
import Drilldown from 'highcharts/modules/drilldown';
Drilldown(Highcharts);
import NoDataToDisplay from 'highcharts/modules/no-data-to-display'
NoDataToDisplay(Highcharts);
// Load the exporting module.
import Exporting from 'highcharts/modules/exporting';
import ExportData from 'highcharts/modules/export-data';

import accessibility from 'highcharts/modules/accessibility';
import point from 'highcharts/modules/accessibility';
import { TicketData } from 'src/app/dao/Ticket-Data';
import { InfraData } from 'src/app/dao/Infra-Data';
import { IVMSInfraData } from 'src/app/dao/IVMSInfra-Data';
// Initialize exporting module.
Exporting(Highcharts);
declare var $: any

@Component({
  selector: 'app-dash-board-content',
  templateUrl: './dash-board-content.component.html',
  styleUrls: ['./dash-board-content.component.css', './highchart.css']
})
export class DashBoardContentComponent implements OnInit {

  busearch = null;
  per: number = 66;
  projectName:string;
  ticketDataList:Array<TicketData>=[];
  meezaTicketDataList:Array<TicketData>=[];
  infraDataList:Array<InfraData>= [];
  ivmsInfraDataList:Array<IVMSInfraData>= [];
  
  cgweeks:Array<String>= [];
  ivmsweeks:Array<String>= [];

  constructor(private _userSessionService: UserSessionService, private spinnerService: Ng4LoadingSpinnerService,  private _dashboardService: DashboardService, private _router: Router) {
  }

  @ViewChild("containerElement", { read: ElementRef }) containerPieChart: ElementRef;
  @ViewChild("containerElementNotification", { read: ElementRef }) containerNotification: ElementRef;
  @ViewChild("containerElementTransaction", { read: ElementRef }) containerTransaction: ElementRef;
  @ViewChild("containerElementSankey", { read: ElementRef }) containerSankey: ElementRef;
  public clickableTicketing() {
    let chartSubData = [];
    let appData: any[][];
    appData = [];
    let index: number = 0;
    appData[0] = [];//l1IssuesOpened
      appData[1] = [];//l1IssuesClosed
      appData[2] = [];//l2IssuesOpened
      appData[3] = [];//l2IssuesClosed
      appData[4] = []; //ticketCreatedDate
    for (let applicationData of this.ticketDataList) {
      //subCharData.push([data.name, data.y]) ;
    
      appData[0][index] = applicationData.l1IssuesOpened;
      appData[1][index] = applicationData.l1IssuesClosed;
      appData[2][index] = applicationData.l2IssuesOpened;
      appData[3][index] = applicationData.l2IssuesClosed;
      appData[4][index] = applicationData.ticketCreatedDate;
      
      console.log(appData);
      index = index + 1;

    }
    //this.networkChartData = [];
     Highcharts.chart(this.containerPieChart.nativeElement, {

      // Created pie chart using Highchart
      chart: {
       type: "spline",
       events: {
        load: function() {
          this.xAxis[0].setExtremes(index-6, index-1);
        }
      }
      },
      title: {
        text: this.ticketDataList[0].projectName
      },
      subtitle: {
        text: ''
      },
      xAxis: {
        tickmarkPlacement: 'between',
        //tickmarkPlacement: 'on',
        categories: appData[4]
      },
      yAxis: {
        title: {
          text: 'Number of Tickets'
      }
      },
      legend: {
        layout: 'vertical',
        align: 'right',
        verticalAlign: 'middle',
        
    },
     
      series: [{
        name: 'L1TicketsOpened',
        colors: ['#7cb5ec'],
        data:   appData[0]

      } as Highcharts.SeriesColumnOptions, {
        name: 'L1TicketsClosed',
        data: appData[1]
      } as Highcharts.SeriesColumnOptions,
      {
        name: 'L2TickstsOpened',
        data: appData[2]
      } as Highcharts.SeriesColumnOptions,
      {
        name: 'L2TickstsClosed',
        data: appData[3]
      } as Highcharts.SeriesColumnOptions],
      noData: {
        style: {
            fontWeight: 'bold',
            fontSize: '15px',
            color: '#303030'
        }
    },
      responsive: {
        rules: [{
            condition: {
                maxWidth: 200
            },
            chartOptions: {
                legend: {
                    layout: 'horizontal',
                    align: 'center',
                    verticalAlign: 'bottom'
                }
            }
        }]
    }
    });
    var stepWidth = 5;
    // the button action
    $('#beginning').click(function() {
      var chart =$('#containerElement').highcharts();
      chart.xAxis[0].setExtremes(0, 5);
    });
  
    $('#forward').click(function() {
      
      var chart = $('#containerElement').highcharts();
      var currentMin = chart.xAxis[0].getExtremes().min;
      var currentMax = chart.xAxis[0].getExtremes().max;
  
      chart.xAxis[0].setExtremes(currentMin + stepWidth, currentMax + stepWidth);
    });
  
    $('#back').click(function() {
      var chart = $('#containerElement').highcharts();
      var currentMin = chart.xAxis[0].getExtremes().min;
      var currentMax = chart.xAxis[0].getExtremes().max;
  
      chart.xAxis[0].setExtremes(currentMin - stepWidth, currentMax - stepWidth);
    });
  
    $('#ending').click(function() {
      var chart  =$('#containerElement').highcharts();
      chart.xAxis[0].setExtremes(9, 11);
    });

  }

  public clickableTransaction() {
    let chartSubData = [];
    let appData: any[][];
    appData = [];
    let index: number = 0;
    appData[0] = [];//l1IssuesOpened
      appData[1] = [];//l1IssuesClosed
      appData[2] = [];//l2IssuesOpened
      appData[3] = [];//l2IssuesClosed
      appData[4] = []; //ticketCreatedDate
    for (let applicationData of this.meezaTicketDataList) {
      //subCharData.push([data.name, data.y]) ;
    
      appData[0][index] = applicationData.l1IssuesOpened;
      appData[1][index] = applicationData.l1IssuesClosed;
      appData[2][index] = applicationData.l2IssuesOpened;
      appData[3][index] = applicationData.l2IssuesClosed;
      appData[4][index] = applicationData.ticketCreatedDate;
      
      console.log(appData);
      index = index + 1;

    }
    //this.networkChartData = [];
     Highcharts.chart(this.containerTransaction.nativeElement, {

      // Created pie chart using Highchart
      chart: {
       type: "spline",
       events: {
        load: function() {
          this.xAxis[0].setExtremes(index-6, index-1);
        }
      }
      },
      title: {
        text: this.meezaTicketDataList[0].projectName
      },
      subtitle: {
        text: ''
      },
      xAxis: {
        tickmarkPlacement: 'between',
        //tickmarkPlacement: 'on',
        categories: appData[4]
      },
      yAxis: {
        title: {
          text: 'Number of Tickets'
      }
      },
      legend: {
        layout: 'vertical',
        align: 'right',
        verticalAlign: 'middle',
        
    },
      series: [{
        name: 'L1TicketsOpened',
        colors: ['#7cb5ec'],
        data:   appData[0]

      } as Highcharts.SeriesColumnOptions, {
        name: 'L1TicketsClosed',
        data: appData[1]
      } as Highcharts.SeriesColumnOptions,
      {
        name: 'L2TickstsOpened',
        data: appData[2]
      } as Highcharts.SeriesColumnOptions,
      {
        name: 'L2TickstsClosed',
        data: appData[3]
      } as Highcharts.SeriesColumnOptions],
      noData: {
        style: {
            fontWeight: 'bold',
            fontSize: '15px',
            color: '#303030'
        }
    },
      responsive: {
        rules: [{
            condition: {
                maxWidth: 200
            },
            chartOptions: {
                legend: {
                    layout: 'horizontal',
                    align: 'center',
                    verticalAlign: 'bottom'
                }
            }
        }]
    }
    });
    var stepWidth = 5;
    // the button action
    $('#forwardTransaction').click(function() {
      
      var chart = $('#containerElementTransaction').highcharts();
      var currentMin = chart.xAxis[0].getExtremes().min;
      var currentMax = chart.xAxis[0].getExtremes().max;
  
      chart.xAxis[0].setExtremes(currentMin + stepWidth, currentMax + stepWidth);
    });
  
    $('#backTransaction').click(function() {
      var chart = $('#containerElementTransaction').highcharts();
      var currentMin = chart.xAxis[0].getExtremes().min;
      var currentMax = chart.xAxis[0].getExtremes().max;
  
      chart.xAxis[0].setExtremes(currentMin - stepWidth, currentMax - stepWidth);
    });
  
  }
  public Infra1Chart(weekSelected:string) {
    console.log(this.infraDataList.length)
    let appData: any[][];
    appData = [];
    let catagoriesval: any[];
    
    let index: number = 0;
    appData[0] = [];//availCPULoad
      appData[1] = [];//availMemSpace
      appData[2] = [];//availStorageSpace
      appData[3] = [];//numberOfServices
      appData[4] = []; //numberOfDb
      catagoriesval=[];
       //ticketCreatedDate
    for (let applicationData of this.infraDataList) {
      //subCharData.push([data.name, data.y]) ;
      console.log(applicationData.weekDuration)
      
    if(applicationData.weekDuration==weekSelected){
      appData[0][index] = applicationData.availCPULoad;
      appData[1][index] = applicationData.availMemSpace;
      appData[2][index] = applicationData.availStorageSpace;
      appData[3][index] = applicationData.numberOfServices;
      appData[4][index] = applicationData.numberOfDb;
      catagoriesval[index] = applicationData.serverName;
      
      console.log(appData);
      console.log(" iteration ",index);
      index = index + 1;

    }
  }
  console.log(catagoriesval);
    var chartWidth=0;
    var unitType = 'week';
    var chartType = 'column'; 
    
    // Test case 2
    //var unitType = 'month';
    //var chartType = 'bar'; 
    
    // Set options
    var options:any = {
      chart: {
        renderTo: 'container',
        type: chartType,
        zoomType: 'x'
      },
      title: {
        text: this.infraDataList[0].projectName
      },
      
      xAxis: {
        categories: catagoriesval
        
      },
      plotOptions: {
        column: {
            stacking: 'normal'
        },
        series: {
          borderWidth: 0,
          dataLabels: {
            enabled: true,
            format: '{point.y:.0f}'
          },
          dataGrouping: {
            enabled: true,
            forced: true
          }
        }
      },
    
      series: [
        {
            name:'CPU load in %',
            data: appData[0],
            stack:0
            
             
        }  ,
        {
          name: 'Available Memory Space in %',
          data: appData[1],
          stack:0        
      }   , {
        name: 'Available Storage Space in %',
        data: appData[2],
        stack:0 
      } ,{
        name: 'Number of Services',
        data: appData[3]
    }    ,
    {
      name: 'Number of DB',
      data: appData[4]
    } 
      ] 
    };

    Highcharts.chart(this.containerSankey.nativeElement,options);
    console.log("end of Infra1Chart method");
  }
  public Infra2Chart(weekSelected:string) {
    console.log(this.ivmsInfraDataList)
    console.log(" selected week ",weekSelected);
    let appData: any[][];
    appData = [];
    let catagoriesval: any[];
    
    let index: number = 0;
    appData[0] = [];//appAvailablity
      appData[1] = [];//dbAvailablity
      appData[2] = [];//serverAvailablity
      appData[3] = [];//numberOfVehicles
      appData[4] = []; //numberOfServers
      appData[5] = [];//numberOfClients
      appData[6] = [];//numberOfDb
      catagoriesval=[];//
       //ticketCreatedDate
    for (let applicationData of this.ivmsInfraDataList) {
      //subCharData.push([data.name, data.y]) ;
      if(applicationData.weekDuration==weekSelected){
        console.log(" applicationData.weekDuration matched");
      appData[0][index] = applicationData.appAvailablity;
      appData[1][index] = applicationData.dbAvailablity;
      appData[2][index] = applicationData.serverAvailablity;
      appData[3][index] = applicationData.numberOfVehicles/100;
      appData[4][index] = applicationData.numberOfServers;
      appData[5][index] = applicationData.numberOfClients;
      appData[6][index] = applicationData.numberOfDb;
      catagoriesval[index] = applicationData.virtualFarms;
      index = index + 1;

      }
   
      
      console.log(appData);
      console.log(" iteration ",index);
      

    }
    
  console.log(catagoriesval);
    var chartWidth=0;
    var unitType = 'week';
    var chartType = 'column'; 
    
    // Test case 2
    //var unitType = 'month';
    //var chartType = 'bar'; 
    
    // Set options
    var options:any = {
      chart: {
        renderTo: 'container',
        type: chartType,
        zoomType: 'x'
      },
      title: {
        text: this.ivmsInfraDataList[0].projectName
      },
      
      xAxis: {
        categories: catagoriesval
        
      },
      plotOptions: {
        column: {
            stacking: 'normal'
        },
        series: {
          borderWidth: 0,
          dataLabels: {
            enabled: true,
            format: '{point.y:.0f}'
          },
          dataGrouping: {
            enabled: true,
            forced: true
          }
        }
      },
    
      series: [
        {
            name:'Up Time Availablity in %',
            data: appData[0],
            stack:0
            
             
        }  ,
        {
          name: 'DB Availablity in %',
          data: appData[1],
          stack:1        
      }   , {
        name: 'Server Availablity  in %',
        data: appData[2],
        stack:2 
      } ,{
        name: 'Number of Vechiles ( X 100)',
        data: appData[3],
        stack:3
      }    ,
    {
      name: 'Number of Servers',
      data: appData[4],
      stack:4
    } ,
    {
      name: 'Number of Clients',
      data: appData[5],
      stack:4
    } ,
    {
      name: 'Number of DBs',
      data: appData[6],
      stack:4
    } 
      ] 
    };

    Highcharts.chart(this.containerNotification.nativeElement,options);
    console.log("end of Infra2Chart method");
  }

  setDefaultValue(name) {
   
  }

  ngOnInit() {

   
      //get weeks for CG Support
       this._dashboardService.getWeeks(700).pipe().subscribe((weeksList:String[]) => {
      this.cgweeks=weeksList;
      console.log("this.weeks "+this.cgweeks);
    }), (error) => {
      console.log(error);
    
    }
     //get weeks for IVMS Support
     this._dashboardService.getWeeks(900).pipe().subscribe((weeksList:String[]) => {
      this.ivmsweeks=weeksList;
      console.log("this.weeks "+this.ivmsweeks);
    }), (error) => {
      console.log(error);
    
    }
    this._dashboardService.getChartDataTesla().subscribe((ticketDataList:TicketData[]) => {

      this.ticketDataList = ticketDataList;
      console.log("ticketDataList ",this.ticketDataList.length);
     
        
      this.clickableTicketing();
 
      }), (error) => {
      console.log(error);
    }

    this._dashboardService.getChartDataMeeza().subscribe((ticketDataList:TicketData[]) => {

      this.meezaTicketDataList = ticketDataList;
      console.log("meezaTicketDataList ",this.meezaTicketDataList.length);
     
        
      this.clickableTransaction();
 
      }), (error) => {
      console.log(error);
    }

    this._dashboardService.getChartDataIVMSIMpl().subscribe((infraDataList:IVMSInfraData[]) => {
      this.ivmsInfraDataList=[]
      var weekselected="Apr 20,2020-Apr 26,2020";
     this.ivmsInfraDataList=infraDataList;
      console.log("ivminfraDataList ",this.ivmsInfraDataList.length);
      this.Infra2Chart(weekselected);
    //  $('#searchweek').change();
    
      }), (error) => {
      console.log(error);
    }
   

    this._dashboardService.getChartDataCGTechImpl().subscribe((infraDataList:InfraData[]) => {
      this.infraDataList=[]
      var weekselected="Apr 20,2020-Apr 26,2020";

      this.infraDataList=infraDataList;
      console.log("infraDataList ",this.infraDataList.length);
      this.Infra1Chart(weekselected);
      $('#searchweek').change();
    
      }), (error) => {
      console.log(error);
    }
   

    
    


  }
  
  

  onInfra1WeekChange(value){
    console.log("weekselected ",value);
    var weekselected=value;
    this.Infra1Chart(weekselected);
   

  }

  onInfra2WeekChange(value){
    console.log("weekselected ",value);
    var weekselected=value;
    this.Infra2Chart(weekselected);
   

  }

 
}
