package com.vishwas.learning.hibernate.tutorial.dto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name="RENTAL_VEHICLE")
public class RentalVehicle {
    @Id
    @GeneratedValue
    @Column(name="RENTAL_VEHICLE_ID")
    private int id;

    @Column(name="RENTAL_VEHICLE_NAME")
    private String name;

    @ManyToMany
    @JoinTable(name="USER_RENTAL_VEHICLE", joinColumns = @JoinColumn(name="RENTAL_VEHICLE_ID"), inverseJoinColumns = @JoinColumn(name="USER_ID"))
    // As mappedBy used in USER entity then this side of relationship will create join table as mention above.
    // Remember hibernate need atlease 1 join table to manage manytomany relationship.
    private Collection<User> users = new ArrayList<User>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
