import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardMessageLayoutComponent } from './card-message-layout.component';

describe('CardMessageLayoutComponent', () => {
  let component: CardMessageLayoutComponent;
  let fixture: ComponentFixture<CardMessageLayoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CardMessageLayoutComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CardMessageLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
