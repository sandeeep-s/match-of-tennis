package com.tennis.match.domain.model;

import lombok.AccessLevel;
import lombok.Getter;

import static com.tennis.match.domain.model.Points.LOVE;

@Getter(AccessLevel.PRIVATE)
public class Game {

    private GameId gameId;
    private TennisMatchSet partOfSet;
    private GameScore serverScore;
    private GameScore receiverScore;
    private GameScoringRules gameScoringRules;

    public static Game from(GameId gameId, TennisMatchSet set) {
        return new Game(gameId, set);
    }

    public void start() {
    }

    public void addPointForServer() {
        gameScoringRules.addPointToServerScore(this);
    }

    public void addPointForReceiver() {
        gameScoringRules.addPointToReceiverScore(this);
    }

    private void checkDeuce() {
        if (serverScore.points() == Points.FORTY && receiverScore.points() == Points.FORTY){
            setGameScoringRules(new DeuceGameScoringRules());
        }
    }

    public GameId gameId() {
        return getGameId();
    }

    public TennisMatchSet partOfSet() {
        return getPartOfSet();
    }

    public GameScore serverScore() {
        return getServerScore();
    }

    public GameScore receiverScore() {
        return getReceiverScore();
    }

    public GameScoringRules gameRules() {
        return getGameScoringRules();
    }

    protected Game(GameId gameId, TennisMatchSet set) {
        setGameId(gameId);
        setPartOfSet(set);
        setServerScore(GameScore.of(LOVE));
        setReceiverScore(GameScore.of(LOVE));
        setGameScoringRules(new SimpleGameScoringRules());
    }
    private void setGameId(GameId gameId) {
        this.gameId = gameId;
    }

    private void setPartOfSet(TennisMatchSet set) {
        this.partOfSet = set;
    }

    void setServerScore(GameScore serverScore) {
        this.serverScore = serverScore;
    }

    void setReceiverScore(GameScore receiverScore) {
        this.receiverScore = receiverScore;
    }

    protected void setGameScoringRules(GameScoringRules gameScoringRules) {
        this.gameScoringRules = gameScoringRules;
    }
}
