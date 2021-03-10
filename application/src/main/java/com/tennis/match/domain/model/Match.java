package com.tennis.match.domain.model;

import lombok.*;

import java.util.*;

import static com.tennis.match.domain.model.MatchStatus.COMPLETED;
import static com.tennis.match.domain.model.MatchStatus.IN_PROGRESS;

@Getter(AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Match {

    @EqualsAndHashCode.Include
    private MatchId matchId;
    private Player playerOne;
    private Player playerTwo;
    private List<TennisMatchSet> sets;
    private SetId currentSetId;
    private ScoreCard scoreCard;
    private MatchStatus status;
    private PlayerNumber winner;

    public static Match from(MatchId matchId, Player playerOne, Player playerTwo, NoOfSets numberOfSets) {
        return new Match(matchId, playerOne, playerTwo, numberOfSets);
    }

    public void scorePoint(PlayerNumber playerNumber) {
        if (status() == COMPLETED) {
            throw new IllegalStateException("Points cannot be scored on completed match");
        }
        currentSet().scorePointFor(playerNumber);
    }

    public void startNewSet() {
        if (currentSetId.value() < sets.size()) {
            currentSetId = sets.get(currentSetId.value()).setId();
        } else {
            setWinner(identifyWinner());
            setStatus(COMPLETED);
        }
    }

    private PlayerNumber identifyWinner() {
        return null;
    }

    public Game currentGame() {
        return currentSet().currentGame();
    }

    public TennisMatchSet currentSet() {
        return getSets().get(getCurrentSetId().value() - 1);
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

    public ScoreCard scoreCard() {
        return getScoreCard();
    }

    public PlayerNumber winner() {
        return getWinner();
    }

    public MatchStatus status() {
        return getStatus();
    }

    protected Match(MatchId matchId, Player playerOne, Player playerTwo, NoOfSets numberOfSets) {
        setMatchId(matchId);
        setPlayerOne(playerOne);
        setPlayerTwo(playerTwo);
        List<TennisMatchSet> sets = new ArrayList<>();
        for (int i = 1; i <= numberOfSets.numericValue; i++) {
            sets.add(TennisMatchSet.from(SetId.from(i), this));
        }
        setSets(sets);
        setCurrentSetId(sets.get(0).setId());
        setScoreCard(new ScoreCard(this));
        setStatus(IN_PROGRESS);
    }

    public List<TennisMatchSet> getSets() {
        return Collections.unmodifiableList(sets);
    }

    private void setMatchId(MatchId matchId) {
        if (null == matchId){
            throw new IllegalArgumentException("MatchId is required");
        }
        this.matchId = matchId;
    }

    private void setPlayerOne(Player playerOne) {
        if (null == playerOne){
            throw new IllegalArgumentException("PlayerOne is required");
        }
        this.playerOne = playerOne;
    }

    private void setPlayerTwo(Player playerTwo) {
        if (null == playerTwo){
            throw new IllegalArgumentException("PlayerTwo is required");
        }
        this.playerTwo = playerTwo;
    }

    private void setSets(List<TennisMatchSet> sets) {
        if (null == sets){
            throw new IllegalArgumentException("Sets are required");
        }
        this.sets = sets;
    }

    private void setCurrentSetId(SetId currentSetId) {
        if (null == currentSetId){
            throw new IllegalArgumentException("CurrentSetId is required");
        }
        this.currentSetId = currentSetId;
    }

    private void setScoreCard(ScoreCard scoreCard) {
        if (null == scoreCard){
            throw new IllegalArgumentException("ScoreCard is required");
        }
        this.scoreCard = scoreCard;
    }

    private void setStatus(MatchStatus status) {
        if (null == status){
            throw new IllegalArgumentException("MatchStatus is required");
        }
        this.status = status;
    }

    private void setWinner(PlayerNumber winner) {
        this.winner = winner;
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
