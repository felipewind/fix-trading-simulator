import { OrderCancelComponent } from './components/order/order-cancel/order-cancel.component';
import { SessionPropertiesComponent } from './components/session/session-properties/session-properties.component';
import { LogMessageComponent } from './components/log/log-message/log-message.component';
import { SessionControlComponent } from './components/session/session-control/session-control.component';
import { SessionMessagesComponent } from './components/session/session-messages/session-messages.component';
import { LogEventComponent } from './components/log/log-event/log-event.component';
import { LogComponent } from './views/log/log.component';
import { OrderComponent } from './views/order/order.component';
import { HomeComponent } from './views/home/home.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SessionComponent } from './views/session/session.component';


const routes: Routes = [
  {
    path: "orders/cancel/:clOrdID",
    component: OrderCancelComponent
  },
  {
    path: "orders",
    component: OrderComponent
  },
  {
    path: "session/control",
    component: SessionControlComponent
  },
  {
    path: "session/messages",
    component: SessionMessagesComponent
  },
  {
    path: "session/properties",
    component: SessionPropertiesComponent
  },
  {
    path: "session",
    component: SessionComponent
  },
  {
    path: "logs/event",
    component: LogEventComponent
  },
  {
    path: "logs/messages/:mode",
    component: LogMessageComponent
  },
  {
    path: "logs",
    component: LogComponent
  },
  {
    path: "",
    component: HomeComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
