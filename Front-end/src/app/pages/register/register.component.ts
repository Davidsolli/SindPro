import { Component } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import {
  FormControl,
  FormGroup,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';
import { RegisterService } from '../../services/register.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { RegisterData } from '../../models/register-data.model';
import { NgIf } from '@angular/common';

interface RegisterForm {
  name: FormControl<string | null>;
  email: FormControl<string | null>;
  phoneNumber: FormControl<string | null>;
  cpf: FormControl<string | null>;
  address: FormControl<string | null>;
  apartment: FormControl<number | null>;
  password: FormControl<string | null>;
  passwordConfirm: FormControl<string | null>;
}

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [HeaderComponent, ReactiveFormsModule, NgIf],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent {
  formRegister: FormGroup<RegisterForm>;

  constructor(
    private router: Router,
    private registerService: RegisterService,
    private toastr: ToastrService
  ) {
    this.formRegister = new FormGroup<RegisterForm>({
      name: new FormControl('', [Validators.required, Validators.minLength(3)]),
      email: new FormControl('', [Validators.required, Validators.email]),
      phoneNumber: new FormControl('', [
        Validators.required,
        Validators.minLength(11),
      ]),
      cpf: new FormControl('', [Validators.required, Validators.minLength(11)]),
      address: new FormControl('', [Validators.required]),
      apartment: new FormControl<number | null>(null, [Validators.required]),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(6),
      ]),
      passwordConfirm: new FormControl('', [
        Validators.required,
        Validators.minLength(6),
      ]),
    });
  }

  submit() {
    if (this.formRegister.invalid) {
      this.toastr.error('Preencha todos os campos corretamente.');
      return;
    }

    if (
      this.formRegister.value.password !==
      this.formRegister.value.passwordConfirm
    ) {
      this.toastr.error('As senhas não coincidem.');
      return;
    }

    const RegisterData: RegisterData = {
      name: this.formRegister.value.name!,
      email: this.formRegister.value.email!,
      phoneNumber: this.formRegister.value.phoneNumber!,
      cpf: this.formRegister.value.cpf!,
      address: this.formRegister.value.address!,
      apartment: this.formRegister.value.apartment!,
      password: this.formRegister.value.password!,
      // passwordConfirm não deve ser enviado para o backend
    };

    this.registerService.register(RegisterData).subscribe({
      next: () => {
        this.toastr.success('Cadastro realizado com sucesso!');
        this.router.navigate(['']);
      },
      error: () => {
        this.toastr.error('Erro ao realizar o cadastro.');
      },
    });
  }

  navigate() {
    this.router.navigate(['']);
  }
}
