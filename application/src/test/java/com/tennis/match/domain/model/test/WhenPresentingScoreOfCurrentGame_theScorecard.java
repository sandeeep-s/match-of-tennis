package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tennis.match.domain.model.Match.NoOfSets.THREE;
import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static com.tennis.match.domain.model.PlayerNumber.PLAYER_TWO;
import static com.tennis.match.domain.model.Points.FIFTEEN;
import static com.tennis.match.domain.model.Points.LOVE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WhenPresentingScoreOfCurrentGame_theScorecard {

    private ScoreCard scoreCardUnderTest;
    private Match match;

    @BeforeEach
    void setUp() {
        Player sampras = Player.from(PLAYER_ONE, "Sampras");
        Player agassi = Player.from(PLAYER_TWO, "Agassi");
        MatchId matchId = MatchId.from(1);
        match = Match.from(matchId, sampras, agassi, THREE);

        scoreCardUnderTest = new ScoreCard(match);
    }

    @Test
    void shouldReflectScoreAsPerPointsScoredByPlayer_(){

        PlayerNumber playerNumber = PlayerNumber.PLAYER_ONE;
        match.currentGame().scorePointFor(playerNumber);

        scoreCardUnderTest.currentGameScoreOf(playerNumber);

        assertThat(match.scoreCard().currentGameScoreOf(playerNumber)).isEqualTo(FIFTEEN);
        assertThat(match.scoreCard().currentGameScoreOf(playerNumber.opponent())).isEqualTo(LOVE);
    }



}
