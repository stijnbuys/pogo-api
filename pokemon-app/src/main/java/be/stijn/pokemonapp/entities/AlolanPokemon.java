package be.stijn.pokemonapp.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "alolan_pokemons")
@Getter
@Setter
@NoArgsConstructor
public class AlolanPokemon extends Pokemon {


    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AlolanPokemon)) {
            return false;
        }
        AlolanPokemon c = (AlolanPokemon) o;

        return getPokedex() == c.getPokedex();
    }

    @Override
    public String toString() {
        return "[ALOLAN] " + super.toString();
    }
}
