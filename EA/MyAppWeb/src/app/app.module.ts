import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { NavComponent } from './nav/nav.component';
import { AgmCoreModule } from '@agm/core';

@NgModule({
  declarations: [ AppComponent, NavComponent ],
  imports: [ BrowserModule, HttpClientModule, CommonModule, FormsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyD4btF6um1qmUt7IZDVsU8WlWI6-PMYZk0'
    }) ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule { }
