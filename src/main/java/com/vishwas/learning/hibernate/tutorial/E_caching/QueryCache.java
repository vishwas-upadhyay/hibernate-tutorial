package com.vishwas.learning.hibernate.tutorial.E_caching;

import com.vishwas.learning.hibernate.tutorial.B_entityobjectstates.BasicUserDetailEntity;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class QueryCache {

    /* By default query are not second level cached. you have to setup the property (hibernate.cache.use_query_cache) for that
    *  query.setCacheable(true); will add query object to managed by second level cache. setCacheable () indicates 2 things:
    *  1. if similar query already exist then use that.
    *  2. if not then manage it  by second level
    *    So each query which you want to handle by second level cache must have this method.
    *
    * Query cache should be used very care fully as query can return huge data as it has association with other entities
    * some best practice are here https://www.baeldung.com/hibernate-second-level-cache
    *
    * */

    public static void execute(SessionFactory sessionFactory) {

        System.out.println("********Query Cache Example**********");
        Session session1= sessionFactory.openSession();
        session1.beginTransaction();
        Query query =session1.createQuery("from BasicUserDetailEntity where id=17");
        query.setCacheable(true);
        List <BasicUserDetailEntity> basicUserDetailEntities = query.list();
        session1.getTransaction().commit();
        session1.close();


        Session session2= sessionFactory.openSession();
        session2.beginTransaction();
        Query query2 =session2.createQuery("from BasicUserDetailEntity where id=17");
        query2.setCacheable(true);
        basicUserDetailEntities = query2.list();
        session2.getTransaction().commit();
        session2.close();
        System.out.println("Only one select query ran as both query object managed by second level cache");




    }
}
