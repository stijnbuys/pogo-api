package be.stijn.pokemonapp.pojo.pogoApi;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReleasedPokemonResponse {

    private List<PokemonResponse> pokemonList = new ArrayList<>();

    @JsonAnySetter
    public void setPokemonResponse(String name, PokemonResponse p) {
        this.pokemonList.add(p);
    }

}
