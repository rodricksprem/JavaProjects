import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewInterfaceComponent } from './task-status.component';

describe('NewInterfaceComponent', () => {
  let component: NewInterfaceComponent;
  let fixture: ComponentFixture<NewInterfaceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewInterfaceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewInterfaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
