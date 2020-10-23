package be.stijn.pokemonapp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseStats {

    private Integer attack;
    private Integer defense;
    private Integer stamina;

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BaseStats)) {
            return false;
        }
        BaseStats c = (BaseStats) o;

        //CHECK
        /*System.out.println("---");
        System.out.println(o);
        System.out.println(c);
        System.out.println(attack + " " + ((BaseStats) o).attack + " " + ((int)attack == (int)c.attack));
        System.out.println(defense + " " + c.defense + " " + ((int)defense == (int)c.defense));
        System.out.println(stamina + " " + c.stamina + " " + ((int)stamina == (int)c.stamina));

        System.out.println((int)attack == (int)c.attack && (int)defense == (int)c.defense && (int)stamina == (int)c.stamina);*/

        return (int)attack == (int)c.attack && (int)defense == (int)c.defense && (int)stamina == (int)c.stamina;
    }

    @Override
    public String toString() {
        return " [A " + attack +"] [D "  + defense +"] [S " + stamina +"]" ;
    }
}
