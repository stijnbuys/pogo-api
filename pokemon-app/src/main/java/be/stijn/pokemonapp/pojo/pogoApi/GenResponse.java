package be.stijn.pokemonapp.pojo.pogoApi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenResponse {

    @JsonProperty("generation_number")
    private int gen;
    @JsonProperty("id")
    private long id;
}
