import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CommonSpaces } from '../models/common-spaces.model';
import { Reservation } from '../models/reservation.model';

@Injectable({
  providedIn: 'root',
})
export class CommonSpacesService {
  apiUrl: string = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  getToken() {
    const token = sessionStorage.getItem('token');
    return new HttpHeaders().set('Authorization', `Bearer ${token}`);
  }

  getAllSpaces(): Observable<CommonSpaces[]> {
    const headers = this.getToken();
    return this.http.get<CommonSpaces[]>(`${this.apiUrl}/common-spaces`, {
      headers,
    });
  }

  createReservation(
    eventDate: string,
    spaceId: number
  ): Observable<Reservation> {
    const headers = this.getToken();
    return this.http.post<Reservation>(
      `${this.apiUrl}/reservation`,
      {
        eventDate,
        spaceId,
      },
      { headers }
    );
  }

  getAllReservation(): Observable<Reservation[]> {
    const headers = this.getToken();
    return this.http.get<Reservation[]>(`${this.apiUrl}/reservation`, {
      headers,
    });
  }

  deleteReservation(id: Number): Observable<void> {
    const headers = this.getToken();
    return this.http.delete<void>(`${this.apiUrl}/reservation/${id}`, {
      headers,
    });
  }
}
