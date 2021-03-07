package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class WhenPlayerWinsPoint_theGame {

    @Test
    void shouldChangeScoreToFifteen_given_currentScoreWasZero() {
        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.builder().build();

        Game game = Game.from(gameId, set);
        game.start();
        assertThat(game.playerOneScore().value()).isEqualTo("0");

        game.playerOneWinsPoint();

        assertThat(game.playerOneScore().value()).isEqualTo("15");
    }

    @Test
    void shouldChangeScoreToThirty_given_currentScoreWasFifteen() {
        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.builder().build();
        Game game = Game.from(gameId, set);
        game.start();
        game.playerOneWinsPoint();
        assertThat(game.playerOneScore().value()).isEqualTo("15");

        game.playerOneWinsPoint();

        assertThat(game.playerOneScore().value()).isEqualTo("30");
    }

    @Test
    void shouldChangeScoreToForty_given_currentScoreWasThirty() {
        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.builder().build();
        Game game = Game.from(gameId, set);
        game.start();
        game.playerOneWinsPoint();
        game.playerOneWinsPoint();
        assertThat(game.playerOneScore().value()).isEqualTo("30");

        game.playerOneWinsPoint();

        assertThat(game.playerOneScore().value()).isEqualTo("40");
    }

    @Test
    void shouldGame_given_currentScoreWasForty_and_deuceRuleWasNotActivated() {
        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.builder().build();
        Game game = Game.from(gameId, set);
        game.start();
        game.playerOneWinsPoint();
        game.playerOneWinsPoint();
        game.playerOneWinsPoint();
        assertThat(game.playerOneScore().value()).isEqualTo("40");

        game.playerOneWinsPoint();

        assertThat(game.playerOneScore().value()).isEqualTo("Game");
    }

    @Test
    void shouldActivateDeuceRule_given_bothPlayersReachScoreForty() {
        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.builder().build();
        Game game = Game.from(gameId, set);
        game.start();
        assertThat(game.gameRules()).isInstanceOf(SimpleGameRules.class);

        game.playerOneWinsPoint();
        game.playerTwoWinsPoint();
        game.playerOneWinsPoint();
        game.playerTwoWinsPoint();
        game.playerOneWinsPoint();
        game.playerTwoWinsPoint();

        assertThat(game.playerOneScore().value()).isEqualTo("40");
        assertThat(game.playerTwoScore().value()).isEqualTo("40");
        assertThat(game.gameRules()).isInstanceOf(DeuceGameRules.class);
    }



    @Test
    void shouldTakeAdvantage_given_currentScoreWasForty_and_deuceRuleWasActivated() {
        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.builder().build();
        Game game = Game.from(gameId, set);
        game.start();
        game.playerOneWinsPoint();
        game.playerTwoWinsPoint();
        game.playerOneWinsPoint();
        game.playerTwoWinsPoint();
        game.playerOneWinsPoint();
        game.playerTwoWinsPoint();
        assertThat(game.playerOneScore().value()).isEqualTo("40");

        game.playerOneWinsPoint();

        assertThat(game.playerOneScore().value()).isEqualTo("AD");
    }

    @Test
    void shouldFailWithError_given_gameIsALreadyWon() {
        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.builder().build();
        Game game = Game.from(gameId, set);
        game.start();
        game.playerOneWinsPoint();
        game.playerOneWinsPoint();
        game.playerOneWinsPoint();
        game.playerOneWinsPoint();
        assertThat(game.playerOneScore().value()).isEqualTo("Game");

        Throwable thrown = catchThrowable(() -> game.playerOneWinsPoint());

        assertThat(thrown).isInstanceOf(IllegalStateException.class);
    }

}
