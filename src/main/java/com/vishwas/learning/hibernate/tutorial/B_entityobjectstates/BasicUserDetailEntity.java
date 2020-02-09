package com.vishwas.learning.hibernate.tutorial.B_entityobjectstates;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
//To make entity second level cache use this annotation
@Cacheable
//Specify cache strategy for entity if you give READ and try to update that entity anywhere else it will throw exception
// Read more at https://www.baeldung.com/hibernate-second-level-cache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.hibernate.annotations.SelectBeforeUpdate
// This annotation run select query to check object has changed or not. If object value is same DB then it will not run update query.
@NamedQuery(name="BasicUserDetailEntity.byId", query = "from BasicUserDetailEntity where id=:id")
@NamedNativeQuery(name="BasicUserDetailEntity.byName", query = "select * from BasicUserDetailEntity where name=:name",resultClass = BasicUserDetailEntity.class)
public class BasicUserDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String name;


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

    @Override
    public String toString() {
        return "BasicUserDetailEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
