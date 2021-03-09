package com.tennis.match.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
public class Player {

    private PlayerNumber playerNumber;
    private String name;

    public static Player from(PlayerNumber playerNumber, String name) {
        return new Player(playerNumber, name);
    }

    private Player(PlayerNumber playerNumber, String name) {
        setPlayerNumber(playerNumber);
        setName(name);
    }

    public PlayerNumber playerNumber() {
        return getPlayerNumber();
    }

    public String name() {
        return getName();
    }

    private void setPlayerNumber(PlayerNumber playerNumber) {
        this.playerNumber = playerNumber;
    }

    private void setName(String name) {
        this.name = name;
    }
}
