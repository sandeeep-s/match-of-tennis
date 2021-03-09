package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static com.tennis.match.domain.model.PlayerNumber.PLAYER_TWO;
import static com.tennis.match.domain.model.Points.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WhenPlayerScoresPoint_theDeuceGameScoringRules {

    private DeuceGameScoringRules deuceGameScoringRulesUnderTest;
    private Game game;

    @BeforeEach
    void setUp() {
        deuceGameScoringRulesUnderTest = new DeuceGameScoringRules();
        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.from(SetId.from(1));
        game = Game.from(gameId, set);
        game.scorePointFor(PLAYER_ONE);
        game.scorePointFor(PLAYER_ONE);
        game.scorePointFor(PLAYER_ONE);
        game.scorePointFor(PlayerNumber.PLAYER_TWO);
        game.scorePointFor(PlayerNumber.PLAYER_TWO);
        game.scorePointFor(PlayerNumber.PLAYER_TWO);
        assertThat(game.gameRules()).isInstanceOf(DeuceGameScoringRules.class);
    }

    @Test
    void shouldChangeScoreToAdvantage_given_gameIsInDeuce() {

        assertThat(game.isDeuce()).isTrue();

        deuceGameScoringRulesUnderTest.scorePoint(game, PLAYER_ONE);

        assertThat(game.scoreOf(PLAYER_ONE)).isEqualTo(ADVANTAGE);
    }

    @Test
    void shouldChangeScoreOfServerToGame_given_serverHasAdvantage() {

        assertThat(game.isDeuce()).isTrue();
        game.scorePointFor(PLAYER_ONE);
        assertThat(game.scoreOf(PLAYER_ONE)).isEqualTo(ADVANTAGE);
        assertThat(game.scoreOf(PLAYER_TWO)).isEqualTo(FORTY);

        deuceGameScoringRulesUnderTest.scorePoint(game, PLAYER_ONE);

        assertThat(game.scoreOf(PLAYER_ONE)).isEqualTo(GAME);
    }

    @Test
    void shouldChangeScoreOfReceiverToForty_given_receiverHasAdvantage() {

        assertThat(game.isDeuce()).isTrue();
        game.scorePointFor(PlayerNumber.PLAYER_TWO);
        assertThat(game.scoreOf(PLAYER_TWO)).isEqualTo(ADVANTAGE);

        deuceGameScoringRulesUnderTest.scorePoint(game, PLAYER_ONE);

        assertThat(game.scoreOf(PLAYER_TWO)).isEqualTo(FORTY);
        assertThat(game.scoreOf(PLAYER_ONE)).isEqualTo(FORTY);
    }

    @Test
    void shouldUpdateSetScore_given_playerWinsTheGame() {

        assertThat(game.isDeuce()).isTrue();
        game.scorePointFor(PlayerNumber.PLAYER_TWO);
        assertThat(game.scoreOf(PLAYER_TWO)).isEqualTo(ADVANTAGE);

        deuceGameScoringRulesUnderTest.scorePoint(game, PLAYER_TWO);

        assertThat(game.scoreOf(PLAYER_TWO)).isEqualTo(GAME);
        assertThat(game.partOfSet().scoreOf(PLAYER_TWO)).isEqualTo(1);
    }


}
