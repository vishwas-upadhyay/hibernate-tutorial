package com.vishwas.learning.hibernate.tutorial;

import com.vishwas.learning.hibernate.tutorial.dto.UserDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HibernateBasicSave {

    @PostMapping("/save")
    public void saveUser(){

        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(2);
        userDetails.setUserName("Dolu");
       // Below code is working because it's traditional hibernate way of persisting entity
       // it requires hibernate.cfg.xml file, to configure sessionfactory object
       SessionFactory sessionFactory= new Configuration().configure().buildSessionFactory();
       Session session=sessionFactory.openSession();
       session.beginTransaction();
       session.save(userDetails);
       session.getTransaction().commit();

    }

}
