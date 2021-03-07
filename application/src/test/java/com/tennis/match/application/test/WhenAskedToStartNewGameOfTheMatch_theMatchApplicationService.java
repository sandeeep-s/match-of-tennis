package com.tennis.match.application.test;

import com.tennis.match.application.MatchApplicationService;
import com.tennis.match.domain.model.MatchId;
import com.tennis.match.domain.model.MatchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class WhenAskedToStartNewGameOfTheMatch_theMatchApplicationService {

    private MatchApplicationService matchApplicationServiceUnderTest;
    @Mock
    private MatchRepository mockMatchRepository;

    @BeforeEach
    void setUp() {
        openMocks(this);
        matchApplicationServiceUnderTest = new MatchApplicationService(mockMatchRepository);
    }

    @Test
    void shouldFetchTheMatchFromRepository(){

        MatchId matchId = MatchId.from(1);

        matchApplicationServiceUnderTest.startNewGameOfMatchWith(matchId);

        verify(mockMatchRepository, times(1)).matchWith(matchId);
    }
}
