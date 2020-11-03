package be.stijn.pokemonapp.pojo.pogoApi;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShinyListResponse {

    private List<ShinyResponse> shinyList = new ArrayList<>();

    @JsonAnySetter
    public void setShinyResponse(String pokedex, ShinyResponse h) {
        this.shinyList.add(h);
    }


}
