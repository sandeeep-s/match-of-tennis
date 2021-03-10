package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.Game;
import com.tennis.match.domain.model.PlayerNumber;
import com.tennis.match.domain.model.Points;
import com.tennis.match.domain.model.SimpleGameScoringRules;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;

import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static com.tennis.match.domain.model.PlayerNumber.PLAYER_TWO;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class WhenCheckingDeuce_theSimpleGameScoringRules {

    private SimpleGameScoringRules simpleGameScoringRulesUnderTest;
    @Mock
    private Game mockGame;

    @BeforeEach
    void setUp() {
        openMocks(this);
        simpleGameScoringRulesUnderTest = new SimpleGameScoringRules();
    }

    @ParameterizedTest
    @CsvSource({
            "FORTY, FORTY, true",
            "THIRTY, FORTY, false",
            "FORTY, THIRTY, false"
    })
    void shouldFlagDeuceCorrectlyBasedOnPlayerScore(Points playerOneScore, Points playerTwoScore, boolean expectedValue) {

        when(mockGame.scoreOf(PLAYER_ONE)).thenReturn(playerOneScore);
        when(mockGame.scoreOf(PLAYER_TWO)).thenReturn(playerTwoScore);

        boolean isDeuce = simpleGameScoringRulesUnderTest.isDeuce(mockGame);

        assertThat(isDeuce).isEqualTo(expectedValue);
    }

}
