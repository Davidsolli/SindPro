import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RentSpaceFormComponent } from './rent-space-form.component';

describe('RentSpaceFormComponent', () => {
  let component: RentSpaceFormComponent;
  let fixture: ComponentFixture<RentSpaceFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RentSpaceFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RentSpaceFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
