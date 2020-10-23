package be.stijn.pokemonapp.beans;

import be.stijn.pokemonapp.apis.PogoApi;
import be.stijn.pokemonapp.entities.AlolanPokemon;
import be.stijn.pokemonapp.entities.PogoApiHash;
import be.stijn.pokemonapp.services.HashService;
import be.stijn.pokemonapp.services.PokemonMoveService;
import be.stijn.pokemonapp.services.PokemonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class PokemonDataUpdate {

    @Autowired
    HashService hashService;

    @Autowired
    PokemonService pokemonService;

    @Autowired
    PokemonMoveService pokemonMoveService;

    public void updatePokemonData()
    {
        log.info("[START UPDATING FROM POGO API]");

        List<PogoApiHash> hashes = hashService.getNewHashes();

        for (PogoApiHash hash: hashes) {
            if (hash.getApiFilename().equals("released_pokemon.json")) {
                pokemonService.updateReleasedPokemons();
                hashService.updateHash(hash);
            }
        }

        for (PogoApiHash hash: hashes) {
            if (hash.getApiFilename().equals("alolan_pokemon.json")) {
                pokemonService.updateAlolanPokemons();
                hashService.updateHash(hash);
            }
        }

        for (PogoApiHash hash: hashes) {
            if (hash.getApiFilename().equals("galarian_pokemon.json")) {
                pokemonService.updateGalarianPokemons();
                hashService.updateHash(hash);
            }
        }

        for (PogoApiHash hash: hashes) {
            if (hash.getApiFilename().equals("fast_moves.json")) {
                pokemonMoveService.updateFastMoves();
                hashService.updateHash(hash);
            }
        }

        for (PogoApiHash hash: hashes) {
            if (hash.getApiFilename().equals("charged_moves.json")) {
                pokemonMoveService.updateChargedMoves();
                hashService.updateHash(hash);
            }
        }

        for (PogoApiHash hash: hashes) {
            if (hash.getApiFilename().equals("pokemon_stats.json")) {
                pokemonService.updateBaseStats();
                hashService.updateHash(hash);
            }
        }

        log.info("[FINISHED WITH UPDATING FROM POGO API]");
    }
}
