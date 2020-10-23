package be.stijn.pokemonapp.repositories;

import be.stijn.pokemonapp.entities.FastMove;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FastMoveRepository extends JpaRepository<FastMove, Long> {
}
