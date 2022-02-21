import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatepqrsComponent } from './createpqrs.component';

describe('CreatepqrsComponent', () => {
  let component: CreatepqrsComponent;
  let fixture: ComponentFixture<CreatepqrsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreatepqrsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreatepqrsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
