import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { NavBarComponent } from './components/ui/nav-bar/nav-bar.component';
import { PqrsComponent } from './components/dashboard/pqrs/pqrs.component';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CreatepqrsComponent } from './components/dashboard/createpqrs/createpqrs.component';
import { SearchpqrsComponent } from './components/dashboard/searchpqrs/searchpqrs.component';

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    PqrsComponent,
    CreatepqrsComponent,
    SearchpqrsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    CommonModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
