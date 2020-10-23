package be.stijn.pokemonapp.controllers;

import be.stijn.pokemonapp.apis.PogoApi;
import be.stijn.pokemonapp.services.PokemonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/raids")
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RaidController {

    @Autowired
    PokemonService pokemonService;

    //@PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/current")
    public ResponseEntity<String> hello() {

        pokemonService.updateReleasedPokemons();

        return ResponseEntity.ok("ok");
    }
}
