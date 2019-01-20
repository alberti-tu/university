import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { app_routing } from './app.routes';
import { AppComponent } from './app.component';
import { NavComponent } from './nav/nav.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';


@NgModule({
  declarations: [ AppComponent, NavComponent, HomeComponent, ProfileComponent ],
  imports: [ BrowserModule, HttpClientModule, CommonModule, FormsModule, app_routing ],
  providers: [ ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
