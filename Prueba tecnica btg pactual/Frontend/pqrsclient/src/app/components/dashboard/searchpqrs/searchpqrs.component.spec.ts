import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchpqrsComponent } from './searchpqrs.component';

describe('SearchpqrsComponent', () => {
  let component: SearchpqrsComponent;
  let fixture: ComponentFixture<SearchpqrsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchpqrsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchpqrsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
