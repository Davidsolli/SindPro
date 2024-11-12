import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RentSpaceTableComponent } from './rent-space-table.component';

describe('RentSpaceTableComponent', () => {
  let component: RentSpaceTableComponent;
  let fixture: ComponentFixture<RentSpaceTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RentSpaceTableComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RentSpaceTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
