import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateWarningComponent } from './create-warning.component';

describe('CreateWarningComponent', () => {
  let component: CreateWarningComponent;
  let fixture: ComponentFixture<CreateWarningComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateWarningComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreateWarningComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
