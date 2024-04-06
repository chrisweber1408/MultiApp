import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomizerAddPageComponent } from './homizer-add-page.component';

describe('HomizerAddPageComponent', () => {
  let component: HomizerAddPageComponent;
  let fixture: ComponentFixture<HomizerAddPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HomizerAddPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HomizerAddPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
