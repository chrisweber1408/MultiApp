import {ComponentFixture, TestBed} from '@angular/core/testing';

import {HomizerStorageEditPageComponent} from './homizer-storage-edit-page.component';

describe('HomizerStorageEditPageComponent', () => {
  let component: HomizerStorageEditPageComponent;
  let fixture: ComponentFixture<HomizerStorageEditPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HomizerStorageEditPageComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(HomizerStorageEditPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
