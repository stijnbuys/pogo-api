package be.stijn.pokemonapp.services;

import be.stijn.pokemonapp.entities.PogoApiHash;

import java.util.List;

public interface HashService {
    List<PogoApiHash> getNewHashes() throws Exception;
    PogoApiHash updateHash(PogoApiHash hash);
}
