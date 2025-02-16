package com.sivalopez;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Tap {
    @CsvBindByName(column = "ID")
    private int id;
    @CsvBindByName(column = "DateTimeUTC")
    @CsvDate(" dd-MM-yyyy HH:mm:ss")
    private LocalDateTime tapTime;
    @CsvBindByName(column = "TapType")
    private String tapType;
    @CsvBindByName(column = "StopId")
    private String stopId;
    @CsvBindByName(column = "CompanyId")
    private String companyId;
    @CsvBindByName(column = "BusID")
    private String busId;
    @CsvBindByName(column = "PAN")
    private String pan;

    public Tap() {
        // empty constructor
    }

    public Tap(int id, String tapTime, String tapType, String stopId, String companyId, String busId, String pan) {
        this.id = id;
        this.tapTime = parseDate(tapTime);
        this.tapType = tapType;
        this.stopId = stopId;
        this.companyId = companyId;
        this.busId = busId;
        this.pan = pan;
    }

    private LocalDateTime parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return LocalDateTime.parse(dateString, formatter);
    }

    public LocalDateTime getTapTime() {
        return this.tapTime;
    }

    public String getTapType() {
        return this.tapType.trim();
    }

    public String getStopId() {
        return this.stopId.trim();
    }

    public String getCompanyId() {
        return this.companyId;
    }

    public String getBusId() {
        return this.busId.trim();
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
        String pan = this.getPan();
        String lastFour = pan.substring(this.getPan().length() - 4);
        return "Tap {\n" +
                "id: [" + this.id + "],\n" +
                "taptime: [" + this.getTapTime() + "],\n" +
                "tapType: [" + this.getTapType() + "],\n" +
                "stopId: [" + this.getStopId() + "],\n" +
                "companyId: [" + this.getCompanyId() + "],\n" +
                "busId: [" + this.getBusId() + "],\n" +
                "pan: [" + pan.substring(1, pan.length() - 4).replaceAll(".", "*") + lastFour + "]\n" +
                "}";
    }
}
