package com.sivalopez;

import java.util.Map;

public class ChargeLookup {
    private static final Map<String, Double> PRICES = Map.of(
            "Stop1Stop2", 3.25,
            "Stop2Stop1", 3.25,
            "Stop1Stop3", 7.30,
            "Stop3Stop1", 7.30,
            "Stop2Stop3", 5.50,
            "Stop3Stop2", 5.50,
            "Stop1", 7.30,
            "Stop2", 5.50,
            "Stop3", 7.30
    );

    public static double lookupChargeForTrip(Trip trip) {
        double chargeAmount = 0.0;
        String key = trip.getFromStopId();
        String toStopId = trip.getToStopId();
        // Incomplete trips can have null toStopId.
        if (toStopId != null) {
            key = key + toStopId;
        }

        if (PRICES.containsKey(key)) {
            chargeAmount = PRICES.get(key);
        }
        return chargeAmount;
    }
}
