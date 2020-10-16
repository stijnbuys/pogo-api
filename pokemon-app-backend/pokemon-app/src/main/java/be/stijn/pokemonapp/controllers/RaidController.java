package be.stijn.pokemonapp.controllers;

import be.stijn.pokemonapp.pojo.LoginRequest;
import be.stijn.pokemonapp.pojo.LoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/raids")
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RaidController {

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/current")
    public ResponseEntity<String> hello() {

        return ResponseEntity.ok("Pokémon");
    }
}
