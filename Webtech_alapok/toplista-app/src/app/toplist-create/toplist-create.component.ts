import { Component, Output, EventEmitter } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Toplist } from '../toplist.model';

@Component({
  selector: 'app-toplist-create',
  standalone: true,
  imports: [FormsModule],
  template: `
    <h3>Új toplista létrehozása</h3>
    <input [(ngModel)]="name" placeholder="Lista neve">
    <select [(ngModel)]="type">
      <option value="film">Film</option>
      <option value="zene">Zene</option>
      <option value="sorozat">Sorozat</option>
    </select>
    <button (click)="create()">Létrehoz</button>
  `
})
export class ToplistCreateComponent {
  name = '';
  type: 'film' | 'zene' | 'sorozat' = 'film';
  @Output() createToplist = new EventEmitter<Toplist>();

  create() {
    if (this.name.trim()) {
      this.createToplist.emit({
        id: Date.now(),
        name: this.name,
        type: this.type,
        items: []
      });
      this.name = '';
    }
  }
}