import { Component, OnInit } from '@angular/core';
import { Pokemon } from 'src/app/models/pokemon';
import { PokemonService } from 'src/app/services/pokemon.service';

@Component({
  selector: 'app-pokedex',
  templateUrl: './pokedex.component.html',
  styleUrls: ['./pokedex.component.css']
})
export class PokedexComponent implements OnInit {

  private pokemonList: Pokemon[];

  kanto: any;
  johto: any
  hoenn: any;
  sinnoh: any;
  unova: any;
  galar: any;

  name: string = "";
  types: string[] = ["Normal", "Grass", "Fire", "Water", "Electric", "Bug", "Poison", "Ground", "Rock", "Steel", "Ice", "Psychic", "Fighting", "Fairy", "Dark", "Flying", "Ghost", "Dragon"];

  constructor(private pokemonService: PokemonService) { }

  ngOnInit(): void {

    this.pokemonService.getPokemons().subscribe( data => {
    this.pokemonList = data;

    this.kanto = this.pokemonList.filter(pokemon => pokemon.gen == 1);
    this.johto = this.pokemonList.filter(pokemon => pokemon.gen == 2);
    this.hoenn = this.pokemonList.filter(pokemon => pokemon.gen == 3);
    this.sinnoh = this.pokemonList.filter(pokemon => pokemon.gen == 4);
    this.unova = this.pokemonList.filter(pokemon => pokemon.gen == 5);
    this.galar = this.pokemonList.filter(pokemon => pokemon.gen == 8);

      });

  }

  addNameToFilter(name: string) {

    this.name = name;
    this.filter();
  }

  addTypeToFilter(type: string) {

    if (this.types.length != 18) {
      if (!this.types.includes(type)) {
        this.types.push(type);
      }
      else {
        this.types = this.types.filter(t => t !== type)
      }
    }
    else {
      this.types = [];
      this.types.push(type);
    }

    this.filter();
  }

  filterByTypes(p: any): boolean {

    let typeOne: boolean;
    let typeTwo: boolean;
    this.types.forEach(t => {

      p.types.forEach(pt => {
        
        if (pt.includes(t)) {

          if (!typeOne) {
            typeOne = true;
          }
          else { 
            typeTwo = true;
          }
        }
      });
     });
     return typeOne || typeTwo;
  }

  filter() {

    this.kanto = this.pokemonList.filter(pokemon => pokemon.gen == 1);
    this.kanto = this.kanto.filter(pokemon => pokemon.name.toLowerCase().includes(this.name.toLowerCase()));
    this.kanto = this.kanto.filter(pokemon => this.filterByTypes(pokemon));


    this.johto = this.pokemonList.filter(pokemon => pokemon.gen == 2);
    this.johto = this.johto.filter(pokemon => pokemon.name.toLowerCase().includes(this.name.toLowerCase()));
    this.johto = this.johto.filter(pokemon => this.filterByTypes(pokemon));

    this.hoenn = this.pokemonList.filter(pokemon => pokemon.gen == 3);
    this.hoenn = this.hoenn.filter(pokemon => pokemon.name.toLowerCase().includes(this.name.toLowerCase()));
    this.hoenn = this.hoenn.filter(pokemon => this.filterByTypes(pokemon));

    this.sinnoh = this.pokemonList.filter(pokemon => pokemon.gen == 4);
    this.sinnoh = this.sinnoh.filter(pokemon => pokemon.name.toLowerCase().includes(this.name.toLowerCase()));
    this.sinnoh = this.sinnoh.filter(pokemon => this.filterByTypes(pokemon));

    this.unova = this.pokemonList.filter(pokemon => pokemon.gen == 5);
    this.unova = this.unova.filter(pokemon => pokemon.name.toLowerCase().includes(this.name.toLowerCase()));
    this.unova = this.unova.filter(pokemon => this.filterByTypes(pokemon));

    this.galar = this.pokemonList.filter(pokemon => pokemon.gen == 8);
    this.galar = this.galar.filter(pokemon => pokemon.name.toLowerCase().includes(this.name.toLowerCase()));
    this.galar = this.galar.filter(pokemon => this.filterByTypes(pokemon));

  }
}
