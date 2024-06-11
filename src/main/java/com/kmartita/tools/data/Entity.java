package com.kmartita.tools.data;

import lombok.Getter;

@Getter
public enum Entity {

    TEAM("team", "teams"),
    SPACE("space", "spaces");

    private final String singular;
    private final String plural;

    Entity(String singular, String plural) {
        this.singular = singular;
        this.plural = plural;
    }
}
