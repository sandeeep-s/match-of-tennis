package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;

import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static com.tennis.match.domain.model.PlayerNumber.PLAYER_TWO;
import static com.tennis.match.domain.model.Points.*;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class WhenCalculatingNewScore_theSimpleGameScoringRules {

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
            "PLAYER_ONE, LOVE, FIFTEEN",
            "PLAYER_ONE, FIFTEEN, THIRTY",
            "PLAYER_ONE, THIRTY, FORTY",
            "PLAYER_ONE, FORTY, GAME",
            "PLAYER_TWO, LOVE, FIFTEEN",
            "PLAYER_TWO, FIFTEEN, THIRTY",
            "PLAYER_TWO, THIRTY, FORTY",
            "PLAYER_TWO, FORTY, GAME"
    })
    void shouldCalculateScoreInAccordanceWithPointsScoredByPlayer(PlayerNumber playerNumber, Points currentScore, Points expectedScore) {

        when(mockGame.scoreOf(playerNumber)).thenReturn(currentScore);

        Points newScore = simpleGameScoringRulesUnderTest.calculateNewScoreOf(playerNumber, mockGame);

        assertThat(newScore).isEqualTo(expectedScore);
    }


    @ParameterizedTest
    @CsvSource({
            "PLAYER_ONE, GAME",
            "PLAYER_TWO, GAME",
    })
    void shouldFailWithError_given_gameIsAlreadyWon(PlayerNumber playerNumber, Points currentScore) {

        when(mockGame.scoreOf(playerNumber)).thenReturn(currentScore);

        Throwable thrown = catchThrowable(() -> simpleGameScoringRulesUnderTest.calculateNewScoreOf(playerNumber, mockGame));

        assertThat(thrown).isInstanceOf(IllegalStateException.class);
    }

}
