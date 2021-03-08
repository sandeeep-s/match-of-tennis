package com.tennis.match.application;

import com.tennis.match.domain.model.*;

public class MatchApplicationService {

    private MatchRepository matchRepository;

    public MatchApplicationService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public void startNewGameOfMatchWith(MatchId matchId) {
        Match match = matchRepository.matchWith(matchId);
        match.startNewGame();
    }

    public void scorePoint(MatchId matchId, PlayerNumber playerNumber) {
        Match match = matchRepository.matchWith(matchId);
        Game game = match.currentGame();
        game.scorePoint(playerNumber);
    }

    public void startNewSetOfMatchWith(MatchId matchId) {
        Match match = matchRepository.matchWith(matchId);
        match.startNewSet();
    }
}
