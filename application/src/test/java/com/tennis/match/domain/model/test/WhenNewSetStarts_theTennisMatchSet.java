package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WhenNewSetStarts_theTennisMatchSet {

    @Mock
    private Match mockMatch;

    @Test
    void shouldStartWithScoreOfZeroGamesForEachPlayer() {

        TennisMatchSet newSet = TennisMatchSet.from(SetId.from(1), mockMatch);

    }

}
