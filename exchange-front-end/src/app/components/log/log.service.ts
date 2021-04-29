import { LogEvent } from './log-event.model';
import { LogMessage } from './log-message.model';
import { catchError, map } from 'rxjs/operators';

import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HttpClient } from '@angular/common/http';
import { EMPTY, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LogService {

  baseUrl = 'http://localhost:8090/logs';

  constructor(private snackBar: MatSnackBar,
    private http: HttpClient) { }

  showMessage(msg: string, isError: boolean = false): void {
    this.snackBar.open(msg, 'X', {
      duration: 3000,
      horizontalPosition: "right",
      verticalPosition: "top",
      panelClass: isError ? ['msg-error'] : ['msg-success']
    });
  }

  readEvent(): Observable<LogEvent[]> {

    const url = `${this.baseUrl}/event`;

    return this.http.get<LogEvent[]>(url).pipe(
      map((obj) => obj),
      catchError((error) => this.errorHandler(error))
    )
  }

  readMessages(mode: string): Observable<LogMessage[]> {

    const url = `${this.baseUrl}/messages-${mode}`;    

    return this.http.get<LogMessage[]>(url).pipe(
      map((obj) => obj),
      catchError((error) => this.errorHandler(error))
    )
  }

  errorHandler(e: any): Observable<any> {
    this.showMessage('Something wrong happened!', true);
    return EMPTY;
  }

}
