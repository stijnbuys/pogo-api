package be.stijn.pokemonapp.pojo.pogoApi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PogoApiHashResponse {

    @JsonProperty("api_filename")
    private String apiFilename;
    @JsonProperty("hash_sha256")
    private String hash;

}
