import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { AppComponent } from './app.component';
import { ToplistSelectorComponent } from './toplist-selector/toplist-selector.component';
import { ToplistEditorComponent } from './toplist-editor/toplist-editor.component';
import { ToplistCreateComponent } from './toplist-create/toplist-create.component';

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes)
  ],

};