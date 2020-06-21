import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DashBoard2ContentComponent } from './dash-board2-content.component';

describe('DashBoardContentComponent', () => {
  let component: DashBoard2ContentComponent;
  let fixture: ComponentFixture<DashBoard2ContentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DashBoard2ContentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DashBoard2ContentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
