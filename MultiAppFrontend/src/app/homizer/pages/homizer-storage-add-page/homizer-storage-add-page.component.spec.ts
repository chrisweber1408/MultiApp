import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomizerStorageAddPageComponent } from './homizer-storage-add-page.component';

describe('HomizerStorageAddPageComponent', () => {
  let component: HomizerStorageAddPageComponent;
  let fixture: ComponentFixture<HomizerStorageAddPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HomizerStorageAddPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomizerStorageAddPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
