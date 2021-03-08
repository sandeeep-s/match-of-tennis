package com.tennis.match.domain.model;

import lombok.*;

import java.io.Serializable;

@Getter(AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class SetScore implements Serializable {

    private static final long serialVersionUID = 1L;

    private int value;

    private SetScore(int aValue) {
        this.setValue(aValue);
    }

    private void setValue(int aValue) {
        if (aValue <= 0) {
            throw new IllegalArgumentException(String.format("%s is not a valid match id", aValue));
        }
        this.value = aValue;
    }

    public static SetScore from(int score) {
        return new SetScore(score);
    }

    public static SetScore from(SetScore score) {
        return new SetScore(score.value());
    }

    public int value() {
        return getValue();
    }

}