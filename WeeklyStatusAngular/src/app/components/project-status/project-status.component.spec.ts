import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectStatusComponet } from './project-status.component';

describe('ProjectStatusComponent', () => {
  let component: ProjectStatusComponet;
  let fixture: ComponentFixture<ProjectStatusComponet>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProjectStatusComponet ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProjectStatusComponet);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
