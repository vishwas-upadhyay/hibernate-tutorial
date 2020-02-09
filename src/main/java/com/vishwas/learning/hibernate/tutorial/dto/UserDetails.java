package com.vishwas.learning.hibernate.tutorial.dto;

import com.vishwas.learning.hibernate.tutorial.A_embededobjects.Address;

import javax.persistence.*;
import java.util.Date;

/* Table created by default hibernate configuration i.e. hibernate.cfg.xml will have name like userdetails if name attribute is not setup
* i.e. @Entity (name="USER_DETAILS")
* but table created by spring data jpa will follow camel casing name pattern for table and column name hence created table would be
* user_detail.  */


@Entity
@Table (name="USER_DETAILS")

// So entity (persistent pojo class) and its linked table both are 2 different things.
// if you give @Entity (name="USER_DETAILS") then you have refer entity class with this name in hql queries
// So better to use @Table annotation for related table configuration.
public class UserDetails {

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

    @Temporal(TemporalType.DATE) // Only date part (12/05/2019) will be persisted in DB. Default is TIMESTAMP.
    @Column(name="DOB")
    private Date dob;
    @Transient // this field will not persist in DB
    @Column(name="SALARY")
    private int userSalary;
    @Lob // Large object - By default hibernate map String data type to varchar(255) in SQL. but if you have really big text which you are
    // not sure of then use @Lob. it will map to CLOB or BLOB data type of DB.
    //TODO: @Lob is persisting as number not text ?
    @Column(name="DESCR")
    protected String description;

    private Address address;

    @Embedded
    @AttributeOverride( name="city", column = @Column(name="office_city_name"))
    @AttributeOverride(name="state", column = @Column(name="office_state_name"))
    @AttributeOverride(name="street", column = @Column(name="office_street_name"))
    @AttributeOverride(name="pincode", column = @Column(name="office_pincode_number"))
    private Address officeAddress;

    public UserDetails() {
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getUserSalary() {
        return userSalary;
    }

    public void setUserSalary(int userSalary) {
        this.userSalary = userSalary;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(Address officeAddress) {
        this.officeAddress = officeAddress;
    }
}
