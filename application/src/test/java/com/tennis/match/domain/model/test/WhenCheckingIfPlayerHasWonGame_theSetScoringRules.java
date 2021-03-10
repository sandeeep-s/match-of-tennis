package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;

import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static com.tennis.match.domain.model.PlayerNumber.PLAYER_TWO;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class WhenCheckingIfPlayerHasWonGame_theSetScoringRules {

    private SetScoringRules setScoringRulesUnderTest;
    @Mock
    private TennisMatchSet mockTennisMatchSet;
    @Mock
    private Game mockGame;

    @BeforeEach
    void setUp() {
        openMocks(this);
        setScoringRulesUnderTest = new SetScoringRules();
    }

    @ParameterizedTest
    @CsvSource({
            "PLAYER_ONE, 6, 0, true",
            "PLAYER_ONE, 6, 1, true",
            "PLAYER_ONE, 6, 2, true",
            "PLAYER_ONE, 6, 3, true",
            "PLAYER_ONE, 6, 4, true",
            "PLAYER_ONE, 6, 5, false",
            "PLAYER_ONE, 7, 5, true",
            "PLAYER_TWO, 6, 0, true",
            "PLAYER_TWO, 6, 1, true",
            "PLAYER_TWO, 6, 2, true",
            "PLAYER_TWO, 6, 3, true",
            "PLAYER_TWO, 6, 4, true",
            "PLAYER_TWO, 6, 5, false",
            "PLAYER_TWO, 7, 5, true"

    })
    void shouldCheckIfPlayerHasWonTheSet(PlayerNumber playerNumber, int playerScore, int opponentScore, boolean expectedValue) {

        when(mockTennisMatchSet.scoreOf(playerNumber)).thenReturn(playerScore);
        when(mockTennisMatchSet.scoreOf(playerNumber.opponent())).thenReturn(opponentScore);

        boolean hasWonSet = setScoringRulesUnderTest.hasWonSet(playerNumber, mockTennisMatchSet);

        assertThat(hasWonSet).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @CsvSource({
            "PLAYER_ONE, GAME, true",
            "PLAYER_TWO, GAME, true",
            "PLAYER_ONE, FORTY, false",
            "PLAYER_TWO, FORTY, false",
            "PLAYER_ONE, ADVANTAGE, false",
            "PLAYER_TWO, ADVANTAGE, false"
    })
    void shouldCheckIfPlayerHasWonTheGame(PlayerNumber playerNumber, Points score, boolean expectedValue) {

        when(mockGame.scoreOf(playerNumber)).thenReturn(score);

        boolean hasWonSet = setScoringRulesUnderTest.hasWonGame(playerNumber, mockGame);

        assertThat(hasWonSet).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @CsvSource({
            "6, 6, true"
    })
    void shouldCheckIfSetIsTied(int playerOneScore, int playerTwoScore, boolean expectedValue) {

        when(mockTennisMatchSet.scoreOf(PLAYER_ONE)).thenReturn(playerOneScore);
        when(mockTennisMatchSet.scoreOf(PLAYER_TWO)).thenReturn(playerTwoScore);

        boolean isSetTied = setScoringRulesUnderTest.isSetTied(mockTennisMatchSet);

        assertThat(isSetTied).isEqualTo(expectedValue);
    }

/*
    @Test
    @CsvSource({"6,0,PLAYER_ONE"})
    void shouldAwardSetToPlayer_given_playerReachesSetScoreOfSix_and_oppositePlayerHasSetScoreOfFourOrLower(int gamesWonByPlayerOne, int gamesWonByPlayerTwo, PlayerNumber expectedWinner) {

        TennisMatchSet tennisMatchSet = TennisMatchSet.from(SetId.from(1), mockMatch);
        tennisMatchSet.startNewGame();
        for (int i = 1; i <= gamesWonByPlayerOne; i++) {
            winCurrentGame(PLAYER_ONE, tennisMatchSet);
        }
        for (int i = 1; i <= gamesWonByPlayerTwo; i++) {
            winCurrentGame(PLAYER_TWO, tennisMatchSet);
        }
        Game game = tennisMatchSet.currentGame();
        game.scorePointFor(PLAYER_ONE);
        game.scorePointFor(PLAYER_ONE);
        game.scorePointFor(PLAYER_ONE);

//        setScoringRulesUnderTest.scoreGameFor(PLAYER_ONE, game);

        assertThat(tennisMatchSet.winner()).isEqualTo(PLAYER_ONE);
    }

    @Test
    void shouldStartNewGame_given_playerReachesSetScoreOfSix_and_oppositePlayerHasSetScoreOfFive() {

        PlayerNumber playerNumberOne = PLAYER_ONE;
        PlayerNumber playerNumberTwo = PLAYER_TWO;
        TennisMatchSet tennisMatchSet = TennisMatchSet.from(SetId.from(1), mockMatch);
        tennisMatchSet.startNewGame();
        for (int i = 1; i <= 5; i++) {
            winCurrentGame(playerNumberOne, tennisMatchSet);
            winCurrentGame(playerNumberTwo, tennisMatchSet);
        }
        Game game = tennisMatchSet.currentGame();
        game.scorePointFor(playerNumberOne);
        game.scorePointFor(playerNumberOne);
        game.scorePointFor(playerNumberOne);

//        setScoringRulesUnderTest.scoreGameFor(playerNumberOne, game);

        assertThat(tennisMatchSet.currentGame().gameId().value()).isEqualTo(12);
        assertThat(tennisMatchSet.winner()).isNull();
    }

    @Test
    void shouldAwardSetToPlayer_given_playerReachesSetScoreOfSeven() {

        PlayerNumber playerNumberOne = PLAYER_ONE;
        PlayerNumber playerNumberTwo = PLAYER_TWO;
        TennisMatchSet tennisMatchSet = TennisMatchSet.from(SetId.from(1), mockMatch);
        for (int i = 1; i <= 5; i++) {
            winCurrentGame(playerNumberOne, tennisMatchSet);
            winCurrentGame(playerNumberTwo, tennisMatchSet);
        }

        winCurrentGame(playerNumberOne, tennisMatchSet);
        Game game = tennisMatchSet.currentGame();
        game.scorePointFor(playerNumberOne);
        game.scorePointFor(playerNumberOne);
        game.scorePointFor(playerNumberOne);

 //       setScoringRulesUnderTest.scoreGameFor(playerNumberOne, game);

        assertThat(tennisMatchSet.winner()).isEqualTo(playerNumberOne);
    }

    private void winCurrentGame(PlayerNumber playerNumberOne, TennisMatchSet tennisMatchSet) {
        Game game = tennisMatchSet.currentGame();
        game.scorePointFor(playerNumberOne);
        game.scorePointFor(playerNumberOne);
        game.scorePointFor(playerNumberOne);
        game.scorePointFor(playerNumberOne);
    }
*/


}
