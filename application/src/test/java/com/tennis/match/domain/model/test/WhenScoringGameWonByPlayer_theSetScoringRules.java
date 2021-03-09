package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static com.tennis.match.domain.model.PlayerNumber.PLAYER_TWO;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class WhenScoringGameWonByPlayer_theSetScoringRules {

    private SetScoringRules setScoringRulesUnderTest;
    @Mock
    private TennisMatchSet mockTennisMatchSet;
    @Mock
    private Match mockMatch;

    @BeforeEach
    void setUp() {
        openMocks(this);
        setScoringRulesUnderTest = new SetScoringRules();
    }

    @Test
    void shouldAskSetToAddGameToGamesAlreadyWonByThePlayer(){

        PlayerNumber playerNumberOne = PLAYER_ONE;
        Game gameWon = Game.from(GameId.from(1), mockTennisMatchSet);
        gameWon.scorePointFor(playerNumberOne);
        gameWon.scorePointFor(playerNumberOne);
        gameWon.scorePointFor(playerNumberOne);
        gameWon.scorePointFor(playerNumberOne);

        setScoringRulesUnderTest.scoreGameFor(playerNumberOne, gameWon);

        verify(mockTennisMatchSet, times(1)).scoreGameWonBy(playerNumberOne, gameWon);
    }

    @Test
    void shouldAwardSetToPlayer_given_playerReachesSetScoreOfSix_and_oppositePlayerHasSetScoreOfFourOrLower(){

        PlayerNumber playerNumberOne = PLAYER_ONE;
        TennisMatchSet tennisMatchSet = TennisMatchSet.from(SetId.from(1), mockMatch);
        tennisMatchSet.startNewGame();
        for (int i = 1; i <= 5; i++){
            winCurrentGame(playerNumberOne, tennisMatchSet);
        }
        Game game = tennisMatchSet.currentGame();
        game.scorePointFor(playerNumberOne);
        game.scorePointFor(playerNumberOne);
        game.scorePointFor(playerNumberOne);

        setScoringRulesUnderTest.scoreGameFor(playerNumberOne, game);

        assertThat(tennisMatchSet.winner()).isEqualTo(playerNumberOne);
    }

    @Test
    void shouldStartNewGame_given_playerReachesSetScoreOfSix_and_oppositePlayerHasSetScoreOfFive(){

        PlayerNumber playerNumberOne = PLAYER_ONE;
        PlayerNumber playerNumberTwo = PLAYER_TWO;
        TennisMatchSet tennisMatchSet = TennisMatchSet.from(SetId.from(1), mockMatch);
        tennisMatchSet.startNewGame();
        for (int i = 1; i <= 5; i++){
            winCurrentGame(playerNumberOne, tennisMatchSet);
            winCurrentGame(playerNumberTwo, tennisMatchSet);
        }
        Game game = tennisMatchSet.currentGame();
        game.scorePointFor(playerNumberOne);
        game.scorePointFor(playerNumberOne);
        game.scorePointFor(playerNumberOne);

        setScoringRulesUnderTest.scoreGameFor(playerNumberOne, game);

        assertThat(tennisMatchSet.currentGame().gameId().value()).isEqualTo(12);
        assertThat(tennisMatchSet.winner()).isNull();
    }

    @Test
    void shouldAwardSetToPlayer_given_playerReachesSetScoreOfSeven(){

        PlayerNumber playerNumberOne = PLAYER_ONE;
        PlayerNumber playerNumberTwo = PLAYER_TWO;
        TennisMatchSet tennisMatchSet = TennisMatchSet.from(SetId.from(1), mockMatch);
        for (int i = 1; i <= 5; i++){
            winCurrentGame(playerNumberOne, tennisMatchSet);
            winCurrentGame(playerNumberTwo, tennisMatchSet);
        }

        winCurrentGame(playerNumberOne, tennisMatchSet);
        Game game = tennisMatchSet.currentGame();
        game.scorePointFor(playerNumberOne);
        game.scorePointFor(playerNumberOne);
        game.scorePointFor(playerNumberOne);

        setScoringRulesUnderTest.scoreGameFor(playerNumberOne, game);

        assertThat(tennisMatchSet.winner()).isEqualTo(playerNumberOne);
    }

    private void winCurrentGame(PlayerNumber playerNumberOne, TennisMatchSet tennisMatchSet) {
        Game game = tennisMatchSet.currentGame();
        game.scorePointFor(playerNumberOne);
        game.scorePointFor(playerNumberOne);
        game.scorePointFor(playerNumberOne);
        game.scorePointFor(playerNumberOne);
    }


}
