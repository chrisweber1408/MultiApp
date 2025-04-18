import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavigationItemComponent } from './navigation-item.component';

describe('NavigationItemComponent', () => {
  let component: NavigationItemComponent;
  let fixture: ComponentFixture<NavigationItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [NavigationItemComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NavigationItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
