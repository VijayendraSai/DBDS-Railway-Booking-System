package com.app.railway.model;

import java.io.Serializable;
import java.util.Objects;

public class ScheduleId implements Serializable {

    private int trainId;
    private int stationId;

    // Default constructor
    public ScheduleId() {}

    // Constructor with trainId and stationId
    public ScheduleId(int trainId, int stationId) {
        this.trainId = trainId;
        this.stationId = stationId;
    }

    // Getters and Setters
    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    // Override equals() and hashCode() to ensure proper handling of composite keys
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleId that = (ScheduleId) o;
        return trainId == that.trainId && stationId == that.stationId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainId, stationId);
    }
}
