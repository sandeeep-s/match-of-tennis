package com.tennis.match.domain.model;

import lombok.*;

import java.io.Serializable;

@Getter(AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class PlayerId implements Serializable {

    private static final long serialVersionUID = 1L;
    private long value;

    private PlayerId(long aValue) {
        this.setValue(aValue);
    }

    private void setValue(long aValue) {
        if (aValue <= 0) {
            throw new IllegalArgumentException(String.format("%s is not a valid player id", aValue));
        }
        this.value = aValue;
    }

    public static PlayerId from(long playerId) {
        return new PlayerId(playerId);
    }

    public static PlayerId from(PlayerId playerId) {
        return new PlayerId(playerId.value());
    }

    public long value() {
        return getValue();
    }

}