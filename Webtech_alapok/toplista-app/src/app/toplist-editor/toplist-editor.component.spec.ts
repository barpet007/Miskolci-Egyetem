import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ToplistEditorComponent } from './toplist-editor.component';

describe('ToplistEditorComponent', () => {
  let component: ToplistEditorComponent;
  let fixture: ComponentFixture<ToplistEditorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ToplistEditorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ToplistEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
