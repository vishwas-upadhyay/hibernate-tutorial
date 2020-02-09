package com.vishwas.learning.hibernate.tutorial.D_hqllearning;

import com.vishwas.learning.hibernate.tutorial.B_entityobjectstates.BasicUserDetailEntity;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HQLandQuaryObject {

    public static void execute(SessionFactory sessionFactory) {

        try(Session session= sessionFactory.openSession()){

            System.out.println("****HQLandQuaryObject Execution*****");
            session.beginTransaction();
            Query query= session.createQuery("from BasicUserDetailEntity");
            query.setFirstResult(5);
            query.setMaxResults(4);
            List<BasicUserDetailEntity> list=query.list();
            session.getTransaction().commit();


        }



    }
}
