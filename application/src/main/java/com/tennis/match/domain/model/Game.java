package com.tennis.match.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static com.tennis.match.domain.model.PlayerNumber.PLAYER_TWO;
import static com.tennis.match.domain.model.Points.LOVE;

@Getter(AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
public class Game {

    private GameId gameId;
    private TennisMatchSet parentSet;
    private Map<PlayerNumber, Points> scores;
    private GameScoringRules gameScoringRules;

    public static Game from(GameId gameId, TennisMatchSet set) {
        return new Game(gameId, set);
    }

    public void scorePointFor(PlayerNumber playerNumber) {
        gameScoringRules.scorePoint(this, playerNumber);
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

    public Points scoreOf(PlayerNumber playerNumber){
        return getScores().get(playerNumber);
    }

    public Map<PlayerNumber, Points> scores() {
        return scores;
    }

    public Map<PlayerNumber, Points> getScores() {
        return scores != null? scores : Collections.emptyMap();
    }

    void updateScoreOf(PlayerNumber playerNumber, Points points) {
        getScores().put(playerNumber, points);
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
        this.gameId = gameId;
    }

    private void setParentSet(TennisMatchSet set) {
        this.parentSet = set;
    }

    public void setScores(Map<PlayerNumber, Points> scores) {
        this.scores = scores;
    }

    protected void setGameScoringRules(GameScoringRules gameScoringRules) {
        this.gameScoringRules = gameScoringRules;
    }

}
