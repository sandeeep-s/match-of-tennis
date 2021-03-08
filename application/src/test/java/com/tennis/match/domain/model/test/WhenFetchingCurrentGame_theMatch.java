package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.Match;
import com.tennis.match.domain.model.MatchId;
import com.tennis.match.domain.model.Player;
import com.tennis.match.domain.model.TennisMatchSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class WhenFetchingCurrentGame_theMatch {

    private Match matchUnderTest;
    @Mock
    private TennisMatchSet mockSet;

    @BeforeEach
    void setUp() {
        openMocks(this);
        matchUnderTest = new TestableMatch();
    }

    private class TestableMatch extends Match{
        public TestableMatch() {
            super(MatchId.from(1), null, null, 3);
        }

        @Override
        public TennisMatchSet currentSet() {
            return mockSet;
        }
    }

    @Test
    void shouldAskSetInProgressForCurrentGame(){

        matchUnderTest.currentGame();

        verify(mockSet, times(1)).currentGame();

    }
}
