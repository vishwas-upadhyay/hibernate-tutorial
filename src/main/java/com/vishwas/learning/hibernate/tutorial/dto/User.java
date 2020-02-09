package com.vishwas.learning.hibernate.tutorial.dto;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="USER_RELETIONSHIP")
public class User {

    @Id
    @GeneratedValue
    @Column(name="ID")
    private int id;

    @Column(name="NAME")
    private String name;

    @OneToOne
    //Todo: @OneToOne relation creates join column(vehicle_id) in USER table to keep entry for vehicle.
    @JoinColumn(name="VEHICLE_ID")
    private Vehicle vehicle;

    @OneToMany (mappedBy = "user" , cascade = CascadeType.ALL)
    //TODO: @OneToMany relation creates new table or join table to manage relationship. In real world application we can use mappedby
    // attribute to avoid join table creation by hibernate

    // cascade attribute helps you to cascade any action which is taken on parent entity to all related entities. this is how we don't need
    // to save books object explicitly in session. if you just save user object it will also save all related book objects.
    // to save entity object if you have used cascade attribute use persist() method not save()
    // Todo what is the diffirence between persist() and save() ?



//   @JoinTable(name="USER_BOOK", joinColumns = @JoinColumn(name="USER_ID"), inverseJoinColumns = @JoinColumn(name="BOOK_ID"))
    private List<Book> book = new ArrayList<>();

    @ManyToMany (mappedBy = "users")
    // This relationship will create join table as USER_RELETIONSHIP_RENTAL_VEHICLE (User_ID, rentalVehicles_RENTAL_VEHICLE_ID) if you
    // don't use mappedBy
    // @ManyToMany relationship will need atleast 1 join table. So to avoid join table on other side of relation ship we can use mappedby.

    private Collection<RentalVehicle> rentalVehicles = new ArrayList<RentalVehicle>();

    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

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

    public Collection<RentalVehicle> getRentalVehicles() {
        return rentalVehicles;
    }

    public void setRentalVehicles(Collection<RentalVehicle> rentalVehicles) {
        this.rentalVehicles = rentalVehicles;
    }
}
