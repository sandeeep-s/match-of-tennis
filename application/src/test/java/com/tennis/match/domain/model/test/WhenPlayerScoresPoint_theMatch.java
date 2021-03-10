package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static com.tennis.match.domain.model.Match.NoOfSets.THREE;
import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class WhenPlayerScoresPoint_theMatch {

    private Match matchUnderTest;
    @Mock
    private TennisMatchSet mockTennisMatchSet;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    private class TestableMatch extends Match{

        public TestableMatch(MatchId matchId, Player playerOne, Player playerTwo, NoOfSets numberOfSets) {
            super(matchId, playerOne, playerTwo, numberOfSets);
        }

        @Override
        public TennisMatchSet currentSet() {
            return mockTennisMatchSet;
        }
    }

    @Test
    void shouldAskCurrentSetToScorePoint_given_matchIsInProgress() {

        MatchId matchId = MatchId.from(1);
        Player sampras = Player.from(PLAYER_ONE, "Sampras");
        Player agassi = Player.from(PLAYER_ONE, "Agassi");
        matchUnderTest = new TestableMatch(matchId, sampras, agassi, THREE){
            @Override
            public MatchStatus status() {
                return MatchStatus.IN_PROGRESS;
            }
        };

        matchUnderTest.scorePoint(PLAYER_ONE);

        verify(mockTennisMatchSet, times(1)).scorePointFor(PLAYER_ONE);
    }

    @Test
    void shouldThrowException_given_matchIsAlreadyCompleted() {

        MatchId matchId = MatchId.from(1);
        Player sampras = Player.from(PLAYER_ONE, "Sampras");
        Player agassi = Player.from(PLAYER_ONE, "Agassi");
        matchUnderTest = new TestableMatch(matchId, sampras, agassi, THREE){
            @Override
            public MatchStatus status() {
                return MatchStatus.COMPLETED;
            }
        };

        Throwable thrown = catchThrowable(() -> matchUnderTest.scorePoint(PLAYER_ONE));

        assertThat(thrown).isInstanceOf(IllegalStateException.class);
    }

}
