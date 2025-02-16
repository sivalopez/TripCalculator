package com.sivalopez;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Tap {
    private int id;
    private ZonedDateTime tapTime;
    private String tapType;
    private String stopId;
    private String companyId;
    private String busId;
    private String pan;

    public Tap(int id, String tapTime, String tapType, String stopId, String companyId, String busId, String pan) {
        this.id = id;
        this.tapTime = parseDate(tapTime);
        this.tapType = tapType;
        this.stopId = stopId;
        this.companyId = companyId;
        this.busId = busId;
        this.pan = pan;
    }

    private ZonedDateTime parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);
        return localDateTime.atZone(ZoneId.of("UTC"));
    }

    public ZonedDateTime getTapTime() {
        return this.tapTime;
    }

    public String getTapType() {
        return this.tapType;
    }

    public String getStopId() {
        return this.stopId;
    }

    public String getCompanyId() {
        return this.companyId;
    }

    public String getBusId() {
        return this.busId;
    }

    public String getPan() {
        return this.pan;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Tap tap = (Tap) obj;
        return id == tap.id && tapTime == tap.tapTime && tapType.equalsIgnoreCase(tap.tapType) &&
                stopId.equalsIgnoreCase(tap.stopId) && companyId.equalsIgnoreCase(tap.companyId) &&
                busId.equalsIgnoreCase(tap.busId) && pan.equals(tap.pan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tapTime, tapType, stopId, companyId, busId, pan);
    }

    @Override
    public String toString() {
        return "Tap {\n" +
                "id: " + this.id + ",\n" +
                "taptime: " + this.tapTime + ",\n" +
                "tapType: " + this.tapType + ",\n" +
                "stopId: " + this.stopId + ",\n" +
                "companyId: " + this.companyId + ",\n" +
                "busId: " + this.busId + ",\n" +
                "pan: " + this.pan + // TODO: Mask PAN
                "}";
    }
}
