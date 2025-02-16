package com.sivalopez;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Tap> taps;
        try (FileReader reader = new FileReader("taps.csv")) {
            CsvToBean<Tap> csvToBean = new CsvToBeanBuilder<Tap>(reader)
                    .withType(Tap.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            taps = csvToBean.parse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!taps.isEmpty()) {
            TripCalculator tripCalculator = new TripCalculator();
            List<Trip> trips = tripCalculator.calculateTrips(taps);
            TripCSVExporter.writeTripsToCSV(trips, "trips.csv");
        }
    }
}