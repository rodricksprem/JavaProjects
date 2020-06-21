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
  selector: 'app-dash-board2-content',
  templateUrl: './dash-board2-content.component.html',
  styleUrls: ['./dash-board2-content.component.css', './highchart.css']
})
export class DashBoard2ContentComponent implements OnInit {

  busearch = null;
  per: number = 66;
  projectName:string;
  teslaTicketDataList:Array<TicketData>=[];
  oabTicketDataList:Array<TicketData>=[];
  infraDataList:Array<InfraData>= [];
  ivmsInfraDataList:Array<IVMSInfraData>= [];
 

  constructor(private _userSessionService: UserSessionService, private spinnerService: Ng4LoadingSpinnerService,  private _dashboardService: DashboardService, private _router: Router) {
  }

  @ViewChild("containerTeslaNetwork", { read: ElementRef }) containerTeslaNetwork: ElementRef;
  @ViewChild("containerElementOAB", { read: ElementRef }) containerElementOAB: ElementRef;
  public teslaNwOpsTickets() {
    let chartSubData = [];
    let appData: any[][];
    appData = [];
    let index: number = 0;
    appData[0] = [];//l1IssuesOpened
      appData[1] = [];//l1IssuesClosed
      appData[2] = [];//l2IssuesOpened
      appData[3] = [];//l2IssuesClosed
      appData[4] = []; //ticketCreatedDate
    for (let applicationData of this.teslaTicketDataList) {
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
     Highcharts.chart(this.containerTeslaNetwork.nativeElement, {

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
        text: this.teslaTicketDataList[0].projectName
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
   
  
    $('#forward').click(function() {
      
      var chart = $('#containerTeslaNetwork').highcharts();
      var currentMin = chart.xAxis[0].getExtremes().min;
      var currentMax = chart.xAxis[0].getExtremes().max;
  
      chart.xAxis[0].setExtremes(currentMin + stepWidth, currentMax + stepWidth);
    });
  
    $('#back').click(function() {
      var chart = $('#containerTeslaNetwork').highcharts();
      var currentMin = chart.xAxis[0].getExtremes().min;
      var currentMax = chart.xAxis[0].getExtremes().max;
  
      chart.xAxis[0].setExtremes(currentMin - stepWidth, currentMax - stepWidth);
    });
  
  }

  public oabTickets() {
    let chartSubData = [];
    let appData: any[][];
    appData = [];
    let index: number = 0;
    appData[0] = [];//l1IssuesOpened
      appData[1] = [];//l1IssuesClosed
      appData[2] = [];//l2IssuesOpened
      appData[3] = [];//l2IssuesClosed
      appData[4] = []; //ticketCreatedDate
    for (let applicationData of this.oabTicketDataList) {
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
     Highcharts.chart(this.containerElementOAB.nativeElement, {

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
        text: this.oabTicketDataList[0].projectName
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
     ],
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
    $('#forwardOAB').click(function() {
      
      var chart = $('#containerElementOAB').highcharts();
      var currentMin = chart.xAxis[0].getExtremes().min;
      var currentMax = chart.xAxis[0].getExtremes().max;
  
      chart.xAxis[0].setExtremes(currentMin + stepWidth, currentMax + stepWidth);
    });
  
    $('#backOAB').click(function() {
      var chart = $('#containerElementOAB').highcharts();
      var currentMin = chart.xAxis[0].getExtremes().min;
      var currentMax = chart.xAxis[0].getExtremes().max;
  
      chart.xAxis[0].setExtremes(currentMin - stepWidth, currentMax - stepWidth);
    });
  
  }
  

  ngOnInit() {

   
      
      
    this._dashboardService.getChartDataTeslaNwOp().subscribe((ticketDataList:TicketData[]) => {

      this.teslaTicketDataList = ticketDataList;
      console.log(" tesls network ops ticketDataList ",this.teslaTicketDataList.length);
      /*this.ticketDataList.forEach( function( ticketData, idx ) {
        this.weeks.push(ticketData.weekDuration);
        console.log("this.weeks ",this.weeks);
      } );*/
        
      this.teslaNwOpsTickets();
 
      }), (error) => {
      console.log(error);
    }

    this._dashboardService.getChartDataOAB().subscribe((ticketDataList:TicketData[]) => {

      this.oabTicketDataList = ticketDataList;
      console.log(" OAB Tickets ",this.oabTicketDataList.length);
      /*this.ticketDataList.forEach( function( ticketData, idx ) {
        this.weeks.push(ticketData.weekDuration);
        console.log("this.weeks ",this.weeks);
      } );*/
        
      this.oabTickets();
 
      }), (error) => {
      console.log(error);
    }

   

    
    


  }
  
 
}
