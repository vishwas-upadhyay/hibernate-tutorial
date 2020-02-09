package com.vishwas.learning.hibernate.tutorial.E_caching;

import com.vishwas.learning.hibernate.tutorial.B_entityobjectstates.BasicUserDetailEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SecondLevelCache {

    public static void execute(SessionFactory sessionFactory) {

        try(Session session = sessionFactory.openSession()) {

            System.out.println("**********First level cache **********");

            session.beginTransaction();
            BasicUserDetailEntity basicUserDetailEntity = session.get(BasicUserDetailEntity.class, 17);
            System.out.println("DB user name : " + basicUserDetailEntity.getName());
//          Hibernate fire update query once you change the object value as it's in persist state within session.
//          if commit() is not fired then update statement will not be executed
            basicUserDetailEntity.setName("RAM");
//            This statement will not fire another query to DB as Hibernate manage first level/session cache
//            in session
            basicUserDetailEntity = session.get(BasicUserDetailEntity.class, 17);
            session.getTransaction().commit();
            session.close();


            }
        }

/* second level cache is not enable by default. you need to set up below property
<property name="hibernate.cache.use_second_level_cache">true</property>
<property name="hibernate.cache.region.factory_class">org.hibernate.cache.ehcache.internal.EhcacheRegionFactory</property>
* Also, once you enabled above configuration then you need to specify which entity and query you want to make second level
cache management. Here we are making BasicUserDetailEntity second level cached.
*
*
* */

        public static void secondLevelCacheExam(SessionFactory sessionFactory){

            System.out.println("**********Second level cache Example **********");

                Session session = sessionFactory.openSession();
                session.beginTransaction();
                BasicUserDetailEntity basicUserDetailEntity = session.get(BasicUserDetailEntity.class, 17);
                System.out.println("DB user name : " + basicUserDetailEntity.getName());
                System.out.println("Due to second level cache not able to see any select query here");
                session.getTransaction().commit();
                session.close();

                Session session1 = sessionFactory.openSession();
                session1.beginTransaction();
                basicUserDetailEntity = session1.get(BasicUserDetailEntity.class, 17);
                session1.getTransaction().commit();
                session1.close();



        }
    }

