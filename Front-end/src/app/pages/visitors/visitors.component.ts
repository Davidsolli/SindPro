import { Component } from '@angular/core';
import { HomeLayoutComponent } from '../../components/home-layout/home-layout.component';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user.model';
import { VisitorService } from '../../services/visitor.service';
import { Visitor } from '../../models/visitor.model';
import { NgFor, NgIf } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-visitors',
  standalone: true,
  imports: [HomeLayoutComponent, NgFor],
  templateUrl: './visitors.component.html',
  styleUrl: './visitors.component.scss',
})
export class VisitorsComponent {
  user!: User;
  visitors: Visitor[] = [];

  constructor(
    private userService: UserService,
    private visitorService: VisitorService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.userService.getUser().subscribe(
      (userData: User) => {
        this.user = userData;
        this.visitorsList();
      },
      (error) => {
        console.error('Erro ao buscar usuário:', error);
      }
    );
  }

  visitorsList() {
    if (this.user && this.user.id) {
      this.visitorService.getVisitors(this.user.id).subscribe(
        (visitorData: Visitor[]) => {
          this.visitors = visitorData;
        },
        (error) => {
          console.error('Erro ao buscar visitantes:', error);
        }
      );
    }
  }

  deleteVisitor(id: number) {
    this.visitorService.deleteVisitor(id).subscribe(
      () => {
        this.visitors = this.visitors.filter((visitor) => visitor.id !== id);
      },
      (error) => {
        console.error('Erro ao excluir visitante:', error);
      }
    );
  }

  navigate(route: string) {
    this.router.navigate([route]);
  }
}
