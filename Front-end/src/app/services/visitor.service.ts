import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Visitor } from '../models/visitor.model';

@Injectable({
  providedIn: 'root',
})
export class VisitorService {
  apiUrl: string = 'http://localhost:8080/visitors';

  constructor(private http: HttpClient) {}

  getToken() {
    const token = sessionStorage.getItem('token');
    return new HttpHeaders().set('Authorization', `Bearer ${token}`);
  }

  createVisitors(name: string, document: string): Observable<Visitor> {
    const headers = this.getToken();
    return this.http.post<Visitor>(
      `${this.apiUrl}`,
      { name, document },
      { headers }
    );
  }

  getVisitors(userId: number): Observable<Visitor[]> {
    const headers = this.getToken();
    return this.http.get<Visitor[]>(`${this.apiUrl}/all/${userId}`, {
      headers,
    });
  }

  deleteVisitor(id: number) {
    const headers = this.getToken();
    return this.http.delete(`${this.apiUrl}/${id}`, { headers });
  }
}
