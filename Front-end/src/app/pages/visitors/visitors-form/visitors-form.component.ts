import { Component } from '@angular/core';
import { HomeLayoutComponent } from '../../../components/home-layout/home-layout.component';
import { Router } from '@angular/router';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { VisitorService } from '../../../services/visitor.service';
import { ToastrService } from 'ngx-toastr';

interface VisitorsForm {
  name: FormControl;
  document: FormControl;
}

@Component({
  selector: 'app-visitors-form',
  standalone: true,
  imports: [HomeLayoutComponent, ReactiveFormsModule],
  templateUrl: './visitors-form.component.html',
  styleUrl: './visitors-form.component.scss',
})
export class VisitorsFormComponent {
  visitorsForm!: FormGroup<VisitorsForm>;

  constructor(
    private router: Router,
    private visitorService: VisitorService,
    private toastr: ToastrService
  ) {
    this.visitorsForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      document: new FormControl('', [Validators.required]),
    });
  }

  submit() {
    if (this.visitorsForm.invalid) {
      this.toastr.error('Preencha todos os campos corretamente.');
      return;
    }

    this.visitorService
      .createVisitors(
        this.visitorsForm.value.name,
        this.visitorsForm.value.document
      )
      .subscribe({
        next: () => {
          this.toastr.success('Visitante registrado!');
          this.router.navigate(['visitors']);
        },
        error: () => this.toastr.error('Erro ao registrar visitante!'),
      });
  }

  navigate(route: string) {
    this.router.navigate([route]);
  }
}
