package be.stijn.pokemonapp.pojo.pogoApi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormResponse {

    @JsonProperty("pokemon_id")
    private long id;
    @JsonProperty("form")
    private String form;
}
