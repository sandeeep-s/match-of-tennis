package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.*;
import org.junit.jupiter.api.Test;

import static com.tennis.match.domain.model.Points.LOVE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WhenNewSetStarts_theTennisMatchSet {

    @Test
    void shouldStartWithScoreOfZeroGamesForEachPlayer() {

        TennisMatchSet newSet = TennisMatchSet.from(SetId.from(1));

    }

}
