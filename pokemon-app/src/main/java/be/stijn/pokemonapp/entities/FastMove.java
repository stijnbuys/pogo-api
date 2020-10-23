package be.stijn.pokemonapp.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fast_moves")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FastMove {

    @Id
    private long id;
    private String name;
    private String type;
    private int power;
    private int duration;
    private int energyDelta;
    private double staminaLossScaler;

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FastMove)) {
            return false;
        }
        FastMove c = (FastMove) o;

        return id == c.id && name.equals(c.name) && type.equals(c.type) && duration == c.duration && energyDelta == c.energyDelta && power == c.power && staminaLossScaler == c.staminaLossScaler;
    }

    @Override
    public String toString()
    {
        return "[FM] [" + id + "] [" + type.toUpperCase() + "] ["  + name.toUpperCase() + "]";
    }

}
