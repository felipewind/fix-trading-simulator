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

  patch(session: Session): Observable<Session> {    
    return this.http.patch(this.baseUrl, session).pipe(
      map((obj) => obj),
      catchError((error) => this.errorHandler(error))
    );
  }

  errorHandler(e: any): Observable<any> {
    this.showMessage('Something wrong happened!', true);
    return EMPTY;
  }

}
