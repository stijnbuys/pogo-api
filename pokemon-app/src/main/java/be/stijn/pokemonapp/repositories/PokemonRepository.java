package be.stijn.pokemonapp.repositories;

import be.stijn.pokemonapp.entities.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    List<Pokemon> findAllByFormOrderByPokedex(String form);
}
