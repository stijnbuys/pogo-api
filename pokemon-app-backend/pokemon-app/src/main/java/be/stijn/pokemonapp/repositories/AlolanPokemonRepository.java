package be.stijn.pokemonapp.repositories;

import be.stijn.pokemonapp.entities.AlolanPokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlolanPokemonRepository extends JpaRepository<AlolanPokemon, Long> {
}
