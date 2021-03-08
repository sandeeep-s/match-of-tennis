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
            return ADVANTAGE;
        }
    },
    ADVANTAGE{
        @Override
        public Points nextPoint() {
            return GAME;
        }

        @Override
        public Points prevPoint() {
            return FORTY;
        }
    },
    GAME;

    public Points nextPoint() {
        throw new IllegalStateException("No point can be added after " + this);
    }


    public Points prevPoint() {
        throw new IllegalStateException("Transition to previous point is illegal from " + this);
    }
}
