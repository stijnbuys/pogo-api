package be.stijn.pokemonapp.services;

import be.stijn.pokemonapp.apis.PogoApi;
import be.stijn.pokemonapp.entities.PogoApiHash;
import be.stijn.pokemonapp.entities.Pokemon;
import be.stijn.pokemonapp.pojo.pogoApi.PogoApiHashResponse;
import be.stijn.pokemonapp.pojo.pogoApi.PokemonResponse;
import be.stijn.pokemonapp.repositories.PogoApiHashRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class HashServiceImpl implements HashService {

    @Autowired
    PogoApiHashRepository pogoApiHashRepository;

    @Autowired
    PogoApi pogoApi;

    @Override
    public List<PogoApiHash> getNewHashes() {
        List<PogoApiHashResponse> updatedHashList = pogoApi.getHashList();
        List<PogoApiHash> currentHashList = pogoApiHashRepository.findAll();
        List<PogoApiHash> newHashList = new ArrayList<>();

        updatedHashList = filterHashList(updatedHashList);

        for (PogoApiHashResponse h : updatedHashList) {

            PogoApiHash hash = hashResponseTohash(h);

            if (!currentHashList.contains(hash)) {
                newHashList.add(hash);
            }
        }
        log.info("[" + newHashList.size() +"] UPDATES FROM POGO API");

        return newHashList;
    }

    @Override
    public PogoApiHash updateHash(PogoApiHash hash) {

        log.info("[HA*] UPDATED HASH " + hash.toString());

        return pogoApiHashRepository.save(hash);
    }

    private PogoApiHash hashResponseTohash(PogoApiHashResponse h) {

        PogoApiHash hash = new PogoApiHash();
        hash.setApiFilename(h.getApiFilename());
        hash.setHash(h.getHash());
        return hash;
    }

    private List<PogoApiHashResponse> filterHashList(List<PogoApiHashResponse> hashlist) {

        //TODO REMOVE FILTER OR REFACTOR
        hashlist.removeIf(h -> !h.getApiFilename().equals("released_pokemon.json") && !h.getApiFilename().equals("fast_moves.json") && !h.getApiFilename().equals("charged_moves.json") && !h.getApiFilename().equals("alolan_pokemon.json") && !h.getApiFilename().equals("galarian_pokemon.json") && !h.getApiFilename().equals("pokemon_stats.json") );
        return hashlist;
    }

}
