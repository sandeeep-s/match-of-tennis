package com.tennis.match.application;

import com.tennis.match.domain.model.Match;
import com.tennis.match.domain.model.MatchId;
import com.tennis.match.domain.model.MatchRepository;

public class MatchApplicationService {

    private MatchRepository matchRepository;

    public MatchApplicationService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public void startNewGameOfMatchWith(MatchId matchId) {
        Match match = matchRepository.matchWith(matchId);
        match.startNewGame();
    }
}
