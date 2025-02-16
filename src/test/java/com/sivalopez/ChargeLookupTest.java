package com.sivalopez;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChargeLookupTest {
    // TODO: Use @ParameterizedTest
    @Test
    void testLookupChargeForACompletedTrip() {
        Trip trip = new Trip();
        trip.setFromStopId("Stop1");
        trip.setToStopId("Stop2");
        double chargeAmount = ChargeLookup.lookupChargeForTrip(trip);

        assertEquals(3.25, chargeAmount);
    }

    @Test
    void testLookupChargeForAnIncompleteTrip() {
        Trip trip = new Trip();
        trip.setFromStopId("Stop1");
        double chargeAmount = ChargeLookup.lookupChargeForTrip(trip);

        assertEquals(7.30, chargeAmount);
    }

    @Test
    void testLookupChargeForACancelledTrip() {
        Trip trip = new Trip();
        trip.setFromStopId("Stop1");
        trip.setToStopId("Stop1");
        double chargeAmount = ChargeLookup.lookupChargeForTrip(trip);

        assertEquals(0.0, chargeAmount);
    }
}
