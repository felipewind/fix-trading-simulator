import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { FormsModule } from "@angular/forms";
import { ModelModule } from "../model/model.module";
import { FixModule } from "../fix/fix.module";
import { TableComponent } from "./table.component";
import { FormComponent } from "./form.component";

@NgModule({
    imports: [BrowserModule, FormsModule, ModelModule, FixModule],
    declarations: [TableComponent, FormComponent],
    exports: [ModelModule, TableComponent, FormComponent]
})
export class CoreModule { }