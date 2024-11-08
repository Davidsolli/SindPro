import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Page } from '../models/page.model';
import { Notification } from '../models/notification.model';
import { Warning } from '../models/warning.model';

@Injectable({
  providedIn: 'root',
})
export class NotificationService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  getToken() {
    const token = sessionStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return headers;
  }

  getNotifications(page: number, size: number): Observable<Page<Notification>> {
    const headers = this.getToken();
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    return this.http.get<Page<Notification>>(
      `${this.apiUrl}/notifications/all`,
      {
        params,
        headers,
      }
    );
  }

  createNotification(title: string, message: string): Observable<Warning> {
    const headers = this.getToken();
    return this.http.post<Warning>(
      `${this.apiUrl}/warnings`,
      {
        title,
        message,
      },
      { headers }
    );
  }

  getWarnings(): Observable<Warning[]> {
    const headers = this.getToken();
    return this.http.get<Warning[]>(`${this.apiUrl}/warnings/all`, { headers });
  }

  deleteNotification(id: number) {
    const headers = this.getToken();
    return this.http.delete(`${this.apiUrl}/notifications/${id}`, { headers });
  }

  deleteWarning(id: number): Observable<void> {
    const headers = this.getToken();
    return this.http.delete<void>(`${this.apiUrl}/warnings/${id}`, { headers });
  }
}
