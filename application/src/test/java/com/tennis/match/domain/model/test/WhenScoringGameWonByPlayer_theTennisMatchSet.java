package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class WhenScoringGameWonByPlayer_theTennisMatchSet {

    private TennisMatchSet setUnderTest;
    @Mock
    private SetScoringRules mockSetScoringRules;

    @BeforeEach
    void setUp() {
        openMocks(this);
        setUnderTest = TennisMatchSet.from(SetId.from(1));
    }

    private class TestableTennisMatchSet extends TennisMatchSet {
        public TestableTennisMatchSet(SetId setId) {
            super(setId);
            setScoringRules(mockSetScoringRules);
        }
    }

    @Test
    void shouldAskSetScoringRulesToScoreWonGame(){

        PlayerNumber playerNumber = PLAYER_ONE;
        Game gameWon = Game.from(GameId.from(1), TennisMatchSet.from(SetId.from(1)));
        gameWon.scorePointFor(playerNumber);
        gameWon.scorePointFor(playerNumber);
        gameWon.scorePointFor(playerNumber);
        gameWon.scorePointFor(playerNumber);

        setUnderTest = new TestableTennisMatchSet(SetId.from(1));

        setUnderTest.scoreGameWonBy(playerNumber, gameWon);

        verify(mockSetScoringRules, times(1)).scoreGameFor(playerNumber, gameWon);
    }

    @Test
    void shouldStartNewGame(){

        PlayerNumber playerNumber = PLAYER_ONE;
        setUnderTest = TennisMatchSet.from(SetId.from(1));
        setUnderTest.startNewGame();
        Game game = setUnderTest.currentGame();
        game.scorePointFor(playerNumber);
        game.scorePointFor(playerNumber);
        game.scorePointFor(playerNumber);
        assertThat(setUnderTest.currentGame().gameId().value()).isEqualTo(1);
        assertThat(setUnderTest.games().size()).isEqualTo(1);

        setUnderTest.scoreGameWonBy(playerNumber, game);

        assertThat(setUnderTest.currentGame().gameId().value()).isEqualTo(2);

    }


}
