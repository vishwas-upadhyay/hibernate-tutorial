package com.vishwas.learning.hibernate.tutorial.D_hqllearning;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ParameterBinding {

    public static void execute (SessionFactory sessionFactory) {
        List list=null;
        try(Session session= sessionFactory.openSession()){
            System.out.println("*****ParameterBinding********");
            Query query = session.createQuery("from BasicUserDetailEntity where id=:id or name=:name");
            query.setParameter("id", 1);
            query.setParameter("name","update user");
             list=query.list();

        }
        finally {
            System.out.println("list items :"+ list.toString());
        }

    }
}
