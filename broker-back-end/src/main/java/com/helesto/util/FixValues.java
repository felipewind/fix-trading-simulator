package com.helesto.util;

import java.util.Map;
import java.util.TreeMap;

import com.helesto.model.OrderEntity;

import quickfix.field.OrdStatus;
import quickfix.field.Side;

public final class FixValues {

    private FixValues() {

    }

    public static final Map<Character, String> sideMap = new TreeMap<>();

    static {
        sideMap.put(Side.BUY, "Buy");
        sideMap.put(Side.SELL, "Sell");
    }

    public static String getSideText(char side) {
        return sideMap.getOrDefault(side, "Incorrect side");
    }

    public static final Map<Character, String> ordStatusMap = new TreeMap<>();

    static {
        ordStatusMap.put(OrderEntity.NOT_CONFIRMED_BY_COUNTERPARTY, "Pending Confirmation");
        ordStatusMap.put(OrdStatus.NEW, "New");
        ordStatusMap.put(OrdStatus.FILLED, "Filled");
    }

    public static String getOrdStatusText(char ordStatus) {
        return ordStatusMap.getOrDefault(ordStatus, "Incorrect status");
    }

}
