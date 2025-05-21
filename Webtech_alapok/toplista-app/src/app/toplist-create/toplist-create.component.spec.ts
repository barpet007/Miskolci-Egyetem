import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ToplistCreateComponent } from './toplist-create.component';

describe('ToplistCreateComponent', () => {
  let component: ToplistCreateComponent;
  let fixture: ComponentFixture<ToplistCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ToplistCreateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ToplistCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
