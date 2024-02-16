import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomizerComponent } from './homizer.component';

describe('HomizerComponent', () => {
  let component: HomizerComponent;
  let fixture: ComponentFixture<HomizerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HomizerComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HomizerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
