package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.Game;
import com.tennis.match.domain.model.GameId;
import com.tennis.match.domain.model.Player;
import com.tennis.match.domain.model.TennisMatchSet;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class WhenPlayerWinsPoint_theGame {

    @Test
    void shouldChangeScoreToFifteen_given_currentScoreWasZero(){
        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.builder().build();

        Game game = Game.from(gameId, set);
        game.start();
        assertThat(game.playerOneScore().value()).isEqualTo("0");

        game.playerOneWinsPoint();

        assertThat(game.playerOneScore().value()).isEqualTo("15");
    }

    @Test
    void shouldChangeScoreToThirty_given_currentScoreWasFifteen(){
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
    void shouldChangeScoreToForty_given_currentScoreWasThirty(){
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
    void shouldWinGame_given_currentScoreWasForty(){
        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.builder().build();
        Game game = Game.from(gameId, set);
        game.start();
        game.playerOneWinsPoint();
        game.playerOneWinsPoint();
        game.playerOneWinsPoint();
        assertThat(game.playerOneScore().value()).isEqualTo("40");

        game.playerOneWinsPoint();

        assertThat(game.playerOneScore().value()).isEqualTo("WinGame");
    }

    @Test
    void shouldFailWithError_given_gameIsALreadyWon(){
        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.builder().build();
        Game game = Game.from(gameId, set);
        game.start();
        game.playerOneWinsPoint();
        game.playerOneWinsPoint();
        game.playerOneWinsPoint();
        game.playerOneWinsPoint();
        assertThat(game.playerOneScore().value()).isEqualTo("WinGame");

        Throwable thrown = catchThrowable(() -> game.playerOneWinsPoint());

        assertThat(thrown).isInstanceOf(IllegalStateException.class);
    }

}
