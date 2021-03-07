package com.tennis.match.domain.model;

public enum Points {

    LOVE("0") {
        @Override
        public Points nextPoint(boolean isDeuce) {
            return FIFTEEN;
        }
    },
    FIFTEEN("15") {
        @Override
        public Points nextPoint(boolean isDeuce) {
            return THIRTY;
        }

    },
    THIRTY("30") {
        @Override
        public Points nextPoint(boolean isDeuce) {
            return FORTY;
        }
    },
    FORTY("40") {
        @Override
        public Points nextPoint(boolean isDeuce) {
            if (isDeuce){
                return ADVANTAGE;
            }else{
                return GAME;
            }
        }
    },
    ADVANTAGE("AD"),
    GAME("Game");

    private String pointsScore;

    Points(String pointsScore) {
        this.pointsScore = pointsScore;
    }

    public String pointsScore() {
        return pointsScore;
    }

    public Points nextPoint(boolean isDeuce) {
        throw new IllegalStateException("No further increment is possible from " + this.pointsScore);
    }


}
