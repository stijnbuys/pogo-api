import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login/login.component';
import { AuthGuardService as AuthGuard } from './auth/auth-guard.service';
import { RegisterComponent } from './components/login/register/register.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {path: 'home' , component: HomeComponent,
    loadChildren: () => import('./modules/home/home.module')
      .then(m => m.HomeModule),
      canActivate: [AuthGuard]
  },
 { path: '**', redirectTo: 'home' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
