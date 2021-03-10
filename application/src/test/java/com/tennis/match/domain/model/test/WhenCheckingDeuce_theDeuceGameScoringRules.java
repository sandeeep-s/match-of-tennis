package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.DeuceGameScoringRules;
import com.tennis.match.domain.model.Game;
import com.tennis.match.domain.model.Points;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;

import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static com.tennis.match.domain.model.PlayerNumber.PLAYER_TWO;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class WhenCheckingDeuce_theDeuceGameScoringRules {

    private DeuceGameScoringRules deuceGameScoringRulesUnderTest;
    @Mock
    private Game mockGame;

    @BeforeEach
    void setUp() {
        openMocks(this);
        deuceGameScoringRulesUnderTest = new DeuceGameScoringRules();
    }

    @ParameterizedTest
    @CsvSource({
            "FORTY, FORTY, true",
            "FORTY, ADVANTAGE, false",
            "ADVANTAGE, FORTY, false"
    })
    void shouldFlagDeuceCorrectlyBasedOnPlayerScore(Points playerOneScore, Points playerTwoScore, boolean expectedValue) {

        when(mockGame.scoreOf(PLAYER_ONE)).thenReturn(playerOneScore);
        when(mockGame.scoreOf(PLAYER_TWO)).thenReturn(playerTwoScore);

        boolean isDeuce = deuceGameScoringRulesUnderTest.isDeuce(mockGame);

        assertThat(isDeuce).isEqualTo(expectedValue);
    }

}
