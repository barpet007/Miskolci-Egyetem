import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ToplistSelectorComponent } from './toplist-selector/toplist-selector.component';
import { ToplistEditorComponent } from './toplist-editor/toplist-editor.component';
import { ToplistCreateComponent } from './toplist-create/toplist-create.component';
import { Toplist } from './toplist.model';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    ToplistSelectorComponent,
    ToplistEditorComponent,
    ToplistCreateComponent
  ],
  template: `
    <app-toplist-selector
      [toplists]="toplists"
      [selectedId]="selectedToplist?.id"
      (selectToplist)="onSelect($event)"
      (create)="onCreate()">
    </app-toplist-selector>

    <app-toplist-create *ngIf="creating" (createToplist)="onCreated($event)"></app-toplist-create>
    <app-toplist-editor *ngIf="selectedToplist && !creating"
      [toplist]="selectedToplist"
      (saveToplist)="onSave($event)">
    </app-toplist-editor>
  `
})
export class AppComponent {
  toplists: Toplist[] = [];
  selectedToplist?: Toplist;
  creating = false;

  onSelect(list: Toplist) {
    this.selectedToplist = list;
    this.creating = false;
  }
  onCreate() {
    this.creating = true;
    this.selectedToplist = undefined;
  }
  onCreated(list: Toplist) {
    this.toplists.push(list);
    this.selectedToplist = list;
    this.creating = false;
  }
  onSave(list: Toplist) {
    alert('Mentve!');
  }
}