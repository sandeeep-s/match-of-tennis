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

public class WhenCalculatingNewScore_theDeuceGameScoringRules {

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
            "PLAYER_ONE, FORTY, FORTY, ADVANTAGE, FORTY",
            "PLAYER_ONE, ADVANTAGE, FORTY, GAME, FORTY",
            "PLAYER_ONE, FORTY, ADVANTAGE, FORTY, FORTY"
    })
    void shouldCalculateScoreInAccordanceWithPointsScoredByPlayer(PlayerNumber playerNumber,
                                                                  Points currentScore,
                                                                  Points opponentScore,
                                                                  Points expectedScore,
                                                                  Points expectedOpponentScore) {

        when(mockGame.scoreOf(playerNumber)).thenReturn(currentScore);
        when(mockGame.scoreOf(playerNumber.opponent())).thenReturn(opponentScore);

        Points newScore = deuceGameScoringRulesUnderTest.calculateNewScoreOf(playerNumber, mockGame);
        Points newOpponentScore = deuceGameScoringRulesUnderTest.calculateNewScoreOfOpponent(playerNumber, mockGame);

        assertThat(newScore).isEqualTo(expectedScore);
        assertThat(newOpponentScore).isEqualTo(expectedOpponentScore);
    }

    @ParameterizedTest
    @CsvSource({
            "PLAYER_ONE, GAME",
            "PLAYER_TWO, GAME",
    })
    void shouldFailWithError_given_gameIsAlreadyWon(PlayerNumber playerNumber, Points currentScore) {

        when(mockGame.scoreOf(playerNumber)).thenReturn(currentScore);

        Throwable thrown = catchThrowable(() -> deuceGameScoringRulesUnderTest.calculateNewScoreOf(playerNumber, mockGame));

        assertThat(thrown).isInstanceOf(IllegalStateException.class);
    }

}
