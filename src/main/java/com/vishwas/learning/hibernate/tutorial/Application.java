package com.vishwas.learning.hibernate.tutorial;

import com.vishwas.learning.hibernate.tutorial.D_hqllearning.HQLandQuaryObject;
import com.vishwas.learning.hibernate.tutorial.D_hqllearning.NamedQueryAndNativeQuery;
import com.vishwas.learning.hibernate.tutorial.D_hqllearning.ParameterBinding;
import com.vishwas.learning.hibernate.tutorial.E_caching.QueryCache;
import com.vishwas.learning.hibernate.tutorial.E_caching.SecondLevelCache;
import com.vishwas.learning.hibernate.tutorial.dto.*;
import com.vishwas.learning.hibernate.tutorial.A_embededobjects.Address;
import com.vishwas.learning.hibernate.tutorial.B_entityobjectstates.EntityObjectStatesConcept;
import com.vishwas.learning.hibernate.tutorial.C_inheritance.FourWheeler;
import com.vishwas.learning.hibernate.tutorial.C_inheritance.TwoWheeler;
import com.vishwas.learning.hibernate.tutorial.C_inheritance.VehicleInheritance;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		UserDetails userDetails = new UserDetails();
		userDetails.setUserId(1);
		userDetails.setUserName("First user name");
		userDetails.setDob(new Date());
		userDetails.setUserSalary(45000);
		userDetails.setDescription("some very long text");
		// Below code is working because it's traditional hibernate way of persisting entity
		// it requires hibernate.cfg.xml file, to configure sessionfactory object
		SessionFactory sessionFactory= new Configuration().configure().buildSessionFactory();
		// SessionFactory object is costly and it is maintained as application object (one object per application)

		try (Session session=sessionFactory.openSession()){
		session.beginTransaction();
		session.save(userDetails);
		session.getTransaction().commit();

		// Get the object by Hibernate
		userDetails = null;
		userDetails = session.get(UserDetails.class, 1);
		}
       finally {
			System.out.println( "Is session factory closed: " + sessionFactory.isClosed() );
		}

		System.out.println("User name is " + userDetails.getUserName());

		embebedObject(sessionFactory);

		savingCollection(sessionFactory);

		oneToOneRelationship(sessionFactory);

		oneToManyRelationship(sessionFactory);

		manyToOneRelationship(sessionFactory);

		manyToManyRelationship(sessionFactory);

		singleTableInheritanceStrategy(sessionFactory);

		EntityObjectStatesConcept.objectDetachAndReattched(sessionFactory);

		HQLandQuaryObject.execute(sessionFactory);

		ParameterBinding.execute(sessionFactory);

		NamedQueryAndNativeQuery.execute(sessionFactory);

		SecondLevelCache.execute(sessionFactory);

		SecondLevelCache.secondLevelCacheExam(sessionFactory);

		QueryCache.execute(sessionFactory);
	}




	static void embebedObject ( SessionFactory sessionFactory){

		try(Session session= sessionFactory.openSession()){

			UserDetails user = new UserDetails();

			user.setDescription("description");
			user.setDob(new Date());
			user.setUserSalary(90000);
			user.setUserName("User name");

//			Address is the embeded object here
			Address address = new Address();
			address.setCity("City name");
			address.setPincode("480001");
			address.setState("state name");
			address.setStreet("street name");

//			office address embeded object
			Address officeAddress= new Address();
			officeAddress.setStreet("office street");
			officeAddress.setState("office state");
			officeAddress.setPincode("office pincode");
			officeAddress.setCity("office city ");

			user.setAddress(address);
			user.setOfficeAddress(officeAddress);

			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
			session.close();

		}

	}

	private static void savingCollection(SessionFactory sessionFactory) {

		try(Session session = sessionFactory.openSession()){

			UserDetailWithCollection userDetailWithCollection = new UserDetailWithCollection();
			userDetailWithCollection.setUserName("Collection User Name");

			Address address1 = new Address();
			address1.setCity("City 1");
			address1.setPincode("Pincode 1");
			address1.setState("State 1");
			address1.setStreet("Street 1");

			Address address2 = new Address();
			address2.setCity("City 2");
			address2.setPincode("Pincode 2");
			address2.setState("State 2");
			address2.setStreet("Street 2");

			userDetailWithCollection.getAddress().add(address2);
			userDetailWithCollection.getAddress().add(address1);

			session.beginTransaction();
			session.save(userDetailWithCollection);
			session.getTransaction().commit();



		}


	}

	private static void oneToOneRelationship(SessionFactory sessionFactory) {

		try(Session session = sessionFactory.openSession()){

			User user = new User();
			user.setName("User Name");

			Vehicle vehicle = new Vehicle();
			vehicle.setVehicleName("CAR");

			user.setVehicle(vehicle);

			session.beginTransaction();
			session.save(user);
			session.save(vehicle);

			session.getTransaction().commit();



		}
	}

	private static void oneToManyRelationship(SessionFactory sessionFactory) {

		try(Session session = sessionFactory.openSession()){

			User user = new User();
			user.setName("One to many relationship");

			Vehicle vehicle = new Vehicle();
			vehicle.setVehicleName("JEEP");

			user.setVehicle(vehicle);

			Book book1 = new Book();
			book1.setName("Book 1");

			Book book2 = new Book();
			book2.setName("Book 2");

			user.getBook().add(book1);
			user.getBook().add(book2);

			session.beginTransaction();
			session.persist(user);
			session.save(vehicle);
//			session.save(book1);
//			session.save(book2);
//   As we have used cascade attribute for user book relationship then we can comment above line and use persist() on user
//	it will save book object as well.

			session.getTransaction().commit();
		}
	}


	private static void manyToOneRelationship(SessionFactory sessionFactory) {

		try(Session session = sessionFactory.openSession()){

			User user = new User();
			user.setName("Many to One relationship");

			Vehicle vehicle = new Vehicle();
			vehicle.setVehicleName("Bike");

			user.setVehicle(vehicle);

			Book book1 = new Book();
			book1.setName("Book 3");

			Book book2 = new Book();
			book2.setName("Book 4");

			user.getBook().add(book1);
			user.getBook().add(book2);
			book1.setUser(user);
			book2.setUser(user);


			session.beginTransaction();
			session.save(user);
			session.save(vehicle);
			session.save(book1);
			session.save(book2);

			session.getTransaction().commit();
		}
	}

	private static void manyToManyRelationship(SessionFactory sessionFactory) {

		try(Session session = sessionFactory.openSession()){

			User user = new User();
			user.setName("Many to Many relationship");

			RentalVehicle rentalVehicle = new RentalVehicle();
			rentalVehicle.setName("Vehicle 1");

			RentalVehicle rentalVehicle1 = new RentalVehicle();
			rentalVehicle1.setName("Vehicle 2");

			user.getRentalVehicles().add(rentalVehicle);
			user.getRentalVehicles().add(rentalVehicle1);

			rentalVehicle.getUsers().add(user);
			rentalVehicle1.getUsers().add(user);



			session.beginTransaction();
			session.save(user);
			session.save(rentalVehicle);
			session.save(rentalVehicle1);

			session.getTransaction().commit();
		}
	}


	private static void singleTableInheritanceStrategy(SessionFactory sessionFactory) {

		try(Session session = sessionFactory.openSession())
		{
			VehicleInheritance vehicleInheritance = new VehicleInheritance();
			vehicleInheritance.setId(1);
			vehicleInheritance.setName("Vehicle");

			TwoWheeler twoWheeler = new TwoWheeler();
			twoWheeler.setSteeringHandle("YAMAHA");
			twoWheeler.setId(2);
			twoWheeler.setName("BIKE");

			FourWheeler fourWheeler = new FourWheeler();
			fourWheeler.setSteeringWheel("PORSCHE WHEEL");
			fourWheeler.setId(3);
			fourWheeler.setName("PORSCHE");

			session.beginTransaction();
			session.save(vehicleInheritance);
			session.save(twoWheeler);
			session.save(fourWheeler);
			session.getTransaction().commit();


		}
	}
}
