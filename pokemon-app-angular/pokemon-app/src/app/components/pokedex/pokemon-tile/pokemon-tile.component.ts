import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-pokemon-tile',
  templateUrl: './pokemon-tile.component.html',
  styleUrls: ['./pokemon-tile.component.css']
})
export class PokemonTileComponent implements OnInit {

  constructor() { }

  @Input()
  pokemon: any;

  imgUrl: string;

  ngOnInit(): void {

    let form = "00";


    //GIRATINA
    if (this.pokemon.pokedex == "487" && this.pokemon.form == "Altered") {
      form = "11";
    }
    //GIRATINA
    if (this.pokemon.pokedex == "487" && this.pokemon.form == "Origin") {
      form = "12";
    }

    //DEOXYS
    if (this.pokemon.pokedex == "386" && this.pokemon.form == "Normal") {
      form = "11";
    }

    //UNOWN
    if (this.pokemon.pokedex == "201") {
      form = "11";
    }

    //ROTOM
    if (this.pokemon.pokedex == "479") {
      form = "11";
    }

    //KYUREM
    if (this.pokemon.pokedex == "646"  && this.pokemon.form == "Normal") {
      form = "11";
    }

    //GENESECT
    if (this.pokemon.pokedex == "649"  && this.pokemon.form == "Normal") {
      form = "11";
    }

    //SIRFETCH'D
    if (this.pokemon.pokedex == "865"  && this.pokemon.form == "Normal") {
      form = "31";
    }


    if (this.pokemon.pokedex.toString().length == 1) {
      this.imgUrl = "../../../assets/pokemon/pokemon_icon_00" + this.pokemon.pokedex + "_" + form + ".png";
    }

    if (this.pokemon.pokedex.toString().length == 2) {
      this.imgUrl = "../../../assets/pokemon/pokemon_icon_0" + this.pokemon.pokedex + "_" + form + ".png";
    }

    if (this.pokemon.pokedex.toString().length == 3) {
      this.imgUrl = "../../../assets/pokemon/pokemon_icon_" + this.pokemon.pokedex + "_" + form + ".png";
    }

    console.warn(this.imgUrl)
  }

}
