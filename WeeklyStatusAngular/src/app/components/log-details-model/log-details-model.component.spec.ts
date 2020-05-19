import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LogDetailsModelComponent } from './log-details-model.component';

describe('LogDetailsModelComponent', () => {
  let component: LogDetailsModelComponent;
  let fixture: ComponentFixture<LogDetailsModelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LogDetailsModelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LogDetailsModelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
