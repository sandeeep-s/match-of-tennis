package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class WhenStartingNewSet_theMatch {

    private Match matchUnderTest;
    @Mock
    private TennisMatchSet mockSet;
    @Captor
    private ArgumentCaptor<SetId> setIdArgumentCaptor;

    @BeforeEach
    void setUp() {
        openMocks(this);
        matchUnderTest = Match.builder().matchId(MatchId.from(1)).build();
    }

    @Test
    void shouldCreateNewSet() {

        try (MockedStatic<TennisMatchSet> gameMockedStatic = Mockito.mockStatic(TennisMatchSet.class)) {
            gameMockedStatic.when(() -> TennisMatchSet.from(any(SetId.class))).thenReturn(mockSet);

            matchUnderTest.startNewSet();

            gameMockedStatic.verify(() -> TennisMatchSet.from(setIdArgumentCaptor.capture()));
        }
    }

    @Test
    void shouldMakeNewlyCreatedSetAsCurrent() {

        TennisMatchSet tennisMatchSet = TennisMatchSet.from(SetId.from(1));
        try (MockedStatic<TennisMatchSet> gameMockedStatic = Mockito.mockStatic(TennisMatchSet.class)) {
            gameMockedStatic.when(() -> TennisMatchSet.from(any(SetId.class))).thenReturn(tennisMatchSet);

            matchUnderTest.startNewSet();

            assertThat(matchUnderTest.currentSet().setId()).isEqualTo(tennisMatchSet.setId());

        }
    }

}
