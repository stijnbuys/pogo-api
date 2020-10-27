package be.stijn.pokemonapp.pojo.pogoApi;

import com.fasterxml.jackson.annotation.JsonAnySetter;
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
public class GenListResponse {

    private List<GenResponse[]> gens = new ArrayList<>();

    @JsonAnySetter
    public void setGenResponse(String name, GenResponse[] g) {
        this.gens.add(g);
    }
}
