package com.tennis.match.domain.model;

public enum Points {

    LOVE {
        @Override
        public Points nextPoint() {
            return FIFTEEN;
        }
    },
    FIFTEEN {
        @Override
        public Points nextPoint() {
            return THIRTY;
        }

    },
    THIRTY {
        @Override
        public Points nextPoint() {
            return FORTY;
        }
    },
    FORTY {
        @Override
        public Points nextPoint() {
            return GAME;
        }
    },
    ADVANTAGE,
    GAME;

    public Points nextPoint() {
        throw new IllegalStateException("No further increment is possible from " + this);
    }


}
