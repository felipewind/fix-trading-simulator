import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { ModelModule } from "../model/model.module";
import { TableComponent } from "./table.component";

@NgModule({
    imports: [BrowserModule, ModelModule],
    declarations: [TableComponent],
    exports: [ModelModule, TableComponent]
})
export class CoreModule { }