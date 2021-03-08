package com.tennis.match.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter(AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
public class Match {

    private MatchId matchId;
    private Player playerOne;
    private Player playerTwo;
    private List<TennisMatchSet> sets;
    private TennisMatchSet currentSet;

    public void startNewGame() {
        currentSet.startNewGame();
    }

    public Game currentGame() {
        return currentSet.currentGame();
    }

    public TennisMatchSet currentSet() {
        return getCurrentSet();
    }

    public void startNewSet() {
        currentSet = TennisMatchSet.from(newSetId());
        sets.add(currentSet);
    }

    private SetId newSetId() {
        return SetId.from(1);
    }

    @Builder
    private Match(MatchId matchId, Player playerOne, Player playerTwo) {
        setMatchId(matchId);
        setPlayerOne(playerOne);
        setPlayerTwo(playerTwo);
        setSets(new ArrayList<>());
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    public void setSets(List<TennisMatchSet> sets) {
        this.sets = sets;
    }

    public void setCurrentSet(TennisMatchSet currentSet) {
        this.currentSet = currentSet;
    }
}
