import { OrderCreateComponent } from './components/order/order-create/order-create.component';
import { OrderComponent } from './views/order/order.component';
import { HomeComponent } from './views/home/home.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SessionComponent } from './views/session/session.component';


const routes: Routes = [
  {
    path: "",
    component: HomeComponent
  },
  {
    path: "orders",
    component: OrderComponent
  },
  {
    path: "orders/create",
    component: OrderCreateComponent
  },
  {
    path: "session",
    component: SessionComponent
  } 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
