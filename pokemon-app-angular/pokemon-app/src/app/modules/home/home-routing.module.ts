import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PokedexComponent } from 'src/app/components/pokedex/pokedex.component';
import { StartComponent } from 'src/app/components/start/start/start.component';
import { AuthGuardService as AuthGuard } from '../../auth/auth-guard.service';



const routes: Routes = [
  { path: '', component: StartComponent/*, canActivate: [AuthGuard] */ },
  { path: 'pokedex', component: PokedexComponent/*, canActivate: [AuthGuard] */ },
  { path: '**', redirectTo: '' }

];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
