package com.tennis.match.application.test;

import com.tennis.match.application.MatchApplicationService;
import com.tennis.match.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static com.tennis.match.domain.model.PlayerNumber.PLAYER_TWO;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class WhenStartingNewMatch_theMatchApplicationService {

    private MatchApplicationService matchApplicationServiceUnderTest;
    @Mock
    private MatchRepository mockMatchRepository;
    @Mock
    private Match mockMatch;
    @Mock
    private Game mockGame;
    @Captor
    private ArgumentCaptor<MatchId> matchIdArgumentCaptor;
    @Captor
    private ArgumentCaptor<Player> playerOneArgumentCaptor;
    @Captor
    private ArgumentCaptor<Player> playerTwoArgumentCaptor;
    @Captor
    private ArgumentCaptor<Integer> noOfSetsArgumentCaptor;
    @Captor
    private ArgumentCaptor<Match> matchArgumentCaptor;

    @BeforeEach
    void setUp() {
        openMocks(this);
        matchApplicationServiceUnderTest = new MatchApplicationService(mockMatchRepository);
    }

    @Test
    void shouldCreateNewMatch() {

        MatchId matchId = MatchId.from(1);
        Player playerOne = Player.from(PLAYER_ONE, "Federer");
        Player playerTwo = Player.from(PLAYER_TWO, "NADAL");
        int noOfSets = 3;
        try (MockedStatic<Match> gameMockedStatic = Mockito.mockStatic(Match.class)) {
            gameMockedStatic.when(() -> Match.from(any(MatchId.class), any(Player.class), any(Player.class), anyInt())).thenReturn(mockMatch);

            matchApplicationServiceUnderTest.startNewMatch(matchId, playerOne, playerTwo, noOfSets);

            gameMockedStatic.verify(() ->
                    Match.from(
                            matchIdArgumentCaptor.capture(),
                            playerOneArgumentCaptor.capture(),
                            playerTwoArgumentCaptor.capture(),
                            noOfSetsArgumentCaptor.capture()));
        }
    }

    @Test
    void shouldStoreCreatedMatchToMatchRepository() {

        MatchId matchId = MatchId.from(1);
        Player playerOne = Player.from(PLAYER_ONE, "Federer");
        Player playerTwo = Player.from(PLAYER_TWO, "NADAL");
        int noOfSets = 3;
        try (MockedStatic<Match> gameMockedStatic = Mockito.mockStatic(Match.class)) {
            gameMockedStatic.when(() -> Match.from(any(MatchId.class), any(Player.class), any(Player.class), anyInt())).thenReturn(mockMatch);

            matchApplicationServiceUnderTest.startNewMatch(matchId, playerOne, playerTwo, noOfSets);

            mockMatchRepository.store(matchArgumentCaptor.capture());
        }
    }

}
