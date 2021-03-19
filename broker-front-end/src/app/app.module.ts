import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { NewOrderSingleComponent } from "./components/new-order-single/new-order-single.component";

@NgModule({
  declarations: [
    AppComponent, NewOrderSingleComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],  
  bootstrap: [AppComponent]
})
export class AppModule { }
