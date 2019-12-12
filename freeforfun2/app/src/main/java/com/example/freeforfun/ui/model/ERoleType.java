package com.example.freeforfun.ui.model;

import java.util.HashMap;
import java.util.Map;

enum ERoleType {
    USER(0),
    ADMIN(1),
    SUPERADMIN(2);

    private Integer role;

    private static Map<Integer, ERoleType> map = new HashMap<Integer, ERoleType>();
    static {
        for (ERoleType roles : ERoleType.values()) {
            map.put(roles.role, roles);
        }
    }

    ERoleType(int i) {
    }

    public static ERoleType valueOf(Integer role) {
        return map.get(role);
    }

    public Integer getRole() {
        return role;
    }
}
