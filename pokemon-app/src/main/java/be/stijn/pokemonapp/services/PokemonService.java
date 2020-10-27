package be.stijn.pokemonapp.services;

import be.stijn.pokemonapp.entities.AlolanPokemon;
import be.stijn.pokemonapp.entities.GalarianPokemon;
import be.stijn.pokemonapp.entities.Pokemon;

import java.util.List;

public interface PokemonService {
    List<Pokemon> updateReleasedPokemons();
    List<AlolanPokemon> updateAlolanPokemons();
    List<GalarianPokemon> updateGalarianPokemons();
    void updateShadowPokemons();
    void updateGens();
    void updateTypes();
    void updateBaseStats();

}
