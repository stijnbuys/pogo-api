package be.stijn.pokemonapp.pojo.pogoApi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TypesResponse {

    @JsonProperty("form")
    private String form;
    @JsonProperty("pokemon_id")
    private long id;
    @JsonProperty("pokemon_name")
    private String name;
    @JsonProperty("type")
    private List<String> types;
}
