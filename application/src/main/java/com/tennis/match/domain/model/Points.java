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
    },
    GAME;

    public Points nextPoint() {
        throw new IllegalStateException("No point can be added after" + this);
    }


}
