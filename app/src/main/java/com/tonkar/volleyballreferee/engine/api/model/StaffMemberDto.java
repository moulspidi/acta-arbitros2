package com.tonkar.volleyballreferee.engine.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffMemberDto {
    private String role;     // "Asistente", "Delegado", etc.
    private String name;
    private String license;  // opcional
}
