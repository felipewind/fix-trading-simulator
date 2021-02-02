import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { ModelModule } from "./model/model.module";
import { CoreModule } from "./core/core.module";
import { routing } from "./app.routing";

@NgModule({
  declarations: [AppComponent],
  imports: [BrowserModule, ModelModule, CoreModule, routing],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
