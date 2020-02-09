package com.vishwas.learning.hibernate.tutorial.C_inheritance;

import javax.persistence.Entity;

@Entity
//@DiscriminatorValue("Car")
public class FourWheeler extends VehicleInheritance {

    private String steeringWheel;

    public String getSteeringWheel() {
        return steeringWheel;
    }

    public void setSteeringWheel(String steeringWheel) {
        this.steeringWheel = steeringWheel;
    }
}
