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
        currentSet().startNewGame();
    }

    public Game currentGame() {
        return currentSet().currentGame();
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
    protected Match(MatchId matchId, Player playerOne, Player playerTwo, int numberOfSets) {
        setMatchId(matchId);
        setPlayerOne(playerOne);
        setPlayerTwo(playerTwo);
        List<TennisMatchSet> sets = new ArrayList<>();
        for (int i = 1; i <= numberOfSets;i++){
            sets.add(TennisMatchSet.from(SetId.from(i)));
        }
        setSets(sets);
        currentSet = getSets().get(0);
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
