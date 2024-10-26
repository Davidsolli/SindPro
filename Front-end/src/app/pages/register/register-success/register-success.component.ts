import { Component } from '@angular/core';
import { HeaderComponent } from "../../../components/header/header.component";
import { CardMessageLayoutComponent } from "../../../components/card-message-layout/card-message-layout.component";
import { Router } from '@angular/router';

@Component({
  selector: 'app-register-success',
  standalone: true,
  imports: [HeaderComponent, CardMessageLayoutComponent],
  templateUrl: './register-success.component.html',
  styleUrl: './register-success.component.scss'
})
export class RegisterSuccessComponent {

  constructor(private router: Router) {}

  navigate() {
    this.router.navigate(['']);
  }
}
