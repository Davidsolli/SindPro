import { Component, EventEmitter, Input } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-home-layout',
  standalone: true,
  imports: [],
  templateUrl: './home-layout.component.html',
  styleUrl: './home-layout.component.scss',
})
export class HomeLayoutComponent {
  @Input() title: string = '';
  user!: User;

  constructor(private router: Router) {}

  navigate(route: string) {
    this.router.navigate([route]);
  }
}
