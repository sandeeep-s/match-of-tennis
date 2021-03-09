package com.tennis.match.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static com.tennis.match.domain.model.PlayerNumber.PLAYER_TWO;

@Getter(AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
public class TieBreaker extends Game {

    private GameId gameId;
    private TennisMatchSet parentSet;
    private Map<PlayerNumber, Integer> tbScores;

    public static TieBreaker from(GameId gameId, TennisMatchSet set) {
        return new TieBreaker(gameId, set);
    }

    public void scorePointFor(PlayerNumber playerNumber) {
        getTbScores().put(playerNumber, tbScoreOf(playerNumber) + 1);

        if (tbScoreOf(playerNumber) >= 6 && (tbScoreOf(playerNumber) - tbScoreOf(playerNumber.oppositePlayerNumber()) == 2)) {
            parentSet.awardSetTo(playerNumber);
        }
    }

    private TieBreaker(GameId gameId, TennisMatchSet set) {
        super(gameId, set);
        tbScores = new HashMap<>(Map.of(PLAYER_ONE, 0, PLAYER_TWO, 0));
    }

    private Integer tbScoreOf(PlayerNumber playerNumber) {
        return getTbScores().get(playerNumber);
    }
}
