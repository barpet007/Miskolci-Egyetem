import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ToplistSelectorComponent } from './toplist-selector.component';

describe('ToplistSelectorComponent', () => {
  let component: ToplistSelectorComponent;
  let fixture: ComponentFixture<ToplistSelectorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ToplistSelectorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ToplistSelectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
