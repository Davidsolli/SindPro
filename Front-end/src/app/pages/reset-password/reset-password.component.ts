import { Component } from '@angular/core';
import { HeaderComponent } from "../../components/header/header.component";
import { CardMessageLayoutComponent } from "../../components/card-message-layout/card-message-layout.component";
import { Router } from '@angular/router';

@Component({
  selector: 'app-reset-password',
  standalone: true,
  imports: [HeaderComponent, CardMessageLayoutComponent],
  templateUrl: './reset-password.component.html',
  styleUrl: './reset-password.component.scss'
})
export class ResetPasswordComponent {

  constructor(private router: Router) {}

  navigate(route: string) {
    this.router.navigate([route]);
  }
}
