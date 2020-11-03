package be.stijn.pokemonapp.services;

import be.stijn.pokemonapp.apis.PogoApi;
import be.stijn.pokemonapp.entities.*;
import be.stijn.pokemonapp.pojo.pogoApi.*;
import be.stijn.pokemonapp.repositories.AlolanPokemonRepository;
import be.stijn.pokemonapp.repositories.GalarianPokemonRepository;
import be.stijn.pokemonapp.repositories.PokemonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class PokemonUpdateServiceImpl implements PokemonUpdateService {

    @Autowired
    PokemonRepository pokemonRepository;

    @Autowired
    AlolanPokemonRepository alolanPokemonRepository;

    @Autowired
    GalarianPokemonRepository galarianPokemonRepository;

    @Autowired
    PogoApi pogoApi;

    private List<BaseStatsResponse> baseStatsList;
    private List<TypesResponse> typesList;
    private List<GenResponse> genList;

    @Override
    public List<Pokemon> updateReleasedPokemons() {

        List<Pokemon> updatedPokemonList = pogoApi.getReleasedPokemonList();
        List<Pokemon> currentPokemonList = pokemonRepository.findAll();
        List<Pokemon> newPokemonList = new ArrayList<>();

        //TODO UPDATING NOT ONLY ADDING -> OVERRIDE EQUALS OF POKEMON ENTITY
        for (Pokemon pokemon : updatedPokemonList) {

            if (!currentPokemonList.contains(pokemon)) {
                log.info("[PO+] ADDED NEW POKEMON " + pokemon.toString());
                newPokemonList.add(pokemon);
            }
        }

        log.info("[" + newPokemonList.size() + "] NEW POKEMON(S) ADDED IN TOTAL");
        return pokemonRepository.saveAll(newPokemonList);
    }

    @Override
    public List<AlolanPokemon> updateAlolanPokemons() {

        List<AlolanPokemon> updatedPokemonList = pogoApi.getAlolanPokemonList();
        List<AlolanPokemon> currentPokemonList = alolanPokemonRepository.findAll();
        List<AlolanPokemon> newPokemonList = new ArrayList<>();

        //TODO UPDATING NOT ONLY ADDING -> OVERRIDE EQUALS OF ALOLAN POKEMON ENTITY
        for (AlolanPokemon pokemon : updatedPokemonList) {

            if (!currentPokemonList.contains(pokemon)) {
                log.info("[PO+] ADDED NEW POKEMON " + pokemon.toString());
                newPokemonList.add(pokemon);
            }
        }

        log.info("[" + newPokemonList.size() + "] NEW ALOLAN POKEMON(S) ADDED IN TOTAL");
        return alolanPokemonRepository.saveAll(newPokemonList);
    }

    @Override
    public List<GalarianPokemon> updateGalarianPokemons() {

        List<GalarianPokemon> updatedPokemonList = pogoApi.getGalarianPokemonList();
        List<GalarianPokemon> currentPokemonList = galarianPokemonRepository.findAll();
        List<GalarianPokemon> newPokemonList = new ArrayList<>();

        //TODO UPDATING NOT ONLY ADDING -> OVERRIDE EQUALS OF ALOLAN POKEMON ENTITY
        for (GalarianPokemon pokemon : updatedPokemonList) {

            if (!currentPokemonList.contains(pokemon)) {
                log.info("[PO+] ADDED NEW POKEMON " + pokemon.toString());
                newPokemonList.add(pokemon);
            }
        }

        log.info("[" + newPokemonList.size() + "] NEW GALARIAN POKEMON(S) ADDED IN TOTAL");
        return galarianPokemonRepository.saveAll(newPokemonList);
    }

    @Override
    public void updateShinys() {
        updateShinyPokemons();
    }

    private void updateShinyPokemons() {
        List<ShinyResponse> shinyList = pogoApi.getShinyPokemonList();
        List<Pokemon> pokemonList = pokemonRepository.findAll();

        for (Pokemon p : pokemonList) {

            if (!p.isShiny()) {
                for (ShinyResponse s : shinyList) {

                    if (s.getId() == p.getPokedex()) {

                        p.setShiny(true);
                        pokemonRepository.save(p);
                        log.info("[SH*] ADDED SHINY FORM TO " + p.toString());
                    }
                }
            }
        }
    }

    @Override
    public void updateShadowPokemons() {

        List<Pokemon> shadowPokemonList = pogoApi.geShadowPokemonList();
        List<Pokemon> pokemonList = pokemonRepository.findAllByFormOrderByPokedex("Normal");

        for (Pokemon p : pokemonList) {
            if (!p.isShadow()) {
                for (Pokemon s : shadowPokemonList) {

                    if (s.getPokedex() == p.getPokedex()) {
                        p.setShadow(true);
                        pokemonRepository.save(p);

                        log.info("[SP*] ADDED SHADOW FORM TO " + p.toString());
                    }
                }
            }
        }
    }

    @Override
    public void updateGens() {
        genList = pogoApi.getPokemonGenerations();

        updateGensOfPokemons();
        updateGensOfAlolanPokemons();
        updateGensOfGalarianPokemons();
    }

    private void updateGensOfPokemons() {

        List<GenResponse> normalGenList = new ArrayList<>(genList);

        List<Pokemon> pokemonList = pokemonRepository.findAll();

        for (Pokemon p : pokemonList) {

            for (GenResponse g : normalGenList) {

                if (g.getId() == p.getPokedex()) {

                    if (p.getGen() == null) {

                        p.setGen(g.getGen());
                        pokemonRepository.save(p);
                    }
                }
            }
        }
    }

    private void updateGensOfAlolanPokemons() {

        List<GenResponse> alolanGenList = new ArrayList<>(genList);

        List<AlolanPokemon> pokemonList = alolanPokemonRepository.findAll();

        for (AlolanPokemon p : pokemonList) {

            for (GenResponse g : alolanGenList) {

                if (g.getId() == p.getPokedex()) {

                    if (p.getGen() == null) {

                        p.setGen(g.getGen());
                        alolanPokemonRepository.save(p);
                    }
                }
            }
        }
    }

    private void updateGensOfGalarianPokemons() {

        List<GenResponse> galarianGenList = new ArrayList<>(genList);

        List<GalarianPokemon> pokemonList = galarianPokemonRepository.findAll();

        for (GalarianPokemon p : pokemonList) {

            for (GenResponse g : galarianGenList) {

                if (g.getId() == p.getPokedex()) {

                    if (p.getGen() == null) {

                        p.setGen(g.getGen());
                        galarianPokemonRepository.save(p);
                    }
                }
            }
        }
    }


    @Override
    public void updateTypes() {

        typesList = pogoApi.getPokemonTypes();

        updateTypesOfPokemons();
        updateBaseStatsOfAlolanPokemons();
        updateBaseStatsOfGalarianPokemons();

    }

    private void updateTypesOfPokemons() {

        List<TypesResponse> normalTypesList = new ArrayList<>(typesList);

        //TODO FIX this
        normalTypesList.removeIf(b -> (!b.getForm().equals("Normal") && !b.getForm().equals("Altered") && !b.getForm().equals("Origin") && !b.getForm().equals("A")) && !b.getForm().equals("Attack") && !b.getForm().equals("Black") && !b.getForm().equals("Blue_striped") && !b.getForm().equals("Burn") && !b.getForm().equals("Chill") && !b.getForm().equals("Copy_2019") && !b.getForm().equals("Costume_2020") && !b.getForm().equals("Defense") && !b.getForm().equals("Douse") && !b.getForm().equals("East_sea") && !b.getForm().equals("Fall_2019") && !b.getForm().equals("Fan") && !b.getForm().equals("Frost") && !b.getForm().equals("Heat") && !b.getForm().equals("Incarnate") && !b.getForm().equals("Mow") && !b.getForm().equals("Overcast") && !b.getForm().equals("Plant") && !b.getForm().equals("Costume2020") && !b.getForm().equals("Rainy") && !b.getForm().equals("Red_striped") && !b.getForm().equals("Sandy") && !b.getForm().equals("Autumn") && !b.getForm().equals("Shock") && !b.getForm().equals("Snowy") && !b.getForm().equals("Speed") && !b.getForm().equals("Spring") && !b.getForm().equals("Standard") && !b.getForm().equals("Summer") && !b.getForm().equals("Sunny") && !b.getForm().equals("Therian") && !b.getForm().equals("Trash") && !b.getForm().equals("Vs_2019") && !b.getForm().equals("Wash") && !b.getForm().equals("West_sea") && !b.getForm().equals("White") && !b.getForm().equals("Winter") && !b.getForm().equals("Zen"));

        List<Pokemon> pokemonList = pokemonRepository.findAll();

        for (Pokemon p : pokemonList) {

            for (TypesResponse t : normalTypesList) {

                if (t.getId() == p.getPokedex() && t.getForm().equals(p.getForm())) {

                    if (p.getTypes() == null) {

                        p.setTypes(t.getTypes());
                        pokemonRepository.save(p);
                        log.info("[BS*] UPDATED TYPES OF " + p.toString());
                    } else {
                        if (!p.getTypes().equals(t.getTypes())) {
                            p.setTypes(t.getTypes());
                            pokemonRepository.save(p);
                            log.info("[BS*] UPDATED TYPES OF " + p.toString());
                        }
                    }
                }
            }
        }
    }

    private void updateTypesOfAlolanPokemons() {

        List<TypesResponse> alolanTypesList = new ArrayList<>(typesList);

        //TODO FIX this
        alolanTypesList.removeIf(b -> (!b.getForm().equals("Alola")));

        List<AlolanPokemon> pokemonList = alolanPokemonRepository.findAll();

        for (AlolanPokemon p : pokemonList) {

            for (TypesResponse t : alolanTypesList) {

                if (t.getId() == p.getPokedex() && t.getForm().equals(p.getForm())) {

                    if (t.getId() == p.getPokedex() && t.getForm().equals(p.getForm())) {

                        if (p.getTypes() == null) {

                            p.setTypes(t.getTypes());
                            alolanPokemonRepository.save(p);
                            log.info("[BS*] UPDATED TYPES OF " + p.toString());
                        } else {
                            if (!p.getTypes().equals(t.getTypes())) {
                                p.setTypes(t.getTypes());
                                alolanPokemonRepository.save(p);
                                log.info("[BS*] UPDATED TYPES OF " + p.toString());
                            }
                        }
                    }
                }
            }
        }
    }

    private void updateTypesOfGalarianPokemons() {

        List<TypesResponse> galarianTypesList = new ArrayList<>(typesList);

        //TODO FIX this
        galarianTypesList.removeIf(b -> (!b.getForm().equals("Galarian") && !b.getForm().equals("Galarian_standard") && !b.getForm().equals("Galarian_zen")));

        List<GalarianPokemon> pokemonList = galarianPokemonRepository.findAll();

        for (GalarianPokemon p : pokemonList) {

            for (TypesResponse t :  galarianTypesList) {

                if (t.getId() == p.getPokedex() && t.getForm().equals(p.getForm())) {

                    if (t.getId() == p.getPokedex() && t.getForm().equals(p.getForm())) {

                        if (p.getTypes() == null) {

                            p.setTypes(t.getTypes());
                            galarianPokemonRepository.save(p);
                            log.info("[BS*] UPDATED TYPES OF " + p.toString());
                        } else {
                            if (!p.getTypes().equals(t.getTypes())) {
                                p.setTypes(t.getTypes());
                                galarianPokemonRepository.save(p);
                                log.info("[BS*] UPDATED TYPES OF " + p.toString());
                            }
                        }
                    }
                }
            }
        }
    }


    @Override
    public void updateBaseStats() {

        baseStatsList = pogoApi.getBaseStats();

        updateBaseStatsOfPokemons();
        updateBaseStatsOfAlolanPokemons();
        updateBaseStatsOfGalarianPokemons();

        //TODO LOG TOTAL UPDATE COUNT
    }

    private void updateBaseStatsOfPokemons () {

        List<BaseStatsResponse> normalBaseStatsList = new ArrayList<>(baseStatsList);

        //TODO FIX this
        normalBaseStatsList.removeIf(b -> (!b.getForm().equals("Normal") && !b.getForm().equals("Altered") && !b.getForm().equals("Origin") && !b.getForm().equals("A")) && !b.getForm().equals("Attack") && !b.getForm().equals("Black") && !b.getForm().equals("Blue_striped") && !b.getForm().equals("Burn") && !b.getForm().equals("Chill") && !b.getForm().equals("Copy_2019") && !b.getForm().equals("Costume_2020") && !b.getForm().equals("Defense") && !b.getForm().equals("Douse") && !b.getForm().equals("East_sea") && !b.getForm().equals("Fall_2019") && !b.getForm().equals("Fan") && !b.getForm().equals("Frost") && !b.getForm().equals("Heat") && !b.getForm().equals("Incarnate") && !b.getForm().equals("Mow") && !b.getForm().equals("Overcast") && !b.getForm().equals("Plant") && !b.getForm().equals("Costume2020") && !b.getForm().equals("Rainy") && !b.getForm().equals("Red_striped") && !b.getForm().equals("Sandy") && !b.getForm().equals("Autumn") && !b.getForm().equals("Shock") && !b.getForm().equals("Snowy") && !b.getForm().equals("Speed") && !b.getForm().equals("Spring") && !b.getForm().equals("Standard") && !b.getForm().equals("Summer") && !b.getForm().equals("Sunny") && !b.getForm().equals("Therian") && !b.getForm().equals("Trash") && !b.getForm().equals("Vs_2019") && !b.getForm().equals("Wash") && !b.getForm().equals("West_sea") && !b.getForm().equals("White") && !b.getForm().equals("Winter") && !b.getForm().equals("Zen"));

        List<Pokemon> pokemonList = pokemonRepository.findAll();

        for (Pokemon p : pokemonList) {

            for (BaseStatsResponse b : normalBaseStatsList) {

                if (b.getId() == p.getPokedex() && b.getForm().equals(p.getForm())) {

                    BaseStats baseStats = baseStatsResponseToBaseStats(b);

                    if (p.getBaseStats() == null) {

                        p.setBaseStats(baseStats);
                        pokemonRepository.save(p);
                        log.info("[BS*] UPDATED BASESTATS OF " + p.toString());
                    } else {
                        if (!p.getBaseStats().equals(baseStats)) {
                            //System.out.println(p.toFullString());
                            //System.out.println("b");
                            p.setBaseStats(baseStats);
                            pokemonRepository.save(p);
                            log.info("[BS*] UPDATED BASESTATS OF " + p.toString());
                        }
                    }
                }
            }
        }
    }

    private void updateBaseStatsOfAlolanPokemons () {

        List<BaseStatsResponse> alolanBaseStatsList = new ArrayList<>(baseStatsList);
        alolanBaseStatsList.removeIf(b -> (!b.getForm().equals("Alola")));

        List<AlolanPokemon> alolanPokemonList = alolanPokemonRepository.findAll();

        for (AlolanPokemon p : alolanPokemonList) {

            for (BaseStatsResponse b : alolanBaseStatsList) {

                if (b.getId() == p.getPokedex() && b.getForm().equals(p.getForm())) {

                    BaseStats baseStats = baseStatsResponseToBaseStats(b);

                    if (p.getBaseStats() == null) {
                        p.setBaseStats(baseStats);
                        alolanPokemonRepository.save(p);
                        log.info("[BS*] UPDATED BASESTATS OF " + p.toString());
                    } else {
                        if (!p.getBaseStats().equals(baseStats)) {

                            p.setBaseStats(baseStats);
                            alolanPokemonRepository.save(p);
                            log.info("[BS*] UPDATED BASESTATS OF " + p.toString());
                        }
                    }
                }
            }
        }
    }

    private void updateBaseStatsOfGalarianPokemons() {

        List<BaseStatsResponse> galarianBaseStatsList = new ArrayList<>(baseStatsList);
        galarianBaseStatsList.removeIf(b -> (!b.getForm().equals("Galarian") && !b.getForm().equals("Galarian_standard") && !b.getForm().equals("Galarian_zen")));

        List<GalarianPokemon> galarianPokemonList = galarianPokemonRepository.findAll();

        for (GalarianPokemon p : galarianPokemonList) {

            for (BaseStatsResponse b : galarianBaseStatsList) {

                if (b.getId() == p.getPokedex() && b.getForm().equals(p.getForm())) {

                    BaseStats baseStats = baseStatsResponseToBaseStats(b);
                    //TODO MAYBE SHORTER

                    if (p.getBaseStats() == null) {

                        p.setBaseStats(baseStats);
                        galarianPokemonRepository.save(p);
                        log.info("[BS*] UPDATED BASESTATS OF " + p.toString());
                    } else {
                        if (!p.getBaseStats().equals(baseStats)) {

                            p.setBaseStats(baseStats);
                            galarianPokemonRepository.save(p);
                            log.info("[BS*] UPDATED BASESTATS OF " + p.toString());
                        }
                    }
                }
            }
        }
    }

    private Pokemon pokemonResponseToPokemon(PokemonResponse p) {

        Pokemon pokemon = new Pokemon();
        pokemon.setPokedex(p.getId());
        pokemon.setName(p.getName());
        pokemon.setForm(p.getForm());
        return pokemon;
    }

    private AlolanPokemon PokemonResponseToAlolanPokemon(PokemonResponse a) {

        AlolanPokemon pokemon = new AlolanPokemon();
        pokemon.setPokedex(a.getId());
        pokemon.setName(a.getName());
        return pokemon;
    }

    private GalarianPokemon PokemonResponseToGalarianPokemon(PokemonResponse g) {

        GalarianPokemon pokemon = new GalarianPokemon();
        pokemon.setPokedex(g.getId());
        pokemon.setName(g.getName());
        return pokemon;
    }

    private BaseStats baseStatsResponseToBaseStats(BaseStatsResponse b) {

        BaseStats baseStats = new BaseStats(b.getBaseAttack() ,b.getBaseDefense(), b.getBaseStamina());

        return baseStats;
    }

}
