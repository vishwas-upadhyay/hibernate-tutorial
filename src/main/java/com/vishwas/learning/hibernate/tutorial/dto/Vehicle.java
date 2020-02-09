package com.vishwas.learning.hibernate.tutorial.dto;

import javax.persistence.*;

@Entity
@Table(name="VEHICLE")
public class Vehicle {
    @Id
    @GeneratedValue
    @Column(name="VEHICLE_ID")
    private int vehicleId;

    @Column(name="VEHICLE_NAME")
    private String vehicleName;

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }
}
