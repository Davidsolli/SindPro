import { Component } from '@angular/core';
import { HomeLayoutComponent } from '../../components/home-layout/home-layout.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-warnings',
  standalone: true,
  imports: [HomeLayoutComponent],
  templateUrl: './warnings.component.html',
  styleUrl: './warnings.component.scss',
})
export class WarningsComponent {}
