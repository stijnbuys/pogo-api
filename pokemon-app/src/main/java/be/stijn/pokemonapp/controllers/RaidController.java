package be.stijn.pokemonapp.controllers;

import be.stijn.pokemonapp.apis.PogoApi;
import be.stijn.pokemonapp.entities.Pokemon;
import be.stijn.pokemonapp.repositories.PokemonRepository;
import be.stijn.pokemonapp.services.PokemonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RaidController {

    @Autowired
    PokemonService pokemonService;

    @Autowired
    PokemonRepository pokemonRepository;

    //@PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/pokemons")
    public ResponseEntity<List<Pokemon>> hello() {

        List<Pokemon> pokemonList = pokemonRepository.findAllByFormOrderByPokedex("Normal");
        pokemonList.addAll(pokemonRepository.findAllByFormOrderByPokedex("Origin"));
        pokemonList.addAll(pokemonRepository.findAllByFormOrderByPokedex("Altered"));

        return ResponseEntity.ok(pokemonList);
    }
}
