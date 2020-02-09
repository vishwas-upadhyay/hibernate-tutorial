package com.vishwas.learning.hibernate.tutorial.dto;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table(name="BOOK")
public class Book {

    @Id
    @GeneratedValue
    @Column(name="BOOK_ID")
    private  int id;

    @Column(name="BOOK_NAME")
    private String name;

    @ManyToOne
    @NotFound (action = NotFoundAction.IGNORE)
    // This will create join column USER_ID in book table to manage this relationship
    // Sometime it may happen that book is not purchased by any user. In this scenario you will encounter exception when you try to
    // access getUser(). To handle this situation we can use @NotFound annotation to tell hibernate what action needs to be taken
    // if related entity object not found.
    @JoinColumn (name="USER_ID")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
