package com.example.freeforfun.ui.model;


import java.util.HashMap;
import java.util.Map;

public enum ELocalType {

	RESTAURANT(0),
	PUB(1),
	CLUB(2),
	TERRACE(3),
	CONFECTIONERY(4),
	FAST_FOOD(5),
	CANTEEN(6),
	PIZZERIA(7),
	SKY_BAR(8);

	private Integer localType;

	private static Map<Integer, ELocalType> map = new HashMap<>();
	static {
		for (ELocalType types : ELocalType.values()) {
			map.put(types.localType, types);
		}
	}

	ELocalType(int i) {
	}

	public static ELocalType valueOf(Integer type) {
		return map.get(type);
	}
}
