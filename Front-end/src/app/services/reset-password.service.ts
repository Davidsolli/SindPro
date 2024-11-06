import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ResetPasswordService {
  apiUrl: string = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  requestReset(email: string): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/password/request-reset`, {
      email,
    });
  }

  resetPassword(newPassword: string, token: string): Observable<void> {
    return this.http.post<void>(
      `${this.apiUrl}/password/reset-password?token=${token}`,
      { newPassword }
    );
  }
}
