package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.*;

import static com.tennis.match.domain.model.Match.NoOfSets.THREE;
import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class WhenPlayerScoresPoint_theTennisMatchSet {

    private TennisMatchSet setUnderTest;
    @Mock
    private Game mockGame;
    @Mock
    private Match mockMatch;
    @Mock
    private SetScoringRules mockSetScoringRules;
    @Captor
    private ArgumentCaptor<PlayerNumber> playerNumberArgumentCaptor;

    @BeforeEach
    void setUp() {
        openMocks(this);
        setUnderTest = TennisMatchSet.from(SetId.from(1), mockMatch);
    }

    private class TestableTennisMatchSet extends TennisMatchSet {

        public TestableTennisMatchSet(SetId setId, Game game) {
            super(setId, mockMatch);
            setCurrentGame(game);
        }

        public TestableTennisMatchSet(SetId setId) {
            super(setId, mockMatch);
            setScoringRules(mockSetScoringRules);
        }

    }

    @ParameterizedTest
    @EnumSource(PlayerNumber.class)
    void shouldAskCurrentGameToScorePoint(PlayerNumber playerNumber) {

        setUnderTest = new TestableTennisMatchSet(SetId.from(1));

        setUnderTest.scorePointFor(playerNumber);

        verify(mockGame, times(1)).scorePointFor(playerNumber);
    }

    @ParameterizedTest
    @EnumSource(PlayerNumber.class)
    void shouldAddGameToPlayerScore_given_playerWinsTheGame(PlayerNumber playerNumber) {

        setUnderTest = new TestableTennisMatchSet(SetId.from(1), mockGame);
        when(mockSetScoringRules.hasWonGame(playerNumber, mockGame)).thenReturn(true);

        setUnderTest.scorePointFor(playerNumber);

        assertThat(setUnderTest.scoreOf(playerNumber)).isEqualTo(1);
    }

    @ParameterizedTest
    @EnumSource(PlayerNumber.class)
    void shouldAwardSetToPlayer_given_setWasWonByPlayer(PlayerNumber playerNumber) {

        setUnderTest = new TestableTennisMatchSet(SetId.from(1), mockGame);
        when(mockSetScoringRules.hasWonGame(playerNumber, mockGame)).thenReturn(true);
        when(mockSetScoringRules.hasWonSet(playerNumber, setUnderTest)).thenReturn(true);

        setUnderTest.scorePointFor(playerNumber);

        assertThat(setUnderTest.winner()).isEqualTo(playerNumber);
    }

    @ParameterizedTest
    @EnumSource(PlayerNumber.class)
    void shouldStartTieBreaker_given_setIsTied(PlayerNumber playerNumber) {

        setUnderTest = new TestableTennisMatchSet(SetId.from(1));

        when(mockSetScoringRules.hasWonGame(playerNumberArgumentCaptor.capture(), any(Game.class))).thenReturn(true);
        when(mockSetScoringRules.hasWonSet(playerNumber, setUnderTest)).thenReturn(false);
        when(mockSetScoringRules.isSetTied(setUnderTest)).thenReturn(true);

        setUnderTest.scorePointFor(playerNumber);

        assertThat(setUnderTest.currentGame()).isInstanceOf(TieBreaker.class);
    }

    @ParameterizedTest
    @EnumSource(PlayerNumber.class)
    void shouldStartNewGame_given_setIsNotTied_and_playerHasNotWonTheSet(PlayerNumber playerNumber) {

        setUnderTest = new TestableTennisMatchSet(SetId.from(1));

        when(mockSetScoringRules.hasWonGame(playerNumberArgumentCaptor.capture(), any(Game.class))).thenReturn(true);
        when(mockSetScoringRules.hasWonSet(playerNumber, setUnderTest)).thenReturn(false);
        when(mockSetScoringRules.isSetTied(setUnderTest)).thenReturn(false);

        setUnderTest.scorePointFor(playerNumber);

        assertThat(setUnderTest.currentGame()).isInstanceOf(Game.class);
    }


}
