import { TestBed, inject } from '@angular/core/testing';

import { AppConstantService } from './app-constant.service';

describe('AppConstantService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AppConstantService]
    });
  });

  it('should be created', inject([AppConstantService], (service: AppConstantService) => {
    expect(service).toBeTruthy();
  }));
});
