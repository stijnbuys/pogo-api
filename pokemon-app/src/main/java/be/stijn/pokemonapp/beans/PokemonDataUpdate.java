package be.stijn.pokemonapp.beans;

import be.stijn.pokemonapp.entities.PogoApiHash;
import be.stijn.pokemonapp.services.HashService;
import be.stijn.pokemonapp.services.PokemonMoveService;
import be.stijn.pokemonapp.services.PokemonUpdateService;
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
    PokemonUpdateService pokemonUpdateService;

    @Autowired
    PokemonMoveService pokemonMoveService;

    public void updatePokemonData() throws Exception {
        log.info("[START UPDATING FROM POGO API]");

        List<PogoApiHash> hashes = hashService.getNewHashes();

        for (PogoApiHash hash: hashes) {
            if (hash.getApiFilename().equals("released_pokemon.json")) {
                pokemonUpdateService.updateReleasedPokemons();
                hashService.updateHash(hash);
            }
        }

        for (PogoApiHash hash: hashes) {
            if (hash.getApiFilename().equals("alolan_pokemon.json")) {
                pokemonUpdateService.updateAlolanPokemons();
                hashService.updateHash(hash);
            }
        }

        for (PogoApiHash hash: hashes) {
            if (hash.getApiFilename().equals("galarian_pokemon.json")) {
                pokemonUpdateService.updateGalarianPokemons();
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
                pokemonUpdateService.updateBaseStats();
                hashService.updateHash(hash);
            }
        }
        for (PogoApiHash hash: hashes) {
            if (hash.getApiFilename().equals("pokemon_types.json")) {
                pokemonUpdateService.updateTypes();
                hashService.updateHash(hash);
            }
        }

        for (PogoApiHash hash: hashes) {
            if (hash.getApiFilename().equals("pokemon_generations.json")) {
                pokemonUpdateService.updateGens();
                hashService.updateHash(hash);
            }
        }

        for (PogoApiHash hash: hashes) {
            if (hash.getApiFilename().equals("shadow_pokemon.json")) {
                pokemonUpdateService.updateShadowPokemons();
                hashService.updateHash(hash);
            }
        }

        for (PogoApiHash hash: hashes) {
            if (hash.getApiFilename().equals("shiny_pokemon.json")) {
                pokemonUpdateService.updateShinys();
                hashService.updateHash(hash);
            }
        }

        log.info("[FINISHED WITH UPDATING FROM POGO API]");
    }
}
