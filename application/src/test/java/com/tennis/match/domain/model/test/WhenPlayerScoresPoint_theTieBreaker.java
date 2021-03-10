package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.GameId;
import com.tennis.match.domain.model.PlayerNumber;
import com.tennis.match.domain.model.TennisMatchSet;
import com.tennis.match.domain.model.TieBreaker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class WhenPlayerScoresPoint_theTieBreaker {

    @Mock
    TennisMatchSet mockTennisMatchSet;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void shouldAwardSetToPlayer_given_playerScoreIsAtLeastSix_and_hasTwoPointsMoreThanOpponent() {

        PlayerNumber player = PLAYER_ONE;
        TieBreaker tieBreaker = TieBreaker.from(GameId.from(1), mockTennisMatchSet);

        tieBreaker.scorePointFor(player);
        tieBreaker.scorePointFor(player);
        tieBreaker.scorePointFor(player);
        tieBreaker.scorePointFor(player);
        tieBreaker.scorePointFor(player);
        tieBreaker.scorePointFor(player);

        verify(mockTennisMatchSet, times(1)).awardSetTo(player);
    }

}
