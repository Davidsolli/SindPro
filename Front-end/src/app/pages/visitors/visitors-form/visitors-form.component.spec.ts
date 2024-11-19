import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VisitorsFormComponent } from './visitors-form.component';

describe('VisitorsFormComponent', () => {
  let component: VisitorsFormComponent;
  let fixture: ComponentFixture<VisitorsFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VisitorsFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VisitorsFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
