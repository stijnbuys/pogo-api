package be.stijn.pokemonapp.entities;


import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    @NotNull
    @Column(unique = true)
    @Size(min = 5, max = 20, message = "Username must be between 10 and 200 characters")
    private String username;
    @NotNull
    private String password;
    @NotNull
    @Email(message = "invalid emailadress")
    @Column(unique = true)
    private String email;
    private String pogoName;

    @ManyToMany(fetch = EAGER, cascade = {CascadeType.ALL})
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<RoleEntity> roles;

}
