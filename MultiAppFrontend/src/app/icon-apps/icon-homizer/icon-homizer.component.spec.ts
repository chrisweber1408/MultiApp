import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IconHomizerComponent } from './icon-homizer.component';

describe('IconHomizerComponent', () => {
  let component: IconHomizerComponent;
  let fixture: ComponentFixture<IconHomizerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IconHomizerComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(IconHomizerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
