import {ComponentFixture, TestBed} from '@angular/core/testing';

import {HomizerItemAddPageComponent} from './homizer-item-add-page.component';

describe('HomizerAddPageComponent', () => {
  let component: HomizerItemAddPageComponent;
  let fixture: ComponentFixture<HomizerItemAddPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HomizerItemAddPageComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(HomizerItemAddPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
