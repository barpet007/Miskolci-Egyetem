import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { ToplistSelectorComponent } from './toplist-selector/toplist-selector.component';
import { ToplistEditorComponent } from './toplist-editor/toplist-editor.component';
import { ToplistCreateComponent } from './toplist-create/toplist-create.component';

@NgModule({
  declarations: [
    AppComponent,
    ToplistSelectorComponent,
    ToplistEditorComponent,
    ToplistCreateComponent
  ],
  imports: [
    BrowserModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }