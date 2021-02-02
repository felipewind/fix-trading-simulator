import { Routes, RouterModule } from "@angular/router";
import { TableComponent } from "./core/table.component";
const routes: Routes = [
	{ path: "", component: TableComponent }]
export const routing = RouterModule.forRoot(routes);