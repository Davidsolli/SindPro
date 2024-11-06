import { Component } from '@angular/core';
import { HeaderComponent } from '../../../components/header/header.component';
import { CardMessageLayoutComponent } from '../../../components/card-message-layout/card-message-layout.component';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ResetPasswordService } from '../../../services/reset-password.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

interface ResetForm {
  newPassword: FormControl;
  newPasswordConfirm: FormControl;
}

@Component({
  selector: 'app-reset-password-form',
  standalone: true,
  imports: [HeaderComponent, CardMessageLayoutComponent, ReactiveFormsModule],
  templateUrl: './reset-password-form.component.html',
  styleUrl: './reset-password-form.component.scss',
})
export class ResetPasswordFormComponent {
  resetForm!: FormGroup<ResetForm>;
  token!: string;

  constructor(
    private route: ActivatedRoute,
    private resetPasswordService: ResetPasswordService,
    private router: Router,
    private toastr: ToastrService
  ) {
    this.resetForm = new FormGroup<ResetForm>({
      newPassword: new FormControl('', [Validators.required]),
      newPasswordConfirm: new FormControl('', [Validators.required]),
    });
  }

  ngOnInit() {
    this.token = this.route.snapshot.queryParamMap.get('token') || '';
  }

  submit() {
    if (this.resetForm.invalid) {
      this.toastr.error('Preencha todos os campos corretamente.');
      return;
    }

    if (
      this.resetForm.value.newPassword !==
      this.resetForm.value.newPasswordConfirm
    ) {
      this.toastr.error('As senhas não coincidem.');
      return;
    }

    this.resetPasswordService
      .resetPassword(this.resetForm.value.newPassword, this.token)
      .subscribe({
        next: () => {
          this.toastr.success('Senha redefinida com sucesso!');
          this.router.navigate(['']);
          console.log(this.token);
        },
        error: () => this.toastr.error('Erro inesperado, tente novamente.'),
      });
  }
}
