package com.tennis.match.domain.model;

import lombok.*;

import java.io.Serializable;

@Getter(AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class GameId implements Serializable {

    private static final long serialVersionUID = 1L;

    private int value;

    private GameId(int aValue) {
        this.setValue(aValue);
    }

    private void setValue(int aValue) {
        if (aValue <= 0) {
            throw new IllegalArgumentException(String.format("%s is not a valid game id", aValue));
        }
        this.value = aValue;
    }

    public static GameId from(int gameId) {
        return new GameId(gameId);
    }

    public static GameId from(GameId gameId) {
        return new GameId(gameId.value());
    }

    public int value() {
        return getValue();
    }

}