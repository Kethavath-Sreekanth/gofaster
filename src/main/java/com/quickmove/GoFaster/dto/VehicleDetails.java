package com.quickmove.GoFaster.dto;

import com.quickmove.GoFaster.entity.Vehicle;

public class VehicleDetails {

    private Vehicle vehicle;
    private double fare;
    private double estimatedTime;

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public double getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(double estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public VehicleDetails(Vehicle vehicle, double fare, double estimatedTime) {
        this.vehicle = vehicle;
        this.fare = fare;
        this.estimatedTime = estimatedTime;
    }

    public VehicleDetails() {
    }

    @Override
    public String toString() {
        return "VehicleDetails [vehicle=" + vehicle + ", fare=" + fare + ", estimatedTime=" + estimatedTime + "]";
    }
}

