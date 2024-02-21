import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomizerItemMainpageComponent } from './homizer-item-mainpage.component';

describe('HomizerMainpageComponent', () => {
  let component: HomizerItemMainpageComponent;
  let fixture: ComponentFixture<HomizerItemMainpageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HomizerItemMainpageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HomizerItemMainpageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
