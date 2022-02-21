import { TestBed } from '@angular/core/testing';

import { PqrsServiceService } from './pqrs-service.service';

describe('PqrsServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PqrsServiceService = TestBed.get(PqrsServiceService);
    expect(service).toBeTruthy();
  });
});
