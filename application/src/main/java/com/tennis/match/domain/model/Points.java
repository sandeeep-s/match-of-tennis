package com.tennis.match.domain.model;

public enum Points {

    ZERO("0") {
        @Override
        public Points nextPoint() {
            return One;
        }
    },
    One("15") {
        @Override
        public Points nextPoint() {
            return Two;
        }

    },
    Two("30") {
        @Override
        public Points nextPoint() {
            return THREE;
        }
    },
    THREE("40") {
        @Override
        public Points nextPoint() {
            return FOUR;
        }
    },
    FOUR("WinGame");

    private String pointsScore;

    Points(String pointsScore) {
        this.pointsScore = pointsScore;
    }

    public String pointsScore() {
        return pointsScore;
    }

    public Points nextPoint() {
        throw new IllegalStateException("No further increment is possible from " + this.pointsScore);
    }


}
