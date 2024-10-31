import { Component } from '@angular/core';
import { HomeLayoutComponent } from '../../components/home-layout/home-layout.component';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user.model';
import { VisitorService } from '../../services/visitor.service';
import { Visitor } from '../../models/visitor.model';
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-visitors',
  standalone: true,
  imports: [HomeLayoutComponent, NgFor, NgIf],
  templateUrl: './visitors.component.html',
  styleUrl: './visitors.component.scss',
})
export class VisitorsComponent {
  user!: User;
  visitors: Visitor[] = [];

  constructor(
    private userService: UserService,
    private visitorService: VisitorService
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
}
