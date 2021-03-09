package com.tennis.match.domain.model;

public enum PlayerNumber {

    PLAYER_ONE(){
        @Override
        public PlayerNumber opponent() {
            return PLAYER_TWO;
        }
    },
    PLAYER_TWO(){
        @Override
        public PlayerNumber opponent() {
            return PLAYER_ONE;
        }
    };

    public PlayerNumber opponent(){return null;}
}
