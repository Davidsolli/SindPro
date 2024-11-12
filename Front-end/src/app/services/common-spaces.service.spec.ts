import { TestBed } from '@angular/core/testing';

import { CommonSpacesService } from './common-spaces.service';

describe('CommonSpacesService', () => {
  let service: CommonSpacesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CommonSpacesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
