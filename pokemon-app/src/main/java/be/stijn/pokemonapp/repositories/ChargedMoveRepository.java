package be.stijn.pokemonapp.repositories;

import be.stijn.pokemonapp.entities.ChargedMove;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargedMoveRepository extends JpaRepository<ChargedMove, Long> {

}
