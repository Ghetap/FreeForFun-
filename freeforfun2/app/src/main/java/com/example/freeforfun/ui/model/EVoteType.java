package com.example.freeforfun.ui.model;

import java.util.HashMap;
import java.util.Map;

public enum EVoteType{
    UPVOTE(1),
    DOWNVOTE(-1);

    private Integer voteType;

    private static Map<Integer, EVoteType> map = new HashMap<>();
    static {
        for (EVoteType voteType1 : EVoteType.values()) {
            map.put(voteType1.voteType, voteType1);
        }
    }

    EVoteType(int i) {
    }

    public static EVoteType valueOf(Integer voteType) {
        return map.get(voteType);
    }
}
