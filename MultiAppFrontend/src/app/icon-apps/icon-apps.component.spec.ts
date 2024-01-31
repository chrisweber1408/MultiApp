import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IconAppsComponent } from './icon-apps.component';

describe('IconAppsComponent', () => {
  let component: IconAppsComponent;
  let fixture: ComponentFixture<IconAppsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IconAppsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(IconAppsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
