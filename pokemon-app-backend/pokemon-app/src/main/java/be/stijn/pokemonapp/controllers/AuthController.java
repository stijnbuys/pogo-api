package be.stijn.pokemonapp.controllers;

import be.stijn.pokemonapp.entities.UserEntity;
import be.stijn.pokemonapp.pojo.LoginRequest;
import be.stijn.pokemonapp.pojo.LoginResponse;
import be.stijn.pokemonapp.security.jwt.JwtTokenUtil;
import be.stijn.pokemonapp.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {

    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginUser) {
        final Authentication authentication = authenticate(loginUser);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok(createLoginResponse(authentication));
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody UserEntity user) {

        userService.registerUser(user);

        //LoginRequest loginUser = new LoginRequest(user.getUsername(),user.getPassword());
        //final Authentication authentication = authenticate(loginUser);


        return ResponseEntity.ok(LoginResponse.builder().token("abc").build());
    }


    private Authentication authenticate(LoginRequest loginUser) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getEmail(),
                        loginUser.getPassword()
                )
        );
    }

    private LoginResponse createLoginResponse(Authentication authentication) {
        return LoginResponse.builder().token(jwtTokenUtil.generateToken(authentication)).build();
    }
}
