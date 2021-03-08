package com.tennis.match.domain.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter(AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class SetScore implements Serializable {

    private static final long serialVersionUID = 1L;

    private SetId setId;
    private List<Game> gamesWon;

}