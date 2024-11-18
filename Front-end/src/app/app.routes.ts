import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { RegisterSuccessComponent } from './pages/register/register-success/register-success.component';
import { ResetPasswordComponent } from './pages/reset-password/reset-password.component';
import { ResetPasswordSuccessComponent } from './pages/reset-password/reset-password-success/reset-password-success.component';
import { ResetPasswordFormComponent } from './pages/reset-password/reset-password-form/reset-password-form.component';
import { WarningsComponent } from './pages/warnings/warnings.component';
import { AuthGuardService } from './services/auth-guard.service';
import { VisitorsComponent } from './pages/visitors/visitors.component';
import { RentSpaceComponent } from './pages/rent-space/rent-space.component';
import { CreateNotificationComponent } from './pages/warnings/create-warning/create-notification.component';
import { RentSpaceTableComponent } from './pages/rent-space/rent-space-table/rent-space-table.component';
import { RentSpaceFormComponent } from './pages/rent-space/rent-space-form/rent-space-form.component';
import { CreateWarningComponent } from './pages/warnings/create-notification/create-warning.component';

export const routes: Routes = [
  {
    path: '',
    component: LoginComponent,
  },
  {
    path: 'register',
    component: RegisterComponent,
  },
  {
    path: 'register-success',
    component: RegisterSuccessComponent,
  },
  {
    path: 'reset-password',
    component: ResetPasswordComponent,
  },
  {
    path: 'reset-password-success',
    component: ResetPasswordSuccessComponent,
  },
  {
    path: 'reset-password-form',
    component: ResetPasswordFormComponent,
  },
  {
    path: 'warnings',
    component: WarningsComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'visitors',
    component: VisitorsComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'rent-space',
    component: RentSpaceComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'create-notification',
    component: CreateNotificationComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'rent-space-table',
    component: RentSpaceTableComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'reservations/new/:spaceId',
    component: RentSpaceFormComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'create-warning',
    component: CreateWarningComponent,
    canActivate: [AuthGuardService]
  }
];
