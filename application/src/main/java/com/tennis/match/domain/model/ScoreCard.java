package com.tennis.match.domain.model;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ScoreCard {

    private Map<PlayerNumber, Map<SetId, Integer>> setScore;
    private Map<PlayerNumber, Points> currentGameScore;

    private final Match match;

    public ScoreCard(Match match) {
        Objects.requireNonNull(match);
        this.match = match;
    }

    public Points currentGameScoreOf(PlayerNumber playerNumber) {
        return match.currentGame().scoreOf(playerNumber);
    }

    public int scoreForCurrentSetOf(PlayerNumber playerNumber) {
        return match.currentSet().scoreOf(playerNumber);
    }

    public List<SetScore> scoreForAllSetsOf(PlayerNumber playerNumber) {
        return match.sets().stream()
                .map(set -> SetScore.from(set.setId(), set.gamesWonByPlayers().get(playerNumber).size()))
                .collect(Collectors.toList());
    }

}
