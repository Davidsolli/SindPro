import { Component } from '@angular/core';
import { HomeLayoutComponent } from '../../../components/home-layout/home-layout.component';
import { Router } from '@angular/router';
import { Reservation } from '../../../models/reservation.model';
import { CommonSpacesService } from '../../../services/common-spaces.service';
import { DatePipe, NgFor, NgIf } from '@angular/common';
import { UserService } from '../../../services/user.service';
import { User } from '../../../models/user.model';

@Component({
  selector: 'app-rent-space-table',
  standalone: true,
  imports: [HomeLayoutComponent, NgFor, NgIf, DatePipe],
  templateUrl: './rent-space-table.component.html',
  styleUrl: './rent-space-table.component.scss',
})
export class RentSpaceTableComponent {
  reservations!: Reservation[];
  user!: User;

  constructor(
    private router: Router,
    private commonSpaceService: CommonSpacesService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.commonSpaceService.getAllReservation().subscribe(
      (reservationData: Reservation[]) => {
        this.reservations = reservationData;
      },
      (error) => console.log('Erro ao buscar reservas!', error)
    );
    this.userService.getUser().subscribe(
      (userData: User) => {
        this.user = userData;
      },
      (error) => {
        console.error('Erro ao buscar usuário:', error);
      }
    );
  }

  deleteReservation(id: number) {
    this.commonSpaceService.deleteReservation(id).subscribe(
      () => {
        this.reservations = this.reservations.filter(
          (reservation) => reservation.id !== id
        );
      },
      (error) => {
        console.log('Erro ao excluir reserva', error);
      }
    );
  }

  navigate(route: String) {
    this.router.navigate([route]);
  }
}
