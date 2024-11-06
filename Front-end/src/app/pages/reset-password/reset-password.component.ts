import { Component } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { CardMessageLayoutComponent } from '../../components/card-message-layout/card-message-layout.component';
import { Router } from '@angular/router';
import { ResetPasswordService } from '../../services/reset-password.service';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

interface RequestForm {
  email: FormControl;
}

@Component({
  selector: 'app-reset-password',
  standalone: true,
  imports: [HeaderComponent, CardMessageLayoutComponent, ReactiveFormsModule],
  templateUrl: './reset-password.component.html',
  styleUrl: './reset-password.component.scss',
})
export class ResetPasswordComponent {
  requestForm!: FormGroup<RequestForm>;

  constructor(
    private router: Router,
    private resetPasswordService: ResetPasswordService,
    private toastr: ToastrService
  ) {
    this.requestForm = new FormGroup({
      email: new FormControl('', [Validators.required]),
    });
  }

  submit() {
    if (this.requestForm.invalid) {
      this.toastr.error('Por favor, preencha o campo com seu email.');
      return;
    }
    this.resetPasswordService
      .requestReset(this.requestForm.value.email)
      .subscribe({
        next: () => {
          this.toastr.success('Email enviado com sucesso!');
          this.router.navigate(['reset-password-success']);
        },
        error: () => this.toastr.error('Erro inesperado, tente novamente.'),
      });
  }

  navigate(route: string) {
    this.router.navigate([route]);
  }
}
