import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { FormsModule } from '@angular/forms';
import { RegisterComponent } from './components/login/register/register.component';
import { HttpClientModule } from '@angular/common/http';
import { AuthService } from './auth/auth.service';
import { AuthGuardService } from './auth/auth-guard.service';
import { JwtHelper } from 'angular2-jwt';
import { PokemonTileComponent } from './components/pokedex/pokemon-tile/pokemon-tile.component';
import { PokedexComponent } from './components/pokedex/pokedex.component';
import { HomeModule } from './modules/home/home.module';
import { StartComponent } from './components/start/start/start.component';
import { ValidatorComponent } from './components/login/validator/validator.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    StartComponent,
    ValidatorComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule, 
    HomeModule,
  ],
  providers: [ 
    AuthGuardService,
    AuthService,
    JwtHelper
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
