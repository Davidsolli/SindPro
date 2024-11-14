import { CommonModule, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HomeLayoutComponent } from '../../../components/home-layout/home-layout.component';
import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { User } from '../../../models/user.model';
import { UserService } from '../../../services/user.service';
import { ToastrService } from 'ngx-toastr';
import { CommonSpacesService } from '../../../services/common-spaces.service';

interface RentSpaceForm {
  eventDate: FormControl;
}

@Component({
  selector: 'app-rent-space-form',
  standalone: true,
  imports: [
    CommonModule,
    HomeLayoutComponent,
    FormsModule,
    NgIf,
    ReactiveFormsModule,
  ],
  templateUrl: './rent-space-form.component.html',
  styleUrl: './rent-space-form.component.scss',
})
export class RentSpaceFormComponent {
  spaceId: string | null = null;
  eventDate: string = '';
  user!: User;
  rentSpaceForm!: FormGroup<RentSpaceForm>;

  isDateValid(): boolean {
    const selectedDate = new Date(this.eventDate);
    const currentDate = new Date();
    return selectedDate >= currentDate;
  }

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService,
    private toastr: ToastrService,
    private commonSpaceService: CommonSpacesService
  ) {
    this.rentSpaceForm = new FormGroup<RentSpaceForm>({
      eventDate: new FormControl('', [Validators.required]),
    });
  }

  ngOnInit(): void {
    this.spaceId = this.route.snapshot.paramMap.get('spaceId');
    this.userService.getUser().subscribe(
      (userData: User) => {
        this.user = userData;
      },
      (error) => {
        console.error('Erro ao buscar usuário:', error);
      }
    );
  }

  submit() {
    if (this.rentSpaceForm.invalid) {
      this.toastr.error('Preencha todos os campos corretamente.');
      return;
    }

    this.commonSpaceService
      .createReservation(
        this.rentSpaceForm.value.eventDate,
        Number(this.spaceId)
      )
      .subscribe({
        next: () => {
          this.toastr.success('Espaço reservado!');
          this.router.navigate(['rent-space-table']);
        },
        error: () => this.toastr.error('Erro inesperado, tente novamente.'),
      });
  }
}
