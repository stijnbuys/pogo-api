package be.stijn.pokemonapp.pojo.pogoApi;

import be.stijn.pokemonapp.entities.Pokemon;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PokemonResponse {

    @JsonProperty
    private int id;
    @JsonProperty
    private String name;
    @JsonIgnore
    private String form;

    @Override
    public String toString() {
        return  name + " " + form;
    }

}
