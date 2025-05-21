import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Toplist } from '../toplist.model';

@Component({
  selector: 'app-toplist-selector',
  template: `
    <h3>Válassz toplistát!</h3>
    <ul>
      <li *ngFor="let list of toplists" (click)="select(list)" [class.selected]="list.id === selectedId">
        {{list.name}} ({{list.type}})
      </li>
    </ul>
    <button (click)="createNew()">Új toplista létrehozása</button>
  `,
  styles: [`.selected { font-weight: bold; }`]
})
export class ToplistSelectorComponent {
  @Input() toplists: Toplist[] = [];
  @Input() selectedId?: number;
  @Output() selectToplist = new EventEmitter<Toplist>();
  @Output() create = new EventEmitter<void>();

  select(list: Toplist) {
    this.selectToplist.emit(list);
  }
  createNew() {
    this.create.emit();
  }
}
