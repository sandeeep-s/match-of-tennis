package com.tennis.match.port.adapters.services;

import com.tennis.match.domain.model.Match;
import com.tennis.match.domain.model.MatchId;
import com.tennis.match.domain.model.MatchRepository;

import java.util.Map;
import java.util.Objects;

public class InMemoryMatchRepository implements MatchRepository {

    private Map<MatchId, Match> matchStore;

    public InMemoryMatchRepository(Map<MatchId, Match> matchStore) {

        Objects.requireNonNull(matchStore);
        this.matchStore = matchStore;
    }

    @Override
    public MatchId nextMatchId() {
        return MatchId.from(matchStore.size() + 1);
    }

    @Override
    public Match matchWith(MatchId matchId) {
        return matchStore.get(matchId);
    }

    @Override
    public void store(Match match) {
        matchStore.put(match.matchId(), match);
    }
}
