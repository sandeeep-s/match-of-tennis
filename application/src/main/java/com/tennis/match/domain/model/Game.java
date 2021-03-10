package com.tennis.match.domain.model;

import lombok.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static com.tennis.match.domain.model.PlayerNumber.PLAYER_TWO;
import static com.tennis.match.domain.model.Points.LOVE;

@Getter(AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Game {

    @EqualsAndHashCode.Include
    private GameId gameId;
    @EqualsAndHashCode.Include
    private TennisMatchSet parentSet;
    private Map<PlayerNumber, Points> scores;
    private GameScoringRules gameScoringRules;

    public static Game from(GameId gameId, TennisMatchSet set) {
        return new Game(gameId, set);
    }

    public static Game from(Game game) {
        return new Game(game.gameId, game.parentSet, game.scores, game.gameScoringRules);
    }

    public void scorePointFor(PlayerNumber playerNumber) {
        Points newScore = gameScoringRules.calculateNewScoreOf(playerNumber, this);
        updateScoreOf(playerNumber, newScore);
        Points newScoreOfOpponent = gameScoringRules.calculateNewScoreOfOpponent(playerNumber, this);
        updateScoreOf(playerNumber.opponent(), newScoreOfOpponent);
        if (gameScoringRules.isDeuce(this)) {
            setGameScoringRules(new DeuceGameScoringRules());
        }
    }

    public boolean isDeuce() {
        return gameScoringRules.isDeuce(this);
    }

    public GameId gameId() {
        return getGameId();
    }

    public TennisMatchSet parentSet() {
        return getParentSet();
    }

    public GameScoringRules gameRules() {
        return getGameScoringRules();
    }

    public Points scoreOf(PlayerNumber playerNumber) {
        return getScores().get(playerNumber);
    }

    private Map<PlayerNumber, Points> getScores() {
        return Collections.unmodifiableMap(scores);
    }

    void updateScoreOf(PlayerNumber playerNumber, Points points) {
        scores.put(playerNumber, points);
    }

    protected Game(GameId gameId, TennisMatchSet set) {
        setGameId(gameId);
        setParentSet(set);
        Map<PlayerNumber, Points> scoresMap = new HashMap<>();
        scoresMap.put(PLAYER_ONE, LOVE);
        scoresMap.put(PLAYER_TWO, LOVE);
        setScores(scoresMap);
        setGameScoringRules(new SimpleGameScoringRules());
    }

    private void setGameId(GameId gameId) {
        if (null == gameId) {
            throw new IllegalArgumentException("GameId is required");
        }
        this.gameId = gameId;
    }

    private void setParentSet(TennisMatchSet set) {
        if (null == set) {
            throw new IllegalArgumentException("Set is required");
        }
        this.parentSet = set;
    }

    private void setScores(Map<PlayerNumber, Points> scores) {
        if (null == scores) {
            throw new IllegalArgumentException("Scores are required");
        }
        this.scores = scores;
    }

    protected void setGameScoringRules(GameScoringRules gameScoringRules) {
        if (null == gameScoringRules) {
            throw new IllegalArgumentException("GameScoringRules are required");
        }
        this.gameScoringRules = gameScoringRules;
    }

}
