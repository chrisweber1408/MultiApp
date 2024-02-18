import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomizerItemListComponent } from './homizer-item-list.component';

describe('HomizerItemListComponent', () => {
  let component: HomizerItemListComponent;
  let fixture: ComponentFixture<HomizerItemListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HomizerItemListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HomizerItemListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
