package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.Match;
import com.tennis.match.domain.model.SetId;
import com.tennis.match.domain.model.TennisMatchSet;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class WhenNewSetStarts_theTennisMatchSet {

    @Mock
    private Match mockMatch;

    @Test
    void shouldStartWithScoreOfZeroGamesForEachPlayer() {

        TennisMatchSet newSet = TennisMatchSet.from(SetId.from(1), mockMatch);

    }

}
