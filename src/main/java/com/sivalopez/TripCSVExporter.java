package com.sivalopez;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TripCSVExporter {
    public static void writeTripsToCSV(List<Trip> trips, String filePath) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath),
                CSVWriter.DEFAULT_SEPARATOR, // Default separator (comma)
                CSVWriter.NO_QUOTE_CHARACTER, // No quotes around fields
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END)) {
            String[] header = {"Started", "Finished", "DurationSecs", "FromStopId", "ToStopId", "ChargeAmount", "CompanyId", "BusID", "PAN", "Status"};
            writer.writeNext(header);

            trips.forEach(trip -> {
                String[] data = {
                        trip.getStartDate().format(formatter),
                        trip.getEndDate().format(formatter),
                        String.valueOf(trip.getDurationSecs()),
                        trip.getFromStopId(),
                        trip.getToStopId(),
                        "$" + trip.getChargeAmount(),
                        trip.getCompanyId(),
                        trip.getBusId(),
                        trip.getPan(),
                        trip.getStatus()
                };
                writer.writeNext(data);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
