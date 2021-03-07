package com.tennis.match.application.test;

import com.tennis.match.application.MatchApplicationService;
import com.tennis.match.domain.model.Game;
import com.tennis.match.domain.model.Match;
import com.tennis.match.domain.model.MatchId;
import com.tennis.match.domain.model.MatchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class WhenAddingPointForServerInCurrentGame_theMatchApplicationService {

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

        matchApplicationServiceUnderTest.changeScoreOfCurrentGame(matchId);

        verify(mockMatchRepository, times(1)).matchWith(matchId);
    }

    @Test
    void shouldFetchCurrentGameOfTheMatch(){

        MatchId matchId = MatchId.from(1);
        when(mockMatchRepository.matchWith(matchId)).thenReturn(mockMatch);

        matchApplicationServiceUnderTest.changeScoreOfCurrentGame(matchId);

        verify(mockMatch, times(1)).currentGame();
    }

    @Test
    void shouldAskGameToAddPointForServer(){

        MatchId matchId = MatchId.from(1);
        when(mockMatchRepository.matchWith(matchId)).thenReturn(mockMatch);
        when(mockMatch.currentGame()).thenReturn(mockGame);

        matchApplicationServiceUnderTest.changeScoreOfCurrentGame(matchId);

        verify(mockGame, times(1)).addPointForServer();
    }

}
