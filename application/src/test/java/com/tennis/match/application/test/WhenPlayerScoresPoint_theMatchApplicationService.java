package com.tennis.match.application.test;

import com.tennis.match.application.MatchApplicationService;
import com.tennis.match.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class WhenPlayerScoresPoint_theMatchApplicationService {

    private MatchApplicationService matchApplicationServiceUnderTest;
    @Mock
    private MatchRepository mockMatchRepository;
    @Mock
    private Match mockMatch;
    @Mock
    private Game mockGame;

    @BeforeEach
    void setUp() {
        openMocks(this);
        matchApplicationServiceUnderTest = new MatchApplicationService(mockMatchRepository);
    }

    @Test
    void shouldFetchTheMatchFromRepository(){

        MatchId matchId = MatchId.from(1);
        when(mockMatchRepository.matchWith(matchId)).thenReturn(mockMatch);
        when(mockMatch.currentGame()).thenReturn(mockGame);

        matchApplicationServiceUnderTest.scorePoint(matchId, PLAYER_ONE);

        verify(mockMatchRepository, times(1)).matchWith(matchId);
    }

    @Test
    void shouldFetchCurrentGameOfTheMatch(){

        MatchId matchId = MatchId.from(1);
        when(mockMatchRepository.matchWith(matchId)).thenReturn(mockMatch);
        when(mockMatch.currentGame()).thenReturn(mockGame);

        matchApplicationServiceUnderTest.scorePoint(matchId, PLAYER_ONE);

        verify(mockMatch, times(1)).currentGame();
    }

    @Test
    void shouldAskGameToScorePoint(){

        MatchId matchId = MatchId.from(1);
        when(mockMatchRepository.matchWith(matchId)).thenReturn(mockMatch);
        when(mockMatch.currentGame()).thenReturn(mockGame);

        matchApplicationServiceUnderTest.scorePoint(matchId, PLAYER_ONE);

        verify(mockGame, times(1)).scorePoint(PLAYER_ONE);
    }

}
