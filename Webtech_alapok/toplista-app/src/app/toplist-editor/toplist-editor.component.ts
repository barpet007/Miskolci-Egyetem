import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Toplist, ToplistItem } from '../toplist.model';

@Component({
  selector: 'app-toplist-editor',
  template: `
    <div *ngIf="toplist">
      <h3>{{toplist.name}} szerkesztése</h3>
      <ul>
        <li *ngFor="let item of toplist.items; let i = index">
          <input [(ngModel)]="item.title" placeholder="Cím">
          <input [(ngModel)]="item.description" placeholder="Leírás">
          <input type="number" [(ngModel)]="item.rank" min="1" max="10" style="width:40px">
          <button (click)="remove(i)">Törlés</button>
        </li>
      </ul>
      <button (click)="add()">Új elem</button>
      <button (click)="save()">Mentés</button>
    </div>
  `
})
export class ToplistEditorComponent {
  @Input() toplist?: Toplist;
  @Output() saveToplist = new EventEmitter<Toplist>();

  add() {
    if (this.toplist && this.toplist.items.length < 10) {
      this.toplist.items.push({ rank: this.toplist.items.length + 1, title: '' });
    }
  }
  remove(i: number) {
    this.toplist?.items.splice(i, 1);
  }
  save() {
    if (this.toplist) this.saveToplist.emit(this.toplist);
  }
}
