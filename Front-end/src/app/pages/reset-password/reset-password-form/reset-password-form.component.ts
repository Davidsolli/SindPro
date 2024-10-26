import { Component } from '@angular/core';
import { HeaderComponent } from "../../../components/header/header.component";
import { CardMessageLayoutComponent } from "../../../components/card-message-layout/card-message-layout.component";

@Component({
  selector: 'app-reset-password-form',
  standalone: true,
  imports: [HeaderComponent, CardMessageLayoutComponent],
  templateUrl: './reset-password-form.component.html',
  styleUrl: './reset-password-form.component.scss'
})
export class ResetPasswordFormComponent {

}
