import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private apiUrl: string = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  getToken() {
    const token = sessionStorage.getItem('token');
    return new HttpHeaders().set('Authorization', `Bearer ${token}`);
  }

  getUser(): Observable<User> {
    const headers = this.getToken();
    return this.http.get<User>(`${this.apiUrl}/users/me`, { headers });
  }

  getUsersList(): Observable<User[]> {
    const headers = this.getToken();
    return this.http.get<User[]>(`${this.apiUrl}/users`, { headers });
  }
}
