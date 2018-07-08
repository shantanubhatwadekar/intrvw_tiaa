package com.tiaa.ccengine.model;

import lombok.Getter;

public enum CardType {
    VISA("VISA", 4),
    MASTER("MASTER", 5),
    AMEX("AMEX", 37),
    DISCOVER("DISCOVER", 6);

    private String type;
    @Getter private int prefix;

    CardType(String type, int prefix) {
        this.type = type;
        this.prefix = prefix;
    }

    public static CardType getType(String type) {
        try {
            return CardType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
