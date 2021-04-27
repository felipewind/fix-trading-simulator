import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './components/template/header/header.component';

import { MatToolbarModule } from '@angular/material/toolbar';
import { FooterComponent } from './components/template/footer/footer.component';
import { NavComponent } from './components/template/nav/nav.component';

import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { HomeComponent } from './views/home/home.component';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { OrderComponent } from './views/order/order.component';
import { OrderCreateComponent } from './components/order/order-create/order-create.component';

import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { OrderReadComponent } from './components/order/order-read/order-read.component';
import { SessionComponent } from './views/session/session.component';
import { SessionControlComponent } from './components/session/session-control/session-control.component';
import { LogEventComponent } from './components/log/log-event/log-event.component';
import { LogComponent } from './views/log/log.component';
import { MessagesComponent } from './components/session/messages/messages.component';
import { SessionPropertiesComponent } from './components/session/session-properties/session-properties.component';
import { LogMessageComponent } from './components/log/log-message/log-message.component';
import { OrderCancelComponent } from './components/order/order-cancel/order-cancel.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    NavComponent,
    HomeComponent,
    OrderComponent,
    OrderCreateComponent,
    OrderReadComponent, SessionComponent, SessionControlComponent, LogEventComponent, LogComponent, MessagesComponent, SessionPropertiesComponent, LogMessageComponent, OrderCancelComponent
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
    MatSortModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
