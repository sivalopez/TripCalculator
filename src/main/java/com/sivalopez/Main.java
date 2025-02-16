package com.sivalopez;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Trip Calculator");
        // TODO: Get taps from CSV file.
        List<Tap> taps = Arrays.asList(
                new Tap(1, "22-01-2023 13:00:00", "ON", "Stop1", "Company1", "Bus37","5500005555555559"),
                new Tap(2, "22-01-2023 13:05:00", "OFF", "Stop2", "Company1", "Bus37","5500005555555559")
        );
        TripCalculator tripCalculator = new TripCalculator();
        List<Trip> trips = tripCalculator.calculateTrips(taps);
        TripCSVExporter.writeTripsToCSV(trips, "trips.csv");
    }
}