package com.tonkar.volleyballreferee.engine.api.model;

import com.tonkar.volleyballreferee.engine.team.TeamType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RotationSnapshot {
    private TeamType team;      // HOME o GUEST
    private int setIndex;       // Índice de set
    private long timestamp;     // epoch millis
    private int[] positions;    // 6 dorsales
    private boolean correction; // true si es corrección
}
