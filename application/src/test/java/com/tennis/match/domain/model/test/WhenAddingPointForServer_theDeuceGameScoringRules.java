package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static com.tennis.match.domain.model.Points.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WhenAddingPointForServer_theDeuceGameScoringRules {

    private DeuceGameScoringRules deuceGameScoringRulesUnderTest;
    private Game game;

    @BeforeEach
    void setUp() {
        deuceGameScoringRulesUnderTest = new DeuceGameScoringRules();
        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.from(SetId.from(1));
        game = Game.from(gameId, set);
        game.scorePoint(PLAYER_ONE);
        game.scorePoint(PLAYER_ONE);
        game.scorePoint(PLAYER_ONE);
        game.scorePoint(PlayerNumber.PLAYER_TWO);
        game.scorePoint(PlayerNumber.PLAYER_TWO);
        game.scorePoint(PlayerNumber.PLAYER_TWO);
        assertThat(game.gameRules()).isInstanceOf(DeuceGameScoringRules.class);
    }

    @Test
    void shouldChangeScoreToAdvantage_given_gameIsInDeuce() {

        assertThat(game.isDeuce()).isTrue();

        deuceGameScoringRulesUnderTest.scorePoint(game, PLAYER_ONE);

        assertThat(game.scores().get(PlayerNumber.PLAYER_ONE).points()).isEqualTo(ADVANTAGE);
    }

    @Test
    void shouldChangeScoreOfServerToGame_given_serverHasAdvantage() {

        game.scorePoint(PLAYER_ONE);
        assertThat(game.scores().get(PlayerNumber.PLAYER_ONE).points()).isEqualTo(ADVANTAGE);
        assertThat(game.scores().get(PlayerNumber.PLAYER_TWO).points()).isEqualTo(FORTY);

        deuceGameScoringRulesUnderTest.scorePoint(game, PLAYER_ONE);

        assertThat(game.scores().get(PlayerNumber.PLAYER_ONE).points()).isEqualTo(GAME);
    }

    @Test
    void shouldChangeScoreOfReceiverToForty_given_receiverHasAdvantage() {

        game.scorePoint(PlayerNumber.PLAYER_TWO);
        assertThat(game.scores().get(PlayerNumber.PLAYER_TWO).points()).isEqualTo(ADVANTAGE);

        deuceGameScoringRulesUnderTest.scorePoint(game, PLAYER_ONE);

        assertThat(game.scores().get(PlayerNumber.PLAYER_TWO).points()).isEqualTo(FORTY);
        assertThat(game.scores().get(PlayerNumber.PLAYER_ONE).points()).isEqualTo(FORTY);
    }


}
