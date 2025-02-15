package com.sivalopez;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class TripCalculator {
    public TripCalculator() {

    }

    public List<Trip> calculateTrips(List<Tap> allTaps) {
        List<Trip> allTrips = new ArrayList<>();

        Map<String, List<Tap>> tapGroups = getTapGroupsByPan(allTaps);
        // For each Pan group, find ON tap and then find matching OFF tap.
        tapGroups.forEach((pan, taps) -> {
            allTrips.addAll(calculateTripsByPan(pan, taps));
        });

        return allTrips;
    }

    public List<Trip> calculateTripsByPan(String pan, List<Tap> taps) {
        List<Trip> panTrips = new ArrayList<>();
        // sort by datetime to find the first match.
        taps.sort(Comparator.comparing(Tap::getTapTime));
        for (int i = 0; i < taps.size(); i++) {
            Tap tap = taps.get(i);
            // Only want to find matching OFF tap for an ON tap.
            // Assumption: An OFF tap shouldn't exist without a matching ON tap.
            if (tap.getTapType().equals("OFF")) {
                continue;
            }

            Tap tapOff = findMatchingTapOff(tap, taps);
            String tripStatus = determineTripStatus(tap, tapOff);

            Trip trip = new Trip();
            trip.setPan(pan);
            trip.setStartDate(tap.getTapTime());
            trip.setFromStopId(tap.getStopId());
            trip.setCompanyId(tap.getCompanyId());
            trip.setBusId(tap.getBusId());
            trip.setStatus(tripStatus);
            if (!tripStatus.equalsIgnoreCase("INCOMPLETE")) {
                trip.setEndDate(tapOff.getTapTime());
                trip.setToStopId(tapOff.getStopId());
                trip.setDurationSecs(Duration.between(trip.getStartDate(), trip.getEndDate()).getSeconds());
            }

            panTrips.add(trip);
        }
        return panTrips;
    }

    public String determineTripStatus(Tap tapOn, Tap tapOff) {
        String status = "COMPLETED";

        if (tapOff == null) {
            status = "INCOMPLETE";
        } else if (tapOn.getStopId().equalsIgnoreCase(tapOff.getStopId())) {
            // Assumption: Trip is cancelled when customer taps OFF at the same stop within 5 minutes of tapping ON.
            long tripDuration = Duration.between(tapOn.getTapTime(), tapOff.getTapTime()).getSeconds();
            if (tripDuration < 300) {
                status = "CANCELLED";
            }
        }

        return status;
    }

    public Map<String, List<Tap>> getTapGroupsByPan(List<Tap> allTaps) {
        return allTaps.stream().collect(Collectors.groupingBy(Tap::getPan));
    }

    public Tap findMatchingTapOff(Tap tapOn, List<Tap> taps) {
        Tap defaultTap = null;
        return taps.stream()
                .filter(tap -> tap.getTapTime().toLocalDate().equals(tapOn.getTapTime().toLocalDate()))
                .filter(tap -> tap.getBusId().equals(tapOn.getBusId()))
                .filter(tap -> tap.getTapType().equalsIgnoreCase("OFF"))
                .filter(tap -> !tap.getStopId().equalsIgnoreCase(tapOn.getStopId()))
                .findFirst().orElse(defaultTap);
    }
}
