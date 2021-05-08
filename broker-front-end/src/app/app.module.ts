import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSelectModule } from '@angular/material/select';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { IMqttServiceOptions, MqttModule } from "ngx-mqtt";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LogEventComponent } from './components/log/log-event/log-event.component';
import { LogMessageComponent } from './components/log/log-message/log-message.component';
import { OrderCancelComponent } from './components/order/order-cancel/order-cancel.component';
import { OrderCreateComponent } from './components/order/order-create/order-create.component';
import { OrderReadComponent } from './components/order/order-read/order-read.component';
import { SessionControlComponent } from './components/session/session-control/session-control.component';
import { SessionHeaderComponent } from './components/session/session-header/session-header.component';
import { SessionMessagesComponent } from './components/session/session-messages/session-messages.component';
import { SessionPropertiesComponent } from './components/session/session-properties/session-properties.component';
import { FooterComponent } from './components/template/footer/footer.component';
import { HeaderComponent } from './components/template/header/header.component';
import { NavComponent } from './components/template/nav/nav.component';
import { HomeComponent } from './views/home/home.component';
import { LogComponent } from './views/log/log.component';
import { OrderComponent } from './views/order/order.component';
import { SessionComponent } from './views/session/session.component';

export const MQTT_SERVICE_OPTIONS: IMqttServiceOptions = {
  hostname: 'localhost',
  port: 9001,
  path: ''
}

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    NavComponent,
    HomeComponent,
    OrderComponent,
    OrderCreateComponent,
    OrderReadComponent, 
    SessionComponent, 
    SessionControlComponent, 
    LogEventComponent, 
    LogComponent, 
    SessionMessagesComponent, 
    SessionPropertiesComponent, 
    LogMessageComponent, 
    OrderCancelComponent, 
    SessionHeaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatSidenavModule,
    MatListModule,
    MatCardModule,
    MatButtonModule,
    MatSnackBarModule,
    HttpClientModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatSelectModule,
    MqttModule.forRoot(MQTT_SERVICE_OPTIONS),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
