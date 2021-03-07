package com.tennis.match.domain.model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter(AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class GameScore {

    private Points points;

    private GameScore(Points points) {
        setPoints(points);
    }

    public static GameScore of(Points points) {
        return new GameScore(points);
    }

    private void setPoints(Points points) {
        this.points = points;
    }

    public Points points() {
        return getPoints();
    }

}
