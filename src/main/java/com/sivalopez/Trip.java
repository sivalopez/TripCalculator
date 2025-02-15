package com.sivalopez;

import java.time.ZonedDateTime;
import java.util.Objects;

public class Trip {
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private int durationSecs;

    public Trip(ZonedDateTime startDate, ZonedDateTime endDate, int durationSecs) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.durationSecs = durationSecs;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Trip trip = (Trip) obj;
        return startDate == trip.startDate &&
                endDate == trip.endDate &&
                durationSecs == trip.durationSecs;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, durationSecs);
    }

    @Override
    public String toString() {
        return this.startDate + " " + this.endDate + " " + this.durationSecs;
    }
}
