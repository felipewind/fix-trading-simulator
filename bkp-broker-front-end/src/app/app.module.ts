import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ModelModule } from "./model/model.module";
import { CoreModule } from "./core/core.module";
import { FixModule } from "./fix/fix.module";
import { routing } from "./app.routing";
import { TableComponent } from './core/table.component';
import { FormComponent } from './core/form.component';

@NgModule({
  imports: [BrowserModule, ModelModule, CoreModule, FixModule, routing],
  providers: [],
  bootstrap: [TableComponent, FormComponent]
})
export class AppModule { }
