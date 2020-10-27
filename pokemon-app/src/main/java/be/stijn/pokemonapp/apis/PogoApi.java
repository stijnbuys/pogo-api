package be.stijn.pokemonapp.apis;

import be.stijn.pokemonapp.entities.AlolanPokemon;
import be.stijn.pokemonapp.entities.GalarianPokemon;
import be.stijn.pokemonapp.entities.Pokemon;
import be.stijn.pokemonapp.pojo.pogoApi.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class PogoApi {

    private final String URL = "https://pogoapi.net";

    public List<PogoApiHashResponse> getHashList() {

        final String URI = "/api/v1/api_hashes.json";

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<PogoApiHashListResponse> response = rest.exchange(URL + URI, HttpMethod.GET, entity, PogoApiHashListResponse.class);

        return response.getBody().getHashList();
    }

    public List<Pokemon> getReleasedPokemonList() {

        final String URI = "/api/v1/released_pokemon.json";

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<ReleasedPokemonResponse> response = rest.exchange(URL + URI, HttpMethod.GET, entity, ReleasedPokemonResponse.class);

        List<FormResponse> forms = getForms();

        List<Pokemon> pokemonListWithForms = new ArrayList<>();

        for (PokemonResponse p: response.getBody().getPokemonList()) {

            for ( FormResponse f: forms) {

                if ( !f.getForm().equals("Alola") && !f.getForm().equals("Galarian") && !f.getForm().equals("Galarian_standard") && !f.getForm().equals("Galarian_zen") && !f.getForm().equals("Shadow") && !f.getForm().equals("Purified")) {
                    if (p.getId() == f.getId()) {

                        Pokemon pokemon = pokemonResponseToPokemon(p);
                        pokemon.setForm(f.getForm());
                        pokemonListWithForms.add(pokemon);
                    }
                }
            }
        }
        return pokemonListWithForms;
    }

    public List<AlolanPokemon> getAlolanPokemonList() {

        final String URI = "/api/v1/alolan_pokemon.json";

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<AlolanPokemonResponse> response = rest.exchange(URL + URI, HttpMethod.GET, entity, AlolanPokemonResponse.class);

        List<AlolanPokemon> pokemonListWithForms = new ArrayList<>();

        for (PokemonResponse p: response.getBody().getPokemonList()) {

            AlolanPokemon pokemon = pokemonResponseToAlolanPokemon(p);
            pokemonListWithForms.add(pokemon);
        }
        return pokemonListWithForms;
    }

    public List<GalarianPokemon> getGalarianPokemonList() {

        final String URI = "/api/v1/galarian_pokemon.json";

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<GalarianPokemonResponse> response = rest.exchange(URL + URI, HttpMethod.GET, entity, GalarianPokemonResponse.class);

        List<FormResponse> forms = getForms();

        List<GalarianPokemon> pokemonListWithForms = new ArrayList<>();

        for (PokemonResponse p: response.getBody().getPokemonList()) {

            for ( FormResponse f: forms) {

                if ( f.getForm().equals("Galarian") || f.getForm().equals("Galarian_standard") || f.getForm().equals("Galarian_zen")) {
                    if (p.getId() == f.getId()) {

                        GalarianPokemon pokemon = pokemonResponseToGalarianPokemon(p);
                        pokemon.setForm(f.getForm());
                        pokemonListWithForms.add(pokemon);
                    }
                }
            }

        }
        return pokemonListWithForms;
    }

    public List<Pokemon> geShadowPokemonList() {

        final String URI = "/api/v1/shadow_pokemon.json";

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<ShadowPokemonResponse> response = rest.exchange(URL + URI, HttpMethod.GET, entity, ShadowPokemonResponse.class);

        List<Pokemon> pokemonList = new ArrayList<>();
        for (PokemonResponse p: response.getBody().getPokemonList()) {
            Pokemon pokemon = pokemonResponseToPokemon(p);
            pokemonList.add(pokemon);
        }

        return pokemonList;
    }

    public List<FastMoveResponse> getFastMoves() {

        final String URI = "/api/v1/fast_moves.json";

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<FastMoveResponse[]> response = rest.exchange(URL + URI, HttpMethod.GET, entity, FastMoveResponse[].class);

        List<FastMoveResponse> fastMoveResponseList = Arrays.asList(response.getBody());

        return fastMoveResponseList;
    }

    public List<ChargedMoveResponse> getChargedMoves() {

        final String URI = "/api/v1/charged_moves.json";

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<ChargedMoveResponse[]> response = rest.exchange(URL + URI, HttpMethod.GET, entity, ChargedMoveResponse[].class);

        List<ChargedMoveResponse> chargedMoveResponseList = Arrays.asList(response.getBody());

        return chargedMoveResponseList;
    }

    public List<BaseStatsResponse> getBaseStats() {

        final String URI = "/api/v1/pokemon_stats.json";

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<BaseStatsResponse[]> response = rest.exchange(URL + URI, HttpMethod.GET, entity, BaseStatsResponse[].class);

        List<BaseStatsResponse> baseStatsResponseList = new ArrayList<>(Arrays.asList(response.getBody()));

        return baseStatsResponseList;
    }

    public List<FormResponse> getForms() {

        final String URI = "/api/v1/pokemon_stats.json";

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<FormResponse[]> response = rest.exchange(URL + URI, HttpMethod.GET, entity, FormResponse[].class);

        List<FormResponse> baseStatsResponseList = new ArrayList<>(Arrays.asList(response.getBody()));


        return baseStatsResponseList;
    }

    public List<TypesResponse> getPokemonTypes() {

        final String URI = "/api/v1/pokemon_types.json";

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<TypesResponse[]> response = rest.exchange(URL + URI, HttpMethod.GET, entity, TypesResponse[].class);

        List<TypesResponse> typesResponseList = new ArrayList<>(Arrays.asList(response.getBody()));

        return typesResponseList;
    }


    public List<GenResponse> getPokemonGenerations() {

        final String URI = "/api/v1/pokemon_generations.json";

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);


        ResponseEntity<GenListResponse> response = rest.exchange(URL + URI, HttpMethod.GET, entity, GenListResponse.class);

        List<GenResponse> genResponseList = new ArrayList<>();
        for (GenResponse[] genArray : response.getBody().getGens()) {
            for (GenResponse g: genArray) {
                genResponseList.add(g);
            }
        }


        return genResponseList;
    }

    private Pokemon pokemonResponseToPokemon(PokemonResponse p) {
        Pokemon pokemon = new Pokemon();
        pokemon.setPokedex(p.getId());
        pokemon.setName(p.getName());
        return pokemon;
    }

    private AlolanPokemon pokemonResponseToAlolanPokemon(PokemonResponse p) {
        AlolanPokemon pokemon = new AlolanPokemon();
        pokemon.setPokedex(p.getId());
        pokemon.setName(p.getName());
        pokemon.setForm("Alola");
        return pokemon;
    }

    private GalarianPokemon pokemonResponseToGalarianPokemon(PokemonResponse p) {
        GalarianPokemon pokemon = new GalarianPokemon();
        pokemon.setPokedex(p.getId());
        pokemon.setName(p.getName());
        pokemon.setForm("Galarian");
        return pokemon;
    }
}
