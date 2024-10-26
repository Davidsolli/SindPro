import { Component, Input } from '@angular/core';
import { HeaderComponent } from "../header/header.component";

@Component({
  selector: 'app-card-message-layout',
  standalone: true,
  imports: [HeaderComponent],
  templateUrl: './card-message-layout.component.html',
  styleUrl: './card-message-layout.component.scss'
})
export class CardMessageLayoutComponent {
  @Input() cardTitle: string = "";
}
