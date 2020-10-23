package be.stijn.pokemonapp.services;

import be.stijn.pokemonapp.entities.UserEntity;
import be.stijn.pokemonapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void registerUser(UserEntity user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));
        userRepository.save(user);
    }

}
