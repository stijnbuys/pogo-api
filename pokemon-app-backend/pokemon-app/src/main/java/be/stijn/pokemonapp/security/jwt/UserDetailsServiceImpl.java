package be.stijn.pokemonapp.security.jwt;

import be.stijn.pokemonapp.entities.RoleEntity;
import be.stijn.pokemonapp.entities.UserEntity;
import be.stijn.pokemonapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserEntity user = userRepository.findByUsername(username);

        return User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(getRoles(user))
                .build();
    }

    private String[] getRoles(UserEntity user) {
        return user.getRoles().stream().map(RoleEntity::getName).toArray(String[]::new);
    }
}
