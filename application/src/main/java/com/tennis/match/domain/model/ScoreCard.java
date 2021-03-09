package com.tennis.match.domain.model;

import java.util.Map;
import java.util.Objects;

public class ScoreCard {


    private Map<PlayerNumber, Map<SetId, Integer>> setScore;
    private Map<PlayerNumber, Points> currentGameScore;

    private Match match;

    public ScoreCard(Match match) {

        Objects.requireNonNull(match);

        currentGameScore = match.currentGame().scores();
    }

    public Points currentGameScoreOf(PlayerNumber playerNumber) {
        return match.currentGame().scoreOf(playerNumber);
    }

    public int scoreForCurrentSetOf(PlayerNumber playerNumber) {
        return match.currentSet().scoreOf(playerNumber);
    }

}
