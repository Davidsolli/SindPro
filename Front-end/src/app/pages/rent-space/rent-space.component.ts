import { Component } from '@angular/core';
import { HomeLayoutComponent } from '../../components/home-layout/home-layout.component';
import { Router } from '@angular/router';
import { CommonSpaces } from '../../models/common-spaces.model';
import { CommonSpacesService } from '../../services/common-spaces.service';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-rent-space',
  standalone: true,
  imports: [HomeLayoutComponent, NgFor],
  templateUrl: './rent-space.component.html',
  styleUrl: './rent-space.component.scss',
})
export class RentSpaceComponent {
  spaces!: CommonSpaces[];

  constructor(
    private router: Router,
    private commonSpacesService: CommonSpacesService
  ) {}

  ngOnInit(): void {
    this.commonSpacesService.getAllSpaces().subscribe(
      (spaceData: CommonSpaces[]) => {
        this.spaces = spaceData;
      },
      (error) => console.error('Erro ao buscar espaços', error)
    );
  }

  navigate(route: String) {
    this.router.navigate([route]);
  }
}
