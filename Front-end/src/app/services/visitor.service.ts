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

  getVisitors(userId: number): Observable<Visitor[]> {
    const token = sessionStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<Visitor[]>(`${this.apiUrl}/all/${userId}`, {
      headers,
    });
  }

  deleteVisitor(id: number) {
    const token = sessionStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.delete(`${this.apiUrl}/${id}`, { headers });
  }
}
