import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CommonSpaces } from '../models/common-spaces.model';

@Injectable({
  providedIn: 'root',
})
export class CommonSpacesService {
  apiUrl: string = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  getAllSpaces(): Observable<CommonSpaces[]> {
    const token = sessionStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<CommonSpaces[]>(`${this.apiUrl}/common-spaces`, {
      headers,
    });
  }
}
