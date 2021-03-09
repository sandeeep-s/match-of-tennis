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

    public void startNewSet() {
        currentSet = TennisMatchSet.from(newSetId());
        sets.add(currentSet);
    }

    private SetId newSetId() {
        return SetId.from(1);
    }

    public void startNewGame() {
        currentSet().startNewGame();
    }

    protected Match(MatchId matchId, Player playerOne, Player playerTwo, NoOfSets numberOfSets) {
        setMatchId(matchId);
        setPlayerOne(playerOne);
        setPlayerTwo(playerTwo);
        List<TennisMatchSet> sets = new ArrayList<>();
        for (int i = 1; i <= numberOfSets.numericValue; i++) {
            sets.add(TennisMatchSet.from(SetId.from(i)));
        }
        setSets(sets);
        setCurrentSet(getSets().get(0));
    }

    public static Match from(MatchId matchId, Player playerOne, Player playerTwo, NoOfSets numberOfSets) {
        return new Match(matchId, playerOne, playerTwo, numberOfSets);
    }

    public Game currentGame() {
        return currentSet().currentGame();
    }

    public TennisMatchSet currentSet() {
        return getCurrentSet();
    }

    public MatchId matchId() {
        return getMatchId();
    }

    public Player playerOne() {
        return getPlayerOne();
    }

    public Player playerTwo() {
        return getPlayerTwo();
    }

    public List<TennisMatchSet> sets() {
        return getSets();
    }

    private void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    private void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    private void setSets(List<TennisMatchSet> sets) {
        this.sets = sets;
    }

    private void setCurrentSet(TennisMatchSet currentSet) {
        this.currentSet = currentSet;
    }

    public enum NoOfSets {
        THREE(3), FIVE(5);

        private int numericValue;

        NoOfSets(int numericValue) {
            this.numericValue = numericValue;
        }

        public int numericValue() {
            return numericValue;
        }
    }

}
