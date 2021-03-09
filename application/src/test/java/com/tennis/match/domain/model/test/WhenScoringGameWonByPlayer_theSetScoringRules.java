package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class WhenScoringGameWonByPlayer_theSetScoringRules {

    private SetScoringRules setScoringRulesUnderTest;
    @Mock
    private TennisMatchSet mockTennisMatchSet;

    @BeforeEach
    void setUp() {
        openMocks(this);
        setScoringRulesUnderTest = new SetScoringRules();
    }

    @Test
    void shouldAskSetToAddGameToGamesAlreadyWonByThePlayer(){

        PlayerNumber playerNumber = PLAYER_ONE;
        Game gameWon = Game.from(GameId.from(1), mockTennisMatchSet);
        gameWon.scorePointFor(playerNumber);
        gameWon.scorePointFor(playerNumber);
        gameWon.scorePointFor(playerNumber);
        gameWon.scorePointFor(playerNumber);

        setScoringRulesUnderTest.scoreGameFor(playerNumber, gameWon);

        verify(mockTennisMatchSet, times(1)).scoreGameWonBy(playerNumber, gameWon);
    }

    @Test
    void shouldAwardSetToPlayer_given_playerReachesSetScoreOfSix_and_oppositePlayerHasSetScoreOfFourOrLower(){

        PlayerNumber playerNumber = PLAYER_ONE;
        TennisMatchSet tennisMatchSet = TennisMatchSet.from(SetId.from(1));
        tennisMatchSet.startNewGame();
        winCurrentGame(playerNumber, tennisMatchSet);
        winCurrentGame(playerNumber, tennisMatchSet);
        winCurrentGame(playerNumber, tennisMatchSet);
        winCurrentGame(playerNumber, tennisMatchSet);
        winCurrentGame(playerNumber, tennisMatchSet);
        Game game = tennisMatchSet.currentGame();
        game.scorePointFor(playerNumber);
        game.scorePointFor(playerNumber);
        game.scorePointFor(playerNumber);

        setScoringRulesUnderTest.scoreGameFor(playerNumber, game);

        assertThat(tennisMatchSet.winner()).isEqualTo(playerNumber);

    }

    private void winCurrentGame(PlayerNumber playerNumber, TennisMatchSet tennisMatchSet) {
        Game game = tennisMatchSet.currentGame();
        game.scorePointFor(playerNumber);
        game.scorePointFor(playerNumber);
        game.scorePointFor(playerNumber);
        game.scorePointFor(playerNumber);
    }


}
