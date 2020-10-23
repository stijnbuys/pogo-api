package be.stijn.pokemonapp.services;

import be.stijn.pokemonapp.entities.ChargedMove;
import be.stijn.pokemonapp.entities.FastMove;
import java.util.List;

public interface PokemonMoveService {
    List<FastMove> updateFastMoves();
    List<ChargedMove> updateChargedMoves();
}
