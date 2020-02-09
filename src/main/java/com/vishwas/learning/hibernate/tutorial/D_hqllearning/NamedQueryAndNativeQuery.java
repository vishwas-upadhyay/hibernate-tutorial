package com.vishwas.learning.hibernate.tutorial.D_hqllearning;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class NamedQueryAndNativeQuery {
    public static void execute(SessionFactory sessionFactory) {

        try(Session session= sessionFactory.openSession()){

            System.out.println("*********NamedQueryAndNativeQuery************");
            session.beginTransaction();
            Query query =session.getNamedQuery("BasicUserDetailEntity.byId");
            query.setParameter("id",17);
            List list =query.getResultList();
            System.out.println("Output of NamedQuery BasicUserDetailEntity.byId "+list);

            query=session.getNamedNativeQuery("BasicUserDetailEntity.byName");
            query.setParameter("name","update user");
             list =query.getResultList();
            System.out.println("Output of NativeNamedQuery BasicUserDetailEntity.byName "+list);




        }


    }
}
