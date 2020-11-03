package be.stijn.pokemonapp;

import be.stijn.pokemonapp.beans.PokemonDataUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class PokemonAppApplication {

	@Autowired
	private PokemonDataUpdate pokemonDataUpdate;

	public static void main(String[] args) {
		SpringApplication.run(PokemonAppApplication.class, args);
	}

	@Scheduled(fixedDelay = 30000)
	public void scheduleFixedDelayTask() throws Exception {

		pokemonDataUpdate.updatePokemonData();
	}

}
