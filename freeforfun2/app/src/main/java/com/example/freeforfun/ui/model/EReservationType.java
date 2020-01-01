package com.example.freeforfun.ui.model;

import java.util.HashMap;
import java.util.Map;

public enum EReservationType {

    ONE_HOUR(0),
    TWO_THREE_HOURS(1),
    FOUR_FIVE_HOURS(2),
    SIX_SEVEN_HOURS(3),
    MORE_THAN_7_HOURS(4);

    private Integer reservationType;

    private static Map<Integer, EReservationType> map = new HashMap<>();
    static {
        for (EReservationType types : EReservationType.values()) {
            map.put(types.reservationType, types);
        }
    }

    EReservationType(int i) {}

    public static EReservationType valueOf(Integer type) {
        return map.get(type);
    }
}
