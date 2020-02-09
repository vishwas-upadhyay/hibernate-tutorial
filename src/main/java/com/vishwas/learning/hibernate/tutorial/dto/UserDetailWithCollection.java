package com.vishwas.learning.hibernate.tutorial.dto;

import com.vishwas.learning.hibernate.tutorial.A_embededobjects.Address;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/* Table created by default hibernate configuration i.e. hibernate.cfg.xml will have name like userdetails if name attribute is not setup
* i.e. @Entity (name="USER_DETAILS")
* but table created by spring data jpa will follow camel casing name pattern for table and column name hence created table would be
* user_detail.  */


@Entity
@Table (name="USER_DETAILS_COLLECTION")

// So entity (persistent pojo class) and its linked table both are 2 different things.
// if you give @Entity (name="USER_DETAILS") then you have refer entity class with this name in hql queries
// So better to use @Table annotation for related table configuration.
public class UserDetailWithCollection {

    @Id @GeneratedValue (strategy = GenerationType.AUTO)
    // @GeneratedValue generats primary key as per passed strategy
    // Auto - Hibernate will decide the next primary key value for you
    // Sequence - You can pass sequence object name of DB to get next value
    // Identity - Some DB has special feature called Identity which can give you next value
    // Table - Hibernate will create table to maintain and store last generate value for primary key.
    @Column(name="USER_ID")
    private int userId;

    @Column (name="NAME")
    private String userName;

    @ElementCollection (fetch = FetchType.EAGER)
    // This annotation used for saving collection in DB by creating join table.

    //TODO: ---Proxy objects and Eager and Lazy fetch Type-----
    // Whenever any entity has another entity or collection as it's part then fetching all data at once for related entity/collection
    // could be costlier hence we need to define fetch type as per our need. Hibernate by default use FetchType.lazy.
    // Hibernate creates proxy class (Sub class of entity class) which is directly linked to DB. So when you call getAddress() of entity class (while session
    // .get() hibernate handover you proxy class object which has same method as entity class. these method for first level class attribute
    // calls parent methods like super();). Hibernate internally
    // calls entity proxy class getAddress() which fetch all the related records of address from DB and populate address attribute of actual entity
    // class and return the address objects.
    @JoinTable (name="USER_ADDRESS", joinColumns = @JoinColumn(name="USER_ID"))
    // Hibernate by default create join table as entity name + attribute name i.e. UserDetailWithCollectionaddress
    // To over write this behaviour we can use @JoinTable and @JoinColumn
    @GenericGenerator(name="hilo-gen",strategy = "increment")
    // To support primary key of join table we need some generator
    // hilo-gen is obsolete. So we are using generator
    @CollectionId(columns = @Column(name="ADDRESS_ID"), generator =("hilo-gen") , type =@Type(type="int"))
    // This annotation is used for creating primary key for join table.
    private Collection<Address> address = new ArrayList<Address>();
    // We can't give Arraylist in declaration, It has to be Collection always because Hibernate accept only interface type
    // not the implementation like HashSet ArrayList etc.




    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Collection<Address> getAddress() {
        return address;
    }

    public void setAddress(Collection<Address> address) {
        this.address = address;
    }
}
