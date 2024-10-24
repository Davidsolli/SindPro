import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginResponse } from '../models/login-response.model';
import { catchError, tap } from 'rxjs/operators';
import { of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private apiUrl: string = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  login(email: string, password: string) {
    return this.http
      .post<LoginResponse>(`${this.apiUrl}/auth/login`, { email, password })
      .pipe(
        tap((value) => {
          sessionStorage.setItem('token', value.token);
        })
      );
  }
}
