package com.tennis.match.domain.model;

import lombok.*;

import java.util.*;

import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static com.tennis.match.domain.model.PlayerNumber.PLAYER_TWO;

@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class TennisMatchSet {

    @EqualsAndHashCode.Include
    private SetId setId;
    @EqualsAndHashCode.Include
    private Match match;
    private List<Game> games;
    private Game currentGame;
    private Map<PlayerNumber, List<Game>> gamesWonByPlayers;
    private SetScoringRules scoringRules;
    private PlayerNumber winner;

    public static TennisMatchSet from(SetId setId, Match match) {
        return new TennisMatchSet(setId, match);
    }

    public void scorePointFor(PlayerNumber playerNumber) {
        Game game = currentGame();
        game.scorePointFor(playerNumber);
        if (scoringRules.hasWonGame(playerNumber, game)) {
            getGamesWonByPlayers().get(playerNumber).add(game);
            if (scoringRules.hasWonSet(playerNumber, this)) {
                awardSetTo(playerNumber);
            } else if (scoringRules.isSetTied(this)) {
                startTieBreaker();
            } else {
                startNewGame();
            }
        }
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

    public void awardSetTo(PlayerNumber playerNumber) {
        setWinner(playerNumber);
        match.startNewSet();
    }

    public int scoreOf(PlayerNumber playerNumber) {
        return getGamesWonByPlayers().get(playerNumber).size();
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

    public Match match() {
        return getMatch();
    }

    public Map<PlayerNumber, List<Game>> gamesWonByPlayers() {
        return getGamesWonByPlayers();
    }

    private GameId newGameId() {
        System.out.println("getGames().size()=" + getGames().size());
        return GameId.from(getGames().size() + 1);
    }

    protected TennisMatchSet(SetId setId, Match match) {
        setSetId(setId);
        setGames(new ArrayList<>());
        setGamesWonByPlayers(new HashMap<>(
                Map.of(PLAYER_ONE, new ArrayList<>(),
                        PLAYER_TWO, new ArrayList<>())
        ));
        setScoringRules(new SetScoringRules());
        setMatch(match);
        startNewGame();
    }

    private void setSetId(SetId setId) {
        if (null == setId) {
            throw new IllegalArgumentException("SetId is required");
        }
        this.setId = setId;
    }

    private void setGames(List<Game> games) {
        if (null == games) {
            throw new IllegalArgumentException("Games is required");
        }
        this.games = games;
    }

    protected void setCurrentGame(Game currentGame) {
        if (null == currentGame) {
            throw new IllegalArgumentException("CurrentGame is required");
        }
        this.currentGame = currentGame;
    }

    protected void setScoringRules(SetScoringRules scoringRules) {
        if (null == scoringRules) {
            throw new IllegalArgumentException("ScoringRules is required");
        }
        this.scoringRules = scoringRules;
    }

    private void setGamesWonByPlayers(Map<PlayerNumber, List<Game>> gamesWonByPlayers) {
        if (null == gamesWonByPlayers) {
            throw new IllegalArgumentException("GamesWonByPlayers is required");
        }
        this.gamesWonByPlayers = gamesWonByPlayers;
    }

    private List<Game> getGames() {
        return Collections.unmodifiableList(games != null ? games : new ArrayList<>());
    }

    public PlayerNumber winner() {
        return getWinner();
    }
}
