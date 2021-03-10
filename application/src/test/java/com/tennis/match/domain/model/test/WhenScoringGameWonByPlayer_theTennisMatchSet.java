package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static com.tennis.match.domain.model.PlayerNumber.PLAYER_TWO;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class WhenScoringGameWonByPlayer_theTennisMatchSet {

    private TennisMatchSet setUnderTest;
    @Mock
    private Match mockMatch;
    @Mock
    private SetScoringRules mockSetScoringRules;

    @BeforeEach
    void setUp() {
        openMocks(this);
        setUnderTest = TennisMatchSet.from(SetId.from(1), mockMatch);
    }

    private class TestableTennisMatchSet extends TennisMatchSet {
        public TestableTennisMatchSet(SetId setId) {
            super(setId, mockMatch);
            setScoringRules(mockSetScoringRules);
        }
    }

    @Test
    void shouldAskSetScoringRulesToScoreWonGame(){

        PlayerNumber playerNumber = PLAYER_ONE;
        Game gameWon = Game.from(GameId.from(1), TennisMatchSet.from(SetId.from(1), mockMatch));
        gameWon.scorePointFor(playerNumber);
        gameWon.scorePointFor(playerNumber);
        gameWon.scorePointFor(playerNumber);
        gameWon.scorePointFor(playerNumber);

        setUnderTest = new TestableTennisMatchSet(SetId.from(1));

//        setUnderTest.scoreGameWonBy(playerNumber, gameWon);

//        verify(mockSetScoringRules, times(1)).scoreGameFor(playerNumber, gameWon);
    }

    @Test
    void shouldStartNewGame(){

        PlayerNumber playerNumber = PLAYER_ONE;
        setUnderTest = TennisMatchSet.from(SetId.from(1), mockMatch);
        Game game = setUnderTest.currentGame();
        game.scorePointFor(playerNumber);
        game.scorePointFor(playerNumber);
        game.scorePointFor(playerNumber);
        assertThat(setUnderTest.currentGame().gameId().value()).isEqualTo(1);
        assertThat(setUnderTest.games().size()).isEqualTo(1);

//        setUnderTest.scoreGameWonBy(playerNumber, game);

        assertThat(setUnderTest.currentGame().gameId().value()).isEqualTo(2);
    }

    @Test
    void shouldStartTieBreaker_given_bothPlayerReachesSetScoreOfSix(){

        PlayerNumber playerNumberOne = PLAYER_ONE;
        PlayerNumber playerNumberTwo = PLAYER_TWO;

        setUnderTest = TennisMatchSet.from(SetId.from(1), mockMatch);
        for (int i = 1; i <= 5; i++){
            winCurrentGame(playerNumberOne, setUnderTest);
        }
        for (int i = 1; i <= 6; i++){
            winCurrentGame(playerNumberTwo, setUnderTest);
        }
        Game game = setUnderTest.currentGame();
        game.scorePointFor(playerNumberOne);
        game.scorePointFor(playerNumberOne);
        game.scorePointFor(playerNumberOne);

//        setUnderTest.scoreGameWonBy(playerNumberOne, game);

        assertThat(setUnderTest.currentGame()).isInstanceOf(TieBreaker.class);
    }

    private void winCurrentGame(PlayerNumber playerNumberOne, TennisMatchSet tennisMatchSet) {
        Game game = tennisMatchSet.currentGame();
        game.scorePointFor(playerNumberOne);
        game.scorePointFor(playerNumberOne);
        game.scorePointFor(playerNumberOne);
        game.scorePointFor(playerNumberOne);
    }


}
