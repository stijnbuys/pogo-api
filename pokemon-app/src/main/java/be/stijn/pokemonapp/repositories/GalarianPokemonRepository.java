package be.stijn.pokemonapp.repositories;

import be.stijn.pokemonapp.entities.GalarianPokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalarianPokemonRepository extends JpaRepository<GalarianPokemon, Long> {
}
