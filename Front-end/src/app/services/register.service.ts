import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RegisterData } from '../models/register-data.model';

@Injectable({
  providedIn: 'root',
})
export class RegisterService {
  private apiUrl: string = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  register(data: RegisterData): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/auth/register`, data);
  }
}
