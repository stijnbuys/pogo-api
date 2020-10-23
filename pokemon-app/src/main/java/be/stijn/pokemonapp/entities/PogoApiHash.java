package be.stijn.pokemonapp.entities;

import be.stijn.pokemonapp.apis.PogoApi;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pogo_api_hashes")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PogoApiHash {

    @Id
    private String apiFilename;
    @NotNull
    private String hash;

    @Override
    public String toString() {
        return "[" + apiFilename.toUpperCase() + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PogoApiHash)) {
            return false;
        }
        PogoApiHash c = (PogoApiHash) o;

        return apiFilename.equals(c.apiFilename) && hash.equals(c.getHash());
    }
}
