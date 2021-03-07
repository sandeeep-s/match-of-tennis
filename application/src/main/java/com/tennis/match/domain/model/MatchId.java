package com.tennis.match.domain.model;

import lombok.*;

import java.io.Serializable;

@Getter(AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class MatchId implements Serializable {

    private static final long serialVersionUID = 1L;
    private long value;

    private MatchId(long aValue) {
        this.setValue(aValue);
    }

    private void setValue(long aValue) {
        if (aValue <= 0) {
            throw new IllegalArgumentException(String.format("%s is not a valid match id", aValue));
        }
        this.value = aValue;
    }

    public static MatchId from(long partnerNumber) {
        return new MatchId(partnerNumber);
    }

    public static MatchId from(MatchId partnerNumber) {
        return new MatchId(partnerNumber.value());
    }

    public long value() {
        return getValue();
    }

}