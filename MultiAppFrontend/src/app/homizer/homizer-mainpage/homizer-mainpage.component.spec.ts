import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomizerMainpageComponent } from './homizer-mainpage.component';

describe('HomizerMainpageComponent', () => {
  let component: HomizerMainpageComponent;
  let fixture: ComponentFixture<HomizerMainpageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HomizerMainpageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HomizerMainpageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
