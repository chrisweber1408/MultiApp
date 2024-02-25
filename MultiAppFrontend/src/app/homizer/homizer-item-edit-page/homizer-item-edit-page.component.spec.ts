import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomizerItemEditPageComponent } from './homizer-item-edit-page.component';

describe('HomizerItemEditPageComponent', () => {
  let component: HomizerItemEditPageComponent;
  let fixture: ComponentFixture<HomizerItemEditPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HomizerItemEditPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HomizerItemEditPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
