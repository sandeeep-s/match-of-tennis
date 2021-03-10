package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.MockitoAnnotations.openMocks;

public class WhenStartingNewGame_theTennisMatchSet {

    private TennisMatchSet setUnderTest;
    @Mock
    private Game mockGame;
    @Captor
    private ArgumentCaptor<GameId> gameIdArgumentCaptor;
    @Captor
    private ArgumentCaptor<TennisMatchSet> setArgumentCaptor;
    @Mock
    private Match mockMatch;

    @BeforeEach
    void setUp() {
        openMocks(this);
        setUnderTest = TennisMatchSet.from(SetId.from(1), mockMatch);
        ;
    }

    @Test
    void shouldCreateNewGame() {

        try (MockedStatic<Game> gameMockedStatic = Mockito.mockStatic(Game.class)) {
            gameMockedStatic.when(() -> Game.from(any(GameId.class), any(TennisMatchSet.class))).thenReturn(mockGame);

            setUnderTest.startNewGame();

            gameMockedStatic.verify(() -> Game.from(gameIdArgumentCaptor.capture(), setArgumentCaptor.capture()));
        }
    }

    @Test
    void shouldMakeNewlyCreatedGameAsCurrent() {

        Game createdMockGame = Game.from(GameId.from(1), TennisMatchSet.from(SetId.from(1), mockMatch));
        try (MockedStatic<Game> gameMockedStatic = Mockito.mockStatic(Game.class)) {
            gameMockedStatic.when(() -> Game.from(any(GameId.class), any(TennisMatchSet.class))).thenReturn(createdMockGame);

            setUnderTest.startNewGame();

            assertThat(setUnderTest.currentGame().gameId()).isEqualTo(createdMockGame.gameId());
        }
    }


}
