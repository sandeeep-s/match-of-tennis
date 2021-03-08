package com.tennis.match.domain.model;

import lombok.*;

import java.io.Serializable;

@Getter(AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class SetId implements Serializable {

    private static final long serialVersionUID = 1L;

    private int value;

    private SetId(int aValue) {
        this.setValue(aValue);
    }

    private void setValue(int aValue) {
        if (aValue <= 0) {
            throw new IllegalArgumentException(String.format("%s is not a valid match id", aValue));
        }
        this.value = aValue;
    }

    public static SetId from(int setId) {
        return new SetId(setId);
    }

    public static SetId from(SetId setId) {
        return new SetId(setId.value());
    }

    public int value() {
        return getValue();
    }

}