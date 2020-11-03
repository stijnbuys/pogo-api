package be.stijn.pokemonapp.pojo;


import be.stijn.pokemonapp.entities.RoleEntity;
import be.stijn.pokemonapp.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String username;
    private String password;
    private String email;
    private String pogoName;

    public UserEntity mapToUser() {

        List<RoleEntity> roles = new ArrayList<>();
        RoleEntity role = new RoleEntity();
        role.setId(1);
        role.setName("USER");
        roles.add(new RoleEntity());

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        userEntity.setEmail(email);
        userEntity.setPogoName(pogoName);
        userEntity.setRoles(roles);
        return userEntity;

    }
}
