import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomizerAddButtonComponent } from './homizer-add-button.component';

describe('HomizerAddButtonComponent', () => {
  let component: HomizerAddButtonComponent;
  let fixture: ComponentFixture<HomizerAddButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HomizerAddButtonComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HomizerAddButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
