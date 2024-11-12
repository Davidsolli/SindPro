import { Component } from '@angular/core';
import { HomeLayoutComponent } from "../../../components/home-layout/home-layout.component";
import { Router } from '@angular/router';

@Component({
  selector: 'app-rent-space-table',
  standalone: true,
  imports: [HomeLayoutComponent],
  templateUrl: './rent-space-table.component.html',
  styleUrl: './rent-space-table.component.scss'
})
export class RentSpaceTableComponent {
  
  constructor(private router: Router) {}

  navigate(route: String) {
    this.router.navigate([route]);
  }
}
