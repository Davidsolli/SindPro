import { Component, OnInit } from '@angular/core';
import { HomeLayoutComponent } from '../../components/home-layout/home-layout.component';
import { DatePipe, NgFor, NgIf } from '@angular/common';
import { User } from '../../models/user.model';
import { UserService } from '../../services/user.service';
import { NotificationService } from '../../services/notification.service';
import { Notification } from '../../models/notification.model';

@Component({
  selector: 'app-warnings',
  standalone: true,
  imports: [HomeLayoutComponent, NgIf, NgFor, DatePipe],
  templateUrl: './warnings.component.html',
  styleUrls: ['./warnings.component.scss'],
})
export class WarningsComponent implements OnInit {
  user!: User;
  notifications!: Notification[];
  page = 0;
  size = 4;
  totalElements = 0;

  constructor(
    private userService: UserService,
    private notificationService: NotificationService
  ) {
    this.notificationList(this.page, this.size);
  }

  ngOnInit(): void {
    this.userService.getUser().subscribe(
      (userData: User) => {
        this.user = userData;
      },
      (error) => {
        console.error('Erro ao buscar usuário:', error);
      }
    );
  }

  notificationList(page: number, size: number) {
    this.notificationService.getNotifications(page, size).subscribe({
      next: (response) => {
        this.notifications = response.content;
        this.totalElements = response.totalElements;
        this.page = page;
      },
      error: (error) => {
        console.error('Erro ao buscar notificações');
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
}
