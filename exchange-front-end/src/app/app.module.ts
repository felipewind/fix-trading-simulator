import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';

import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatCheckboxModule } from '@angular/material/checkbox';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/template/header/header.component';
import { FooterComponent } from './components/template/footer/footer.component';
import { NavComponent } from './components/template/nav/nav.component';
import { HomeComponent } from './views/home/home.component';
import { OrderComponent } from './views/order/order.component';
import { OrderReadComponent } from './components/order/order-read/order-read.component';
import { SessionComponent } from './views/session/session.component';
import { SessionControlComponent } from './components/session/session-control/session-control.component';
import { LogEventComponent } from './components/log/log-event/log-event.component';
import { LogComponent } from './views/log/log.component';
import { SessionMessagesComponent } from './components/session/session-messages/session-messages.component';
import { SessionPropertiesComponent } from './components/session/session-properties/session-properties.component';
import { LogMessageComponent } from './components/log/log-message/log-message.component';
import { OrderCancelComponent } from './components/order/order-cancel/order-cancel.component';
import { SessionHeaderComponent } from './components/session/session-header/session-header.component';
import { OrderEditComponent } from './components/order/order-edit/order-edit.component';
import { NegotiationEditComponent } from './components/negotiation/negotiation-edit/negotiation-edit.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    NavComponent,
    HomeComponent,
    OrderComponent,
    OrderReadComponent,
    SessionComponent,
    SessionControlComponent,
    LogEventComponent,
    LogComponent,
    SessionMessagesComponent,
    SessionPropertiesComponent,
    LogMessageComponent,
    OrderCancelComponent,
    SessionHeaderComponent,
    OrderEditComponent,
    NegotiationEditComponent
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
    MatCheckboxModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
