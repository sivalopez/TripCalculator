package com.sivalopez;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TripCalculatorTest {
    Tap tapStart = new Tap(1, "22-01-2023 13:00:00", "ON", "Stop1", "Company1", "Bus37","5500005555555559");
    Tap tapEnd = new Tap(2, "22-01-2023 13:05:00", "OFF", "Stop2", "Company1", "Bus37","5500005555555559");

    @Test
    public void testDetermineCompletedTripStatus() {
        TripCalculator calculator = new TripCalculator();
        String actualStatus = calculator.determineTripStatus(tapStart, tapEnd);

        assertEquals("COMPLETED", actualStatus);
    }

    @Test
    public void testDetermineIncompleteTripStatus() {
        TripCalculator calculator = new TripCalculator();
        String actualStatus = calculator.determineTripStatus(tapStart, null);

        assertEquals("INCOMPLETE", actualStatus);
    }

    @Test
    public void testDetermineCancelledTripStatus() {
        Tap tapEnd3mins = new Tap(2, "22-01-2023 13:03:00", "OFF", "Stop1", "Company1", "Bus37","5500005555555559");

        TripCalculator calculator = new TripCalculator();
        String actualStatus = calculator.determineTripStatus(tapStart, tapEnd3mins);

        assertEquals("CANCELLED", actualStatus);
    }

    @Test
    public void testGetTapGroupsByPan() {
        Tap tapThree = new Tap(3, "22-01-2023 09:20:00", "ON", "Stop3", "Company1", "Bus36","4111111111111111");

        Map<String, List<Tap>> expectedTapGroups = new HashMap<>();
        expectedTapGroups.put("5500005555555559", Arrays.asList(tapStart, tapEnd));
        expectedTapGroups.put("4111111111111111", List.of(tapThree));

        TripCalculator calculator = new TripCalculator();
        Map<String, List<Tap>> tapGroups = calculator.getTapGroupsByPan(Arrays.asList(tapStart, tapEnd, tapThree));

        assertEquals(expectedTapGroups, tapGroups);
    }

    @Test
    public void testFindAMatchingTapOffFromMultipleDates() {
        Tap tapThree = new Tap(3, "23-01-2023 09:20:00", "ON", "Stop1", "Company1", "Bus37","5500005555555559");
        Tap tapFour = new Tap(4, "23-01-2023 09:25:00", "OFF", "Stop2", "Company1", "Bus37","5500005555555559");

        TripCalculator calculator = new TripCalculator();
        Tap actualTap = calculator.findMatchingTapOff(tapStart, Arrays.asList(tapStart, tapEnd, tapThree, tapFour));

        assertEquals(tapEnd, actualTap);
    }

    @Test
    public void testFindAnyMatchingTapOffForgotToTapOff() {
        Tap tapTwo = new Tap(2, "22-01-2023 19:20:00", "ON", "Stop2", "Company1", "Bus37","5500005555555559");

        TripCalculator calculator = new TripCalculator();
        Tap actualTap = calculator.findMatchingTapOff(tapStart, Arrays.asList(tapStart, tapTwo));

        assertNull(actualTap);
    }
}
