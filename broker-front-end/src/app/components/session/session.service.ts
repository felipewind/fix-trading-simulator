import { Message } from './message.model';
import { Session } from './session.model';
import { catchError, map } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HttpClient } from '@angular/common/http';
import { EMPTY, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  baseUrl = 'http://localhost:8080/session';

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

  startInitiator(): Observable<Session> {
    const url = `${this.baseUrl}/start-initiator`
    return this.http.post<Session>(url, null).pipe(
      map((obj) => obj),
      catchError((error) => this.errorHandler(error))
    );
  }

  stopInitiator(): Observable<Session> {
    const url = `${this.baseUrl}/stop-initiator`
    return this.http.post<Session>(url, null).pipe(
      map((obj) => obj),
      catchError((error) => this.errorHandler(error))
    );
  }

  logout(): Observable<Session> {
    const url = `${this.baseUrl}/logout`
    return this.http.post<Session>(url, null).pipe(
      map((obj) => obj),
      catchError((error) => this.errorHandler(error))
    );
  }

  read(): Observable<Session> {
    return this.http.get<Session>(this.baseUrl).pipe(
      map((obj) => obj),
      catchError((error) => this.errorHandler(error))
    );
  }

  readMessages(): Observable<Message[]> {

    const url = `${this.baseUrl}/messages`;

    return this.http.get<Message[]>(url).pipe(
      map((obj) => obj),
      catchError((error) => this.errorHandler(error))
    )
  }

  errorHandler(e: any): Observable<any> {
    this.showMessage('Something wrong happened!', true);
    return EMPTY;
  }

}
