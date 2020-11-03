package be.stijn.pokemonapp.pojo.pogoApi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ShinyResponse {

    @JsonProperty("id")
    private long id;
    @JsonProperty("alolan_shiny")
    private boolean alolanShiny;
}
