import { Component, OnInit } from '@angular/core';
import { PokemonService } from 'src/app/services/pokemon.service';

@Component({
  selector: 'app-pokedex',
  templateUrl: './pokedex.component.html',
  styleUrls: ['./pokedex.component.css']
})
export class PokedexComponent implements OnInit {

  pokemonlist: any;

  kanto: any;
  johto: any
  hoenn: any;
  sinnoh: any;
  unova: any;
  galar: any;

  name: string;

  constructor(private pokemonService: PokemonService) { }

  ngOnInit(): void {

    this.pokemonService.getPokemons().subscribe( data => {
    this.pokemonlist = data;

    this.kanto = this.pokemonlist.filter(pokemon => pokemon.gen == 1);
    this.johto = this.pokemonlist.filter(pokemon => pokemon.gen == 2);
    this.hoenn = this.pokemonlist.filter(pokemon => pokemon.gen == 3);
    this.sinnoh = this.pokemonlist.filter(pokemon => pokemon.gen == 4);
    this.unova = this.pokemonlist.filter(pokemon => pokemon.gen == 5);
    this.galar = this.pokemonlist.filter(pokemon => pokemon.gen == 8);

      });

  }

  filterByName(name: string) {

    console.warn("name:" + name);
    this.kanto = this.pokemonlist.filter(pokemon => pokemon.gen == 1);
    this.kanto = this.kanto.filter(pokemon => pokemon.name.toLowerCase().includes(name.toLowerCase()));

    this.johto = this.pokemonlist.filter(pokemon => pokemon.gen == 2);
    this.johto = this.johto.filter(pokemon => pokemon.name.toLowerCase().includes(name.toLowerCase()));

    this.hoenn = this.pokemonlist.filter(pokemon => pokemon.gen == 3);
    this.hoenn = this.hoenn.filter(pokemon => pokemon.name.toLowerCase().includes(name.toLowerCase()));

    this.sinnoh = this.pokemonlist.filter(pokemon => pokemon.gen == 4);
    this.sinnoh = this.sinnoh.filter(pokemon => pokemon.name.toLowerCase().includes(name.toLowerCase()));

    this.unova = this.pokemonlist.filter(pokemon => pokemon.gen == 5);
    this.unova = this.unova.filter(pokemon => pokemon.name.toLowerCase().includes(name.toLowerCase()));
    
    this.galar = this.pokemonlist.filter(pokemon => pokemon.gen == 8);
    this.galar = this.galar.filter(pokemon => pokemon.name.toLowerCase().includes(name.toLowerCase()));

  }

}
