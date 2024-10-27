import { Component, EventEmitter, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home-layout',
  standalone: true,
  imports: [],
  templateUrl: './home-layout.component.html',
  styleUrl: './home-layout.component.scss',
})
export class HomeLayoutComponent {
  @Input() title: string = '';

  constructor(private router: Router) {}

  navigate(route: string) {
    this.router.navigate([route]);
  }
}
