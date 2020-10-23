package be.stijn.pokemonapp.services;

import be.stijn.pokemonapp.entities.PogoApiHash;

import java.util.List;

public interface HashService {
    List<PogoApiHash> getNewHashes();
    PogoApiHash updateHash(PogoApiHash hash);
}
