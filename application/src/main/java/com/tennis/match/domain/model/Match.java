package com.tennis.match.domain.model;

import lombok.Builder;

import java.util.List;

@Builder
public class Match {

    private Player playerOne;
    private Player playerTwo;
    private List<TennisMatchSet> sets;
    private TennisMatchSet setInProgress;

    public void startNewGame() {
        setInProgress.startNewGame();
    }

    public Game currentGame() {
        return null;
    }
}
