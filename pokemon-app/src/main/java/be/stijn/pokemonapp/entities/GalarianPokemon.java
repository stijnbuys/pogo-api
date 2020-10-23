package be.stijn.pokemonapp.entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "galarian_pokemons")
@Getter
@Setter
@NoArgsConstructor
public class GalarianPokemon extends Pokemon {

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof GalarianPokemon)) {
            return false;
        }
        GalarianPokemon c = (GalarianPokemon) o;

        return getId() == c.getId();
    }

    @Override
    public String toString() {
        return "[GALARIAN] " + super.toString() ;
    }
}
