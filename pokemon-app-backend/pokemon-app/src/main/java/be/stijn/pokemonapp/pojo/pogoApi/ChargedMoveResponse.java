package be.stijn.pokemonapp.pojo.pogoApi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChargedMoveResponse {

        @JsonProperty("move_id")
        private long moveId;
        @JsonProperty("name")
        private String name;
        @JsonProperty("type")
        private String type;
        @JsonProperty("power")
        private int power;
        @JsonProperty("duration")
        private int duration;
        @JsonProperty("energy_delta")
        private int energyDelta;
        @JsonProperty("stamina_loss_scaler")
        private int staminaLossScaler;

}
