import { Component } from '@angular/core';
import { HomeLayoutComponent } from '../../../components/home-layout/home-layout.component';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { NotificationService } from '../../../services/notification.service';
import { Router } from '@angular/router';

interface NotificationForm {
  title: FormControl;
  message: FormControl;
}

@Component({
  selector: 'app-create-notification',
  standalone: true,
  imports: [HomeLayoutComponent, ReactiveFormsModule],
  templateUrl: './create-notification.component.html',
  styleUrl: './create-notification.component.scss',
})
export class CreateNotificationComponent {
  notificationForm!: FormGroup<NotificationForm>;

  constructor(
    private toastr: ToastrService,
    private notificationService: NotificationService,
    private router: Router
  ) {
    this.notificationForm = new FormGroup({
      title: new FormControl('', [Validators.required]),
      message: new FormControl('', [Validators.required]),
    });
  }

  submit() {
    if (this.notificationForm.invalid) {
      this.toastr.error('Preencha todos os campos corretamente.');
      return;
    }

    this.notificationService
      .createNotification(
        this.notificationForm.value.title,
        this.notificationForm.value.message
      )
      .subscribe({
        next: () => {
          this.toastr.success('Aviso criada!');
          this.router.navigate(['warnings']);
        },
        error: () => {this.toastr.error('Error ao criar um aviso, tente novamente!')}
      });
  }

  navigate(route: String) {
    this.router.navigate([route]);
  }
}
