package com.sivalopez;

import java.time.ZonedDateTime;
import java.util.Objects;

public class Trip {
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private long durationSecs;
    private String fromStopId;
    private String toStopId;
    private double chargeAmount;
    private String companyId;
    private String busId;
    private String pan;
    private String status;

    public Trip() {
        // Empty constructor
    }

    public Trip(ZonedDateTime startDate, ZonedDateTime endDate, long durationSecs, String fromStopId, String toStopId,
                double chargeAmount, String companyId, String busId, String pan, String status) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.durationSecs = durationSecs;
        this.fromStopId = fromStopId;
        this.toStopId = toStopId;
        this.chargeAmount = chargeAmount;
        this.companyId = companyId;
        this.busId = busId;
        this.pan = pan;
        this.status = status;
    }

    public ZonedDateTime getStartDate() {
        return this.startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return this.endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public void setDurationSecs(long durationSecs) {
        this.durationSecs = durationSecs;
    }

    public void setFromStopId(String fromStopId) {
        this.fromStopId = fromStopId;
    }

    public void setToStopId(String toStopId) {
        this.toStopId = toStopId;
    }

    public void setChargeAmount(double chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Trip trip = (Trip) obj;
        return startDate == trip.startDate &&
                endDate == trip.endDate &&
                durationSecs == trip.durationSecs &&
                fromStopId.equalsIgnoreCase(trip.fromStopId) &&
                toStopId.equalsIgnoreCase(trip.toStopId) &&
                chargeAmount == trip.chargeAmount &&
                companyId.equalsIgnoreCase(trip.companyId) &&
                busId.equalsIgnoreCase(trip.busId) &&
                pan.equalsIgnoreCase(trip.pan) &&
                status.equalsIgnoreCase(trip.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, durationSecs, fromStopId, toStopId, chargeAmount, companyId, busId, pan, status);
    }

    @Override
    public String toString() {
        return "Trip {" +
                "startDate: " + this.startDate + ",\n" +
                "endDate: " + this.endDate + ",\n" +
                "durationSecs: " + this.durationSecs + ",\n" +
                "fromStopId: " + this.fromStopId + ",\n" +
                "toStopId: " + this.toStopId + ",\n" +
                "chargeAmount: " + this.chargeAmount + ",\n" +
                "companyId: " + this.companyId + ",\n" +
                "busId: " + this.busId + ",\n" +
                "pan: " + this.pan + ",\n" +
                "status: " + this.status + ",\n" +
                "}";
    }
}
