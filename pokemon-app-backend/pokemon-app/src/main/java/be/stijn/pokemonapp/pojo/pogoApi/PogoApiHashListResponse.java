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
public class PogoApiHashListResponse {

    private List<PogoApiHashResponse> hashList = new ArrayList<>();

    @JsonAnySetter
    public void setHashResponse(String name, PogoApiHashResponse h) {
        this.hashList.add(h);
    }
}
