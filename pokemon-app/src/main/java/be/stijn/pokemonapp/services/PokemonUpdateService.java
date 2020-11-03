package be.stijn.pokemonapp.services;

import be.stijn.pokemonapp.entities.AlolanPokemon;
import be.stijn.pokemonapp.entities.GalarianPokemon;
import be.stijn.pokemonapp.entities.Pokemon;

import java.util.List;

public interface PokemonUpdateService {
    List<Pokemon> updateReleasedPokemons();
    List<AlolanPokemon> updateAlolanPokemons();
    List<GalarianPokemon> updateGalarianPokemons();
    void updateShinys();
    void updateShadowPokemons();
    void updateGens();
    void updateTypes();
    void updateBaseStats();

}
