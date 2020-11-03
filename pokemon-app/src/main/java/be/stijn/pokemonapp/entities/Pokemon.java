package be.stijn.pokemonapp.entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "pokemons")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Pokemon implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    private long pokedex;
    @NotNull
    private String name;

    private String form;

    @Embedded
    private BaseStats baseStats;

    @ElementCollection
    private List<String> types;

    private Integer gen;

    private boolean shadow;

    private boolean shiny;

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Pokemon)) {
            return false;
        }
        Pokemon c = (Pokemon) o;

        return pokedex == c.pokedex && form.equals(c.getForm());
    }

    @Override
    public String toString() {
        return "[" + pokedex + "] ["  + name.toUpperCase() + "] [" + form .toUpperCase()+ "]";
    }

    public String toFullString() {
        return toString() + " [A " + baseStats.getAttack() +"] [D "  + baseStats.getDefense() +"] [S " + baseStats.getStamina() +"]" ;
    }
}
