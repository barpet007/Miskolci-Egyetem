import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Toplist } from '../toplist.model';

@Component({
  selector: 'app-toplist-selector',
  standalone: true,
  imports: [CommonModule],
  template: `
    <h3>Válassz toplistát!</h3>
    <ul>
      <li *ngFor="let list of toplists" 
          (click)="select(list)" 
          [class.selected]="list.id === selectedId"
          style="cursor:pointer;">
        {{list.name}} ({{list.type}})
      </li>
    </ul>
    <button (click)="createNew()">Új toplista létrehozása</button>
  `,
  styles: [`
    .selected { font-weight: bold; color: #1976d2; }
    ul { list-style: none; padding: 0; }
    li { margin-bottom: 4px; }
  `]
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