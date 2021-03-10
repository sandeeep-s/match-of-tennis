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

    private SetId setId;
    private Integer gamesWon;


    public SetScore(SetId setId, Integer gamesWon) {
        setSetId(setId);
        setGamesWon(gamesWon);
    }

    public static SetScore from(SetId setId, Integer gamesWon) {
        return new SetScore(setId, gamesWon);
    }

}