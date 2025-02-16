package com.sivalopez;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;

public class Trip {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private long durationSecs;
    private String fromStopId;
    private String toStopId;
    // Using double for amount as there are no calculations required, but ideally should use BigDecimal.
    private double chargeAmount;
    private String companyId;
    private String busId;
    private String pan;
    private String status;

    public Trip() {
        // Empty constructor
    }

    public Trip(LocalDateTime startDate, LocalDateTime endDate, long durationSecs, String fromStopId, String toStopId,
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

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public long getDurationSecs() {
        return this.durationSecs;
    }

    public void setDurationSecs(long durationSecs) {
        this.durationSecs = durationSecs;
    }

    public String getFromStopId() {
        return this.fromStopId.trim();
    }

    public void setFromStopId(String fromStopId) {
        this.fromStopId = fromStopId;
    }

    public String getToStopId() {
        if (this.toStopId != null) {
            return this.toStopId.trim();
        }
        return this.toStopId;
    }

    public void setToStopId(String toStopId) {
        this.toStopId = toStopId;
    }

    public double getChargeAmount() {
        return this.chargeAmount;
    }

    public void setChargeAmount(double chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public String getCompanyId() {
        return this.companyId.trim();
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getBusId() {
        return this.busId.trim();
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getPan() {
        return this.pan.trim();
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getStatus() {
        return this.status;
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
        String pan = this.getPan();
        String lastFour = pan.substring(this.getPan().length() - 4);
        return "Trip { " +
                "startDate: " + this.startDate + ",\n" +
                "endDate: " + this.endDate + ",\n" +
                "durationSecs: " + this.durationSecs + ",\n" +
                "fromStopId: " + this.fromStopId + ",\n" +
                "toStopId: " + this.toStopId + ",\n" +
                "chargeAmount: " + this.chargeAmount + ",\n" +
                "companyId: " + this.companyId + ",\n" +
                "busId: " + this.busId + ",\n" +
                "pan: " + pan.substring(1, pan.length() - 4).replaceAll(".", "*") + lastFour + ",\n" +
                "status: " + this.status +
                " }";
    }
}
