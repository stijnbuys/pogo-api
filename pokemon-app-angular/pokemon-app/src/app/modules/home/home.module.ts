import { NgModule } from '@angular/core';

import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from 'src/app/components/home/home.component';
import { PokemonTileComponent } from 'src/app/components/pokedex/pokemon-tile/pokemon-tile.component';
import { PokedexComponent } from 'src/app/components/pokedex/pokedex.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    HomeComponent,
    PokemonTileComponent,
    PokedexComponent
  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    FormsModule,
  ]
})
export class HomeModule { }
