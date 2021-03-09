package com.tennis.match.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.tennis.match.domain.model.MatchStatus.COMPLETED;
import static com.tennis.match.domain.model.MatchStatus.IN_PROGRESS;

@Getter(AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
public class Match {

    private MatchId matchId;
    private Player playerOne;
    private Player playerTwo;
    private List<TennisMatchSet> sets;
    private TennisMatchSet currentSet;
    private SetId currentSetId;
    private ScoreCard scoreCard;
    private MatchStatus matchStatus;
    private PlayerNumber winner;

    public void startNewSet() {
        if (currentSetId.value() < sets.size()) {
            currentSetId = sets.get(currentSetId.value()).setId();
        } else {
            setWinner(identifyWinner());
            setMatchStatus(COMPLETED);
        }
    }

    private PlayerNumber identifyWinner() {
        return sets.stream().map(TennisMatchSet::winner)
                .collect(Collectors.groupingBy(playerNumber -> playerNumber, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .orElseThrow(() -> new IllegalStateException("Winner could not be identified even after match completed"))
                .getKey();
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
        setMatchStatus(IN_PROGRESS);
    }

    public static Match from(MatchId matchId, Player playerOne, Player playerTwo, NoOfSets numberOfSets) {
        return new Match(matchId, playerOne, playerTwo, numberOfSets);
    }

    public Game currentGame() {
        return currentSet().currentGame();
    }

    public void scorePoint(PlayerNumber playerNumber) {
        if (matchStatus == COMPLETED) {
            throw new IllegalStateException("Points cannot be scored on completed match");
        }
        currentSet().currentGame().scorePointFor(playerNumber);
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
