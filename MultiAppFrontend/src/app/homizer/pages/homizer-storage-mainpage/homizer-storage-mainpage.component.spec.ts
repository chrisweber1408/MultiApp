import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomizerStorageMainpageComponent } from './homizer-storage-mainpage.component';

describe('HomizerStorageMainpageComponent', () => {
  let component: HomizerStorageMainpageComponent;
  let fixture: ComponentFixture<HomizerStorageMainpageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HomizerStorageMainpageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HomizerStorageMainpageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
