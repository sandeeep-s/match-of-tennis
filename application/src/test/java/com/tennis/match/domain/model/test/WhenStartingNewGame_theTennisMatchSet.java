package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class WhenStartingNewGame_theTennisMatchSet {

    private TennisMatchSet setUnderTest;
    @Mock
    private Game mockGame;
    @Captor
    private ArgumentCaptor<GameId>  gameIdArgumentCaptor;
    @Captor
    private ArgumentCaptor<TennisMatchSet>  setArgumentCaptor;
    private

    @BeforeEach
    void setUp() {
        openMocks(this);
        setUnderTest = TennisMatchSet.builder().currentGame(mockGame).build();
    }

    @Test
    void shouldCreateNewGame(){

        try(MockedStatic<Game> gameMockedStatic = Mockito.mockStatic(Game.class)){
            gameMockedStatic.when(() -> Game.from(any(GameId.class), any(TennisMatchSet.class))).thenReturn(mockGame);

            setUnderTest.startNewGame();

            gameMockedStatic.verify(() -> Game.from(gameIdArgumentCaptor.capture(),setArgumentCaptor.capture()));
        }
    }

    @Test
    void shouldStartNewlyCreatedGame(){

        try(MockedStatic<Game> gameMockedStatic = Mockito.mockStatic(Game.class)){
            gameMockedStatic.when(() -> Game.from(any(GameId.class), any(TennisMatchSet.class))).thenReturn(mockGame);

            setUnderTest.startNewGame();

            verify(mockGame, times(1)).start();
        }
    }

    @Test
    void shouldMakeNewlyCreatedGameAsInProgress(){

        Game createdMockGame = Game.from(GameId.from(1), TennisMatchSet.from(SetId.from(1)));
        try(MockedStatic<Game> gameMockedStatic = Mockito.mockStatic(Game.class)){
            gameMockedStatic.when(() -> Game.from(any(GameId.class), any(TennisMatchSet.class))).thenReturn(createdMockGame);

            setUnderTest.startNewGame();

            assertThat(setUnderTest.currentGame().gameId()).isEqualTo(createdMockGame.gameId());
        }
    }


}
