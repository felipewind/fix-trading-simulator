import { LogMessageComponent } from './components/log/log-message/log-message.component';
import { SessionControlComponent } from './components/session/session-control/session-control.component';
import { MessagesComponent } from './components/session/messages/messages.component';
import { LogEventComponent } from './components/log/log-event/log-event.component';
import { LogComponent } from './views/log/log.component';
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
  },  
  {
    path: "session/control",
    component: SessionControlComponent
  },  
  {
    path: "session/messages",
    component: MessagesComponent
  },
  {
    path: "logs",
    component: LogComponent
  },
  {
    path: "logs/event",
    component: LogEventComponent
  },
  {
    path: "logs/messages",
    component: LogMessageComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
