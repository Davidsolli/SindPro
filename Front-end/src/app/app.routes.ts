import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { RegisterSuccessComponent } from './pages/register/register-success/register-success.component';
import { PathLocationStrategy } from '@angular/common';
import { ResetPasswordComponent } from './pages/reset-password/reset-password.component';
import { ResetPasswordSuccessComponent } from './pages/reset-password/reset-password-success/reset-password-success.component';
import { ResetPasswordFormComponent } from './pages/reset-password/reset-password-form/reset-password-form.component';

export const routes: Routes = [
    {
        path: "",
        component: LoginComponent
    },
    {
        path: "register",
        component: RegisterComponent
    },
    {
        path: "register-success",
        component: RegisterSuccessComponent
    },
    {
        path: "reset-password",
        component: ResetPasswordComponent
    },
    {
        path: "reset-password-success",
        component: ResetPasswordSuccessComponent
    },
    {
        path: "reset-password-form",
        component: ResetPasswordFormComponent
    }
];
