import { Component } from '@angular/core';
import { HomeLayoutComponent } from '../../../components/home-layout/home-layout.component';
import { User } from '../../../models/user.model';
import { UserService } from '../../../services/user.service';
import { NgFor } from '@angular/common';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { NotificationService } from '../../../services/notification.service';
import { Router } from '@angular/router';

interface notificationForm {
  title: FormControl;
  message: FormControl;
  receiverId: FormControl;
}

@Component({
  selector: 'app-create-warning',
  standalone: true,
  imports: [HomeLayoutComponent, NgFor, ReactiveFormsModule],
  templateUrl: './create-warning.component.html',
  styleUrl: './create-warning.component.scss',
})
export class CreateWarningComponent {
  users!: User[];
  notificationForm!: FormGroup<notificationForm>;

  constructor(
    private userService: UserService,
    private toastr: ToastrService,
    private notificationService: NotificationService,
    private router: Router
  ) {
    this.notificationForm = new FormGroup({
      title: new FormControl('', [Validators.required]),
      message: new FormControl('', [Validators.required]),
      receiverId: new FormControl('', [Validators.required]),
    });
  }

  ngOnInit(): void {
    this.userService.getUsersList().subscribe(
      (userData: User[]) => {
        this.users = userData;
      },
      (error) => {
        console.error('Erro ao buscar usuário:', error);
      }
    );
  }

  submit() {
    if (this.notificationForm.invalid) {
      this.toastr.error('Preencha todos os campos corretamente.');
      return;
    }

    const receiverId = parseInt(
      this.notificationForm.value.receiverId.match(/\d+/)[0],
      10
    );

    this.notificationService
      .createNotification(
        this.notificationForm.value.title,
        this.notificationForm.value.message,
        receiverId
      )
      .subscribe({
        next: () => {
          this.toastr.success('Notificação enviada!');
          this.router.navigate(['warnings']);
        },
        error: () => this.toastr.error('Erro ao enviar notificação!'),
      });
  }
}
