package com.tennis.match.port.adapters.api;

import com.tennis.match.application.MatchApplicationService;
import com.tennis.match.domain.model.Match;
import com.tennis.match.domain.model.MatchId;
import com.tennis.match.domain.model.Player;
import com.tennis.match.domain.model.PlayerNumber;

public class MatchScoreManagerApi {

    private MatchApplicationService matchApplicationService;

    public MatchScoreManagerApi(MatchApplicationService matchApplicationService) {
        this.matchApplicationService = matchApplicationService;
    }

    public Match startMatch(Player playerOne, Player playerTwo, Match.NoOfSets numberOfSets){
        return matchApplicationService.startNewMatch(playerOne, playerTwo, numberOfSets);
    }

    public Match awardPointToPlayer(MatchId matchId, PlayerNumber playerNumber ){
        return null;
    }

}
