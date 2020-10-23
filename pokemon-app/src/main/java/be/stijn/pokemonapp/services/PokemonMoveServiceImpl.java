package be.stijn.pokemonapp.services;

import be.stijn.pokemonapp.apis.PogoApi;
import be.stijn.pokemonapp.entities.ChargedMove;
import be.stijn.pokemonapp.entities.FastMove;
import be.stijn.pokemonapp.pojo.pogoApi.ChargedMoveResponse;
import be.stijn.pokemonapp.pojo.pogoApi.FastMoveResponse;
import be.stijn.pokemonapp.repositories.ChargedMoveRepository;
import be.stijn.pokemonapp.repositories.FastMoveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PokemonMoveServiceImpl implements PokemonMoveService {

    @Autowired
    PogoApi pogoApi;

    @Autowired
    FastMoveRepository fastMoveRepository;

    @Autowired
    ChargedMoveRepository chargedMoveRepository;

    @Override
    public List<FastMove> updateFastMoves() {

        List<FastMoveResponse> updatedFastMoveList = pogoApi.getFastMoves();
        List<FastMove> currentFastMoveList = fastMoveRepository.findAll();
        List<FastMove> newFastMoveList = new ArrayList<>();

        for (FastMoveResponse f : updatedFastMoveList) {

            FastMove fastMove = fastMoveResponseToFastMove(f);

            if (!currentFastMoveList.contains(fastMove)) {

                if (fastMoveRepository.findById(f.getMoveId()).isPresent()) {

                    log.info("[FM*] UPDATED OLD FASTMOVE " + fastMove.toString());
                }
                else {
                    log.info("[FM+] ADDED NEW FASTMOVE " + fastMove.toString() );
                }
                newFastMoveList.add(fastMove);
            }
        }

        log.info("[" + newFastMoveList.size() +"] NEW FASTMOVE(S) UPDATED IN TOTAL");

        return fastMoveRepository.saveAll(newFastMoveList);
    }

    @Override
    public List<ChargedMove> updateChargedMoves() {

        List<ChargedMoveResponse> updatedChargedMoveList = pogoApi.getChargedMoves();
        List<ChargedMove> currentChargedMoveList = chargedMoveRepository.findAll();
        List<ChargedMove> newChargedMoveList = new ArrayList<>();

        for (ChargedMoveResponse f : updatedChargedMoveList) {

            ChargedMove chargedMove = chargedMoveResponseToChargedMove(f);

            if (!currentChargedMoveList.contains(chargedMove)) {

                if (chargedMoveRepository.findById(f.getMoveId()).isPresent()) {

                    log.info("[CM*] UPDATED OLD CHARGEDMOVE " + chargedMove.toString());
                }
                else {
                    log.info("[CM+] ADDED NEW CHARGEDMOVE " + chargedMove.toString() );
                }
                newChargedMoveList.add(chargedMove);
            }
        }

        log.info("[" + newChargedMoveList.size() +"] NEW CHARGEDMOVE(S) UPDATED IN TOTAL");

        return chargedMoveRepository.saveAll(newChargedMoveList);
    }

    private FastMove fastMoveResponseToFastMove(FastMoveResponse f) {

        FastMove fastMove = new FastMove();
        fastMove.setId(f.getMoveId());
        fastMove.setName(f.getName());
        fastMove.setType(f.getType());
        fastMove.setDuration(f.getDuration());
        fastMove.setEnergyDelta(f.getEnergyDelta());
        fastMove.setPower(f.getPower());
        return fastMove;
    }

    private ChargedMove chargedMoveResponseToChargedMove(ChargedMoveResponse c) {

        ChargedMove chargedMove = new ChargedMove();
        chargedMove.setId(c.getMoveId());
        chargedMove.setName(c.getName());
        chargedMove.setType(c.getType());
        chargedMove.setDuration(c.getDuration());
        chargedMove.setEnergyDelta(c.getEnergyDelta());
        chargedMove.setPower(c.getPower());

        return chargedMove;
    }
}
