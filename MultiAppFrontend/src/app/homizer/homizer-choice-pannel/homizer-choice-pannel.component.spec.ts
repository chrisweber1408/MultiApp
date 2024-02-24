import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomizerChoicePannelComponent } from './homizer-choice-pannel.component';

describe('HomizerChoicePannelComponent', () => {
  let component: HomizerChoicePannelComponent;
  let fixture: ComponentFixture<HomizerChoicePannelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HomizerChoicePannelComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HomizerChoicePannelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
