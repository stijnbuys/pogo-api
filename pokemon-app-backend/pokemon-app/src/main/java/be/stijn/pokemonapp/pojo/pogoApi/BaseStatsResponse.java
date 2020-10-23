package be.stijn.pokemonapp.pojo.pogoApi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseStatsResponse {

    @JsonProperty("pokemon_id")
    private long id;
    @JsonProperty("form")
    private String form;
    @JsonProperty("base_attack")
    private int baseAttack;
    @JsonProperty("base_defense")
    private int baseDefense;
    @JsonProperty("base_stamina")
    private int baseStamina;

}
