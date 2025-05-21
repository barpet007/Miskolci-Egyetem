import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms'; // <-- EZT ADD HOZZÁ

import { AppComponent } from './app.component';
// ...további importok...

@NgModule({
  declarations: [
    AppComponent,
    // ...további komponensek...
  ],
  imports: [
    BrowserModule,
    FormsModule, // <-- EZT IS ADD HOZZÁ
    // ...további modulok...
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }