package be.stijn.pokemonapp.repositories;

import be.stijn.pokemonapp.entities.PogoApiHash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PogoApiHashRepository extends JpaRepository<PogoApiHash, Long> {
}
