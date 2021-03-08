package com.tennis.match.domain.model;

public enum PlayerNumber {

    PLAYER_ONE(){
        @Override
        PlayerNumber oppositePlayerNumber() {
            return PLAYER_TWO;
        }
    },
    PLAYER_TWO(){
        @Override
        PlayerNumber oppositePlayerNumber() {
            return PLAYER_ONE;
        }
    };

    PlayerNumber oppositePlayerNumber(){return null;}
}
