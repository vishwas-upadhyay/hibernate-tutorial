package com.vishwas.learning.hibernate.tutorial.B_entityobjectstates;

import org.hibernate.SessionFactory;
import org.hibernate.Session;

//Please refer google drive Hibernate doc to understand object state theory
public class EntityObjectStatesConcept {

    public static void objectDetachAndReattched (SessionFactory sessionFactory){

        BasicUserDetailEntity user = new BasicUserDetailEntity();
        user.setName("update user");

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();

        // user object in detached state now  as session is closed

        //user.setName("updated object post session close");

        session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();

    }
}
