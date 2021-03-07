package com.tennis.match.domain.model;

public interface MatchRepository {
    Match matchWith(MatchId matchId);
}
