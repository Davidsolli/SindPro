import { Component } from '@angular/core';
import { HeaderComponent } from '../../../components/header/header.component';
import { CardMessageLayoutComponent } from '../../../components/card-message-layout/card-message-layout.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-reset-password-success',
  standalone: true,
  imports: [HeaderComponent, CardMessageLayoutComponent],
  templateUrl: './reset-password-success.component.html',
  styleUrl: './reset-password-success.component.scss',
})
export class ResetPasswordSuccessComponent {
  constructor(private router: Router) {}

  navigate() {
    this.router.navigate(['']);
  }
}
