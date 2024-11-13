import { Component, OnInit } from '@angular/core';
import { HomeLayoutComponent } from '../../components/home-layout/home-layout.component';
import { DatePipe, NgFor, NgIf } from '@angular/common';
import { User } from '../../models/user.model';
import { UserService } from '../../services/user.service';
import { NotificationService } from '../../services/notification.service';
import { Notification } from '../../models/notification.model';
import { Warning } from '../../models/warning.model';
import { CardMessageLayoutComponent } from '../../components/card-message-layout/card-message-layout.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-warnings',
  standalone: true,
  imports: [
    HomeLayoutComponent,
    NgIf,
    NgFor,
    DatePipe,
  ],
  templateUrl: './warnings.component.html',
  styleUrls: ['./warnings.component.scss'],
})
export class WarningsComponent implements OnInit {
  user!: User;
  notifications!: Notification[];
  warnings!: Warning[];
  page = 0;
  size = 4;
  totalElements = 0;
  isModalOpen = false;

  constructor(
    private userService: UserService,
    private notificationService: NotificationService,
    private route: Router
  ) {}

  ngOnInit(): void {
    this.userService.getUser().subscribe(
      (userData: User) => {
        this.user = userData;
        this.notificationList(this.page, this.size);
      },
      (error) => {
        console.error('Erro ao buscar usuário:', error);
      }
    );

    this.warningList();
  }

  notificationList(page: number, size: number) {
    this.notificationService.getNotifications(page, size).subscribe({
      next: (response) => {
        this.notifications = response.content;
        this.totalElements = response.totalElements;
        this.page = page;
      },
      error: (error) => {
        console.error('Erro ao buscar notificações:', error);
      },
    });
  }

  warningList() {
    this.notificationService.getWarnings().subscribe({
      next: (response) => {
        this.warnings = response;
      },
    });
  }

  deleteNotification(id: number) {
    this.notificationService.deleteNotification(id).subscribe(
      () => {
        this.notifications = this.notifications.filter(
          (notification) => notification.id !== id
        );
      },
      (error) => {
        console.error(error);
      }
    );
  }

  deleteWarning(id: number) {
    this.notificationService.deleteWarning(id).subscribe(
      () => {
        this.warnings = this.warnings.filter((warning) => warning.id !== id);
      },
      (error) => {
        console.error(error);
      }
    );
  }

  navigate(route: string) {
    this.route.navigate([route]);
  }
}
