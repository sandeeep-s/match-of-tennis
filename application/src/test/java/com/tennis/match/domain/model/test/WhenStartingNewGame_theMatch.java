package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.Match;
import com.tennis.match.domain.model.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class WhenStartingNewGame_theMatch{

    private Match matchUnderTest;
    @Mock
    private Set mockSet;

    @BeforeEach
    void setUp() {
        openMocks(this);
        matchUnderTest = Match.builder().setInProgress(mockSet).build();
    }

    @Test
    void shouldAskSetInProgressToStartNewGame(){

        matchUnderTest.startNewGame();

        verify(mockSet, times(1)).startNewGame();

    }
}
