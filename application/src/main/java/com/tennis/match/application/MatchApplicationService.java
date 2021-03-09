package com.tennis.match.application;

import com.tennis.match.domain.model.*;

public class MatchApplicationService {

    private MatchRepository matchRepository;

    public MatchApplicationService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public Match startNewMatch(Player playerOne, Player playerTwo, Match.NoOfSets numberOfSets) {
        MatchId matchId = matchRepository.nextMatchId();
        Match match = Match.from(matchId, playerOne, playerTwo, numberOfSets);
        matchRepository.store(match);
        return match;
    }

    public void startNewGameOfMatchWith(MatchId matchId) {
        Match match = matchRepository.matchWith(matchId);
        match.startNewGame();
    }

    public void scorePoint(MatchId matchId, PlayerNumber playerNumber) {
        Match match = matchRepository.matchWith(matchId);
        Game game = match.currentGame();
        game.scorePointFor(playerNumber);
    }

    public void startNewSetOfMatchWith(MatchId matchId) {
        Match match = matchRepository.matchWith(matchId);
        match.startNewSet();
    }

}
