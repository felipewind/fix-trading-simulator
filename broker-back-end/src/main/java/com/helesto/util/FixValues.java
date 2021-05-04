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
        ordStatusMap.put(OrderEntity.NEW_ORDER_NOT_CONFIRMED, "Pending Confirmation");
        ordStatusMap.put(OrderEntity.CANCEL_NOT_CONFIRMED, "Cancel Pending Confirmation");
        ordStatusMap.put(OrdStatus.NEW, "New");
        ordStatusMap.put(OrdStatus.PARTIALLY_FILLED, "Partially Filled");
        ordStatusMap.put(OrdStatus.FILLED, "Filled");
        ordStatusMap.put(OrdStatus.PENDING_CANCEL, "Pending Cancel");
        ordStatusMap.put(OrdStatus.CANCELED, "Canceled");
        ordStatusMap.put(OrdStatus.REJECTED, "Rejected");
    }

    public static String getOrdStatusText(char ordStatus) {
        return ordStatusMap.getOrDefault(ordStatus, "Incorrect status");
    }

}
