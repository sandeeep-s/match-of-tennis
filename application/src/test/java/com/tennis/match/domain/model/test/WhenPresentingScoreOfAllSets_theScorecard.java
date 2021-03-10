package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tennis.match.domain.model.Match.NoOfSets.THREE;
import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static com.tennis.match.domain.model.PlayerNumber.PLAYER_TWO;
import static org.assertj.core.api.Assertions.assertThat;

public class WhenPresentingScoreOfAllSets_theScorecard {

    private Match match;

    @BeforeEach
    void setUp() {
        Player sampras = Player.from(PLAYER_ONE, "Sampras");
        Player agassi = Player.from(PLAYER_TWO, "Agassi");
        MatchId matchId = MatchId.from(1);
        match = Match.from(matchId, sampras, agassi, THREE);
    }

    @Test
    void shouldReflectScoreAsPerPointsScoredByPlayer(){

        PlayerNumber playerNumber = PlayerNumber.PLAYER_ONE;
        for (int i = 1; i <= 6; i++){
            winCurrentGame(playerNumber, match.currentSet());
        }
        for (int i = 1; i <= 6; i++){
            winCurrentGame(playerNumber, match.currentSet());
        }
        for (int i = 1; i <= 6; i++){
            winCurrentGame(playerNumber, match.currentSet());
        }

        assertThat(match.scoreCard().scoreForAllSetsOf(playerNumber)).hasSize(3);
        assertThat(match.scoreCard().scoreForAllSetsOf(playerNumber))
                .contains(
                        SetScore.from(SetId.from(1), 6),
                        SetScore.from(SetId.from(2), 6),
                        SetScore.from(SetId.from(3), 6));
        assertThat(match.scoreCard().scoreForAllSetsOf(playerNumber.opponent()))
                .contains(
                        SetScore.from(SetId.from(1), 0),
                        SetScore.from(SetId.from(2), 0),
                        SetScore.from(SetId.from(3), 0));
    }

    private void winCurrentGame(PlayerNumber playerNumberOne, TennisMatchSet tennisMatchSet) {
        Game game = tennisMatchSet.currentGame();
        game.scorePointFor(playerNumberOne);
        game.scorePointFor(playerNumberOne);
        game.scorePointFor(playerNumberOne);
        game.scorePointFor(playerNumberOne);
    }


}
