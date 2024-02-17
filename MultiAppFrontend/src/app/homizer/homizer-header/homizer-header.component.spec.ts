import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomizerHeaderComponent } from './homizer-header.component';

describe('HomizerHeaderComponent', () => {
  let component: HomizerHeaderComponent;
  let fixture: ComponentFixture<HomizerHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HomizerHeaderComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HomizerHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
