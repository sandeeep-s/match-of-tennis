package com.tennis.match.test;

import com.tennis.match.application.MatchApplicationService;
import com.tennis.match.domain.model.*;
import com.tennis.match.port.adapters.api.MatchScoreManagerApi;
import com.tennis.match.port.adapters.services.InMemoryMatchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.tennis.match.domain.model.Match.NoOfSets.THREE;
import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MatchScoreManagementIT {

    private MatchScoreManagerApi matchScoreManagerAPI;
    private MatchRepository matchRepository;
    private MatchApplicationService matchApplicationService;
    private Map<MatchId, Match> matchStore;

    @BeforeEach
    void setUp() {
        matchStore = new HashMap<>();
        matchRepository = new InMemoryMatchRepository(matchStore);
        matchApplicationService = new MatchApplicationService(matchRepository);
        matchScoreManagerAPI = new MatchScoreManagerApi(matchApplicationService);
    }

    @Test
    void shouldStartMatch_and_storeItInRepository(){

        Player sampras = Player.from(PLAYER_ONE, "Sampras");
        Player agassi = Player.from(PLAYER_ONE, "Agassi");

        Match match = matchScoreManagerAPI.startMatch(sampras, agassi, THREE);

        assertThat(match.matchId().value()).isEqualTo(1);
        assertThat(match.sets().size()).isEqualTo(3);
        assertThat(match.playerOne().name()).isEqualTo("Sampras");
        assertThat(match.playerTwo().name()).isEqualTo("Agassi");
        assertThat(match.playerOne().playerNumber()).isEqualTo(PLAYER_ONE);
        assertThat(match.playerTwo().playerNumber()).isEqualTo(PLAYER_ONE);

        assertThat(matchStore.size()).isEqualTo(1);
    }

}
