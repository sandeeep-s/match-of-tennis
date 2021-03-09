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
    private ArgumentCaptor<Match.NoOfSets> noOfSetsArgumentCaptor;
    @Captor
    private ArgumentCaptor<Match> matchArgumentCaptor;

    @BeforeEach
    void setUp() {
        openMocks(this);
        matchApplicationServiceUnderTest = new MatchApplicationService(mockMatchRepository);
    }

    @Test
    void shouldCreateNewMatch() {

        Player playerOne = Player.from(PLAYER_ONE, "Federer");
        Player playerTwo = Player.from(PLAYER_TWO, "NADAL");

        try (MockedStatic<Match> gameMockedStatic = Mockito.mockStatic(Match.class)) {
            gameMockedStatic.when(() -> Match.from(any(MatchId.class), any(Player.class), any(Player.class), any(Match.NoOfSets.class))).thenReturn(mockMatch);

            matchApplicationServiceUnderTest.startNewMatch(playerOne, playerTwo, Match.NoOfSets.THREE);

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

        Player playerOne = Player.from(PLAYER_ONE, "Federer");
        Player playerTwo = Player.from(PLAYER_TWO, "NADAL");
        try (MockedStatic<Match> gameMockedStatic = Mockito.mockStatic(Match.class)) {
            gameMockedStatic.when(() -> Match.from(any(MatchId.class), any(Player.class), any(Player.class), any(Match.NoOfSets.class))).thenReturn(mockMatch);

            matchApplicationServiceUnderTest.startNewMatch(playerOne, playerTwo, Match.NoOfSets.THREE);

            mockMatchRepository.store(matchArgumentCaptor.capture());
        }
    }

}
