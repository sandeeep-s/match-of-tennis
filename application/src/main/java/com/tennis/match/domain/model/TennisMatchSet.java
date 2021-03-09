package com.tennis.match.domain.model;

import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static com.tennis.match.domain.model.PlayerNumber.PLAYER_TWO;

@Getter(AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
public class TennisMatchSet {

    private SetId setId;
    private List<Game> games;
    private Game currentGame;
    private Map<PlayerNumber, List<Game>> gamesWonByPlayers;
    private SetScoringRules scoringRules;
    private PlayerNumber winner;

    public static TennisMatchSet from(SetId setId) {
        return new TennisMatchSet(setId);
    }

    public void startNewGame() {
        Game game = Game.from(newGameId(), this);
        games.add(game);
        setCurrentGame(game);
    }

    private void startTieBreaker() {
        TieBreaker tieBreaker = TieBreaker.from(newGameId(), this);
        games.add(tieBreaker);
        setCurrentGame(tieBreaker);
    }

    public int scoreOf(PlayerNumber playerNumber){
        return getGamesWonByPlayers().get(playerNumber).size();
    }

    public void scoreGameWonBy(PlayerNumber playerNumber, Game game){
        scoringRules.scoreGameFor(playerNumber, game);

        if (scoringRules.isSetTied(this)){
            startTieBreaker();
        }else{
            startNewGame();
        }
    }

    public void addToGamesWonBy(PlayerNumber playerNumber, Game game) {
        getGamesWonByPlayers().get(playerNumber).add(game);
    }

    public void awardSetTo(PlayerNumber playerNumber){
        setWinner(playerNumber);
    }

    public SetId setId() {
        return getSetId();
    }

    public List<Game> games() {
        return getGames();
    }

    public Game currentGame() {
        return getCurrentGame();
    }

    private GameId newGameId() {
        return GameId.from(getGames().size() + 1);
    }

    protected TennisMatchSet(SetId setId) {
        setSetId(setId);
        setGames(new ArrayList<>());
        setGamesWonByPlayers(new HashMap<>(
                Map.of(PLAYER_ONE, new ArrayList<>(),
                        PLAYER_TWO, new ArrayList<>())
        ));
        setScoringRules(new SetScoringRules());
        startNewGame();
    }

    private void setSetId(SetId setId) {
        this.setId = setId;
    }

    private void setGames(List<Game> games) {
        this.games = games;
    }

    private void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    protected void setScoringRules(SetScoringRules scoringRules) {
        this.scoringRules = scoringRules;
    }

    public void setGamesWonByPlayers(Map<PlayerNumber, List<Game>> gamesWonByPlayers) {
        this.gamesWonByPlayers = gamesWonByPlayers;
    }

    private List<Game> getGames() {
        return games != null ? games : new ArrayList<>();
    }

    public PlayerNumber winner() {
        return getWinner();
    }
}
