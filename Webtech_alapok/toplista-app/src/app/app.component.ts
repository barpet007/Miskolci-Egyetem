import { Component } from '@angular/core';
import { Toplist } from './toplist.model';

@Component({
  selector: 'app-root',
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
    // már referenciában szerkesztjük, csak frissítjük a nézetet
    alert('Mentve!');
  }
}
