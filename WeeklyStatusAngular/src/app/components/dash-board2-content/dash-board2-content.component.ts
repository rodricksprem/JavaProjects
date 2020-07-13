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
import { SRData } from 'src/app/dao/SRData';
import {  SRSeries } from 'src/app/dao/srseries';
import { s } from '@angular/core/src/render3';
import { WeekStatus } from 'src/app/dao/WeekStatus';
// Initialize exporting module.
Exporting(Highcharts);
declare var $: any

@Component({
  selector: 'app-dash-board2-content',
  templateUrl: './dash-board2-content.component.html',
  styleUrls: ['./dash-board2-content.component.css', './highchart.css']
})
export class DashBoard2ContentComponent implements OnInit {

  projectName: string;
  teslaTicketDataList: Array<TicketData> = [];
  oabTicketDataList: Array<TicketData> = [];
  uabTicketDataList: Array<SRData> = [];
  modifieduabTicketDataList: Array<SRData> = [];
  
  infraDataList: Array<InfraData> = [];
  ivmsInfraDataList: Array<IVMSInfraData> = [];
  srSeriesList: Array<SRSeries>=[];

  constructor(private _userSessionService: UserSessionService, private spinnerService: Ng4LoadingSpinnerService, private _dashboardService: DashboardService, private _router: Router) {
  }

  @ViewChild("containerTeslaNetwork", { read: ElementRef }) containerTeslaNetwork: ElementRef;
  @ViewChild("containerElementOAB", { read: ElementRef }) containerElementOAB: ElementRef;
  @ViewChild("containerElementAlabama", { read: ElementRef }) containerElementAlabama: ElementRef;
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
          load: function () {
            this.xAxis[0].setExtremes(index - 6, index - 1);
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
        data: appData[0]

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


    $('#forward').click(function () {

      var chart = $('#containerTeslaNetwork').highcharts();
      var currentMin = chart.xAxis[0].getExtremes().min;
      var currentMax = chart.xAxis[0].getExtremes().max;

      chart.xAxis[0].setExtremes(currentMin + stepWidth, currentMax + stepWidth);
    });

    $('#back').click(function () {
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
          load: function () {
            this.xAxis[0].setExtremes(index - 6, index - 1);
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
        data: appData[0]

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
    $('#forwardOAB').click(function () {

      var chart = $('#containerElementOAB').highcharts();
      var currentMin = chart.xAxis[0].getExtremes().min;
      var currentMax = chart.xAxis[0].getExtremes().max;

      chart.xAxis[0].setExtremes(currentMin + stepWidth, currentMax + stepWidth);
    });

    $('#backOAB').click(function () {
      var chart = $('#containerElementOAB').highcharts();
      var currentMin = chart.xAxis[0].getExtremes().min;
      var currentMax = chart.xAxis[0].getExtremes().max;

      chart.xAxis[0].setExtremes(currentMin - stepWidth, currentMax - stepWidth);
    });

  }

  public uabTickets() {

    let appData: any[][];
    appData = [];
    let srWeeks = [];
    
    let srCategories = [];
    let tempsrWeeks = [];
   
    let splitIndex = new Map<string, number>();
    let index: number = 0;
    let catagoryindex: number = 0;
    appData[0] = [];//srOpened
    appData[1] = [];//srClosed
    appData[2] = []; //srCreatedDate
    appData[3] = []; //srCategory

    let srSeries = new SRSeries();

    this.uabTicketDataList.forEach(srData => {
      tempsrWeeks.push(srData.srCreatedDate);
      srCategories.push(srData.srCategory);
    });

    srWeeks = Array.from(new Set(tempsrWeeks.map((item: Date) => item)));
    
    srCategories = Array.from(new Set(srCategories.map((item: Date) => item)));
   
    

    srCategories.forEach(category => {

      srSeries = new SRSeries();
      srSeries.name = category+"Opened";
      srSeries.data = [];
      srSeries.stack=category+"Opened";
      this.srSeriesList.push(srSeries);
      srSeries = new SRSeries();
      srSeries.name = category+"Closed";
      srSeries.data = [];
      srSeries.stack=category+"Closed";
      this.srSeriesList.push(srSeries);
    });

    console.log(srCategories);
    console.log(this.srSeriesList)
    //for each week
    for(let week of srWeeks){
      let srDataTempList:SRData[]=[];
      //get the list of SRData for each week
     srDataTempList= this.uabTicketDataList.filter(srData => {
      
      if(srData.srCreatedDate==week){
        this.modifieduabTicketDataList.push(srData);
      }
        return srData.srCreatedDate==week

       }     
     )
     console.log("week srdata list",srDataTempList);
    let categoryTempList:String[]=[]; 
    //get the list of srcategories for the given SRDataList
    srDataTempList.forEach(srDataTemp => 
      categoryTempList.push(srDataTemp.srCategory)
    );
    console.log("category list for week ",categoryTempList)
    let categoryFilterList:string[]=[]; 
    // intersect the catagory list to get the categories absent from SRDataList of given week
    categoryFilterList=srCategories.filter(value => (!(categoryTempList.includes(value))))
    console.log("filtered category ",categoryFilterList);
    categoryFilterList.forEach(categoriesTemp=>{
      //add new SRData into modifieduabtikcetDatalist
      let newSRData:SRData=new SRData();
      newSRData.srClosed=0;
      newSRData.srOpened=0;
      newSRData.srCreatedDate=week;
      newSRData.srCategory=categoriesTemp;
      this.modifieduabTicketDataList.push(newSRData);
      
    });
    }
    console.log("modifiedlist ",this.modifieduabTicketDataList);
    for (let applicationData of this.modifieduabTicketDataList) {
      //subCharData.push([data.name, data.y]) ;
          appData[0][index] = applicationData.srOpened;
          let name = applicationData.srCategory + "Opened";

      srSeries = this.srSeriesList.find(function (srSeries: SRSeries) {
        return srSeries.name.indexOf(name)>=0;
      });
      srSeries.data.push(applicationData.srOpened);

      name = applicationData.srCategory + "Closed";

      srSeries = this.srSeriesList.find(function (srSeries: SRSeries) {
        return srSeries.name.indexOf(name)>=0;
      });
      srSeries.data.push(applicationData.srClosed);

      index = index + 1;
    }
  



    //this.networkChartData = [];
   
    var options :any={
      chart: {
        type: "column",
        
      },
    title: {
       
        text: ''
    },
    
    xAxis: {
      labels: {
      //  format: '<div style="text-align:center;">&nbsp; '+srCategories+'<br /><br />{value}</div>',
        useHTML: true
      },
      tickmarkPlacement: 'between',
        categories: srWeeks
    },
    yAxis: {
     min: 0,
      title: {
        text: 'Number of SRs/CRs'
      }
    },
    tooltip: {
      formatter: function() {
        return '<b>' + this.x + '</b><br/>' +
          this.series.name + ': ' + this.y + '<br/>' +
          'Total: ' + this.point.stackTotal;
      }
    },
    plotOptions: {
      column: {
        stacking: 'normal'
      }
    },
  
    series: this.srSeriesList
  
    }
    console.log(options);
    Highcharts.chart(this.containerElementAlabama.nativeElement,options);
    console.log("end of UAB method");
    var stepWidth = 5;
    // the button action
    $('#forwardAlabama').click(function () {

      var chart = $('#containerElementAlabama').highcharts();
      var currentMin = chart.xAxis[0].getExtremes().min;
      var currentMax = chart.xAxis[0].getExtremes().max;

      chart.xAxis[0].setExtremes(currentMin + stepWidth, currentMax + stepWidth);
    });

    $('#backAlabama').click(function () {
      var chart = $('#containerElementAlabama').highcharts();
      var currentMin = chart.xAxis[0].getExtremes().min;
      var currentMax = chart.xAxis[0].getExtremes().max;

      chart.xAxis[0].setExtremes(currentMin - stepWidth, currentMax - stepWidth);
    });

  }

  ngOnInit() {




    this._dashboardService.getChartDataTeslaNwOp().subscribe((ticketDataList: TicketData[]) => {

      this.teslaTicketDataList = ticketDataList;
      console.log(" tesls network ops ticketDataList ", this.teslaTicketDataList.length);
      /*this.ticketDataList.forEach( function( ticketData, idx ) {
        this.weeks.push(ticketData.weekDuration);
        console.log("this.weeks ",this.weeks);
      } );*/

      this.teslaNwOpsTickets();

    }), (error) => {
      console.log(error);
    }

    this._dashboardService.getChartDataOAB().subscribe((ticketDataList: TicketData[]) => {

      this.oabTicketDataList = ticketDataList;
      console.log(" OAB Tickets ", this.oabTicketDataList.length);
      /*this.ticketDataList.forEach( function( ticketData, idx ) {
        this.weeks.push(ticketData.weekDuration);
        console.log("this.weeks ",this.weeks);
      } );*/

      this.oabTickets();

    }), (error) => {
      console.log(error);
    }
    this._dashboardService.getChartDataUAB().subscribe((srDataList: SRData[]) => {

      this.uabTicketDataList = srDataList;
      console.log(" UAB Tickets ", this.uabTicketDataList.length);
      /*this.ticketDataList.forEach( function( ticketData, idx ) {
        this.weeks.push(ticketData.weekDuration);
        console.log("this.weeks ",this.weeks);
      } );*/

      this.uabTickets();

    }), (error) => {
      console.log(error);
    }







  }


}
