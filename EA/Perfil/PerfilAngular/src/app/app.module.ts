import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { BalanceComponent } from './components/balance/balance.component';
import { TasksComponent } from './components/tasks/tasks.component';


@NgModule({
  declarations: [ AppComponent, BalanceComponent, TasksComponent ],
  imports: [ BrowserModule, HttpClientModule, CommonModule, FormsModule ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
