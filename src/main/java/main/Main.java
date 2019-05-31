package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.security.auth.login.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

import model.*;

public class Main {

	private static final Logger log = Logger.getLogger(Main.class);

        // TODO Check PersistenceUnit Definition
	@PersistenceUnit(
			unitName = "westbahn"
	)
	private static EntityManagerFactory sessionFactory;
	@PersistenceContext
	private static EntityManager entitymanager;
	
	static SimpleDateFormat dateForm = new SimpleDateFormat("dd.MM.yyyy");
	static SimpleDateFormat timeForm = new SimpleDateFormat("dd.MM.yyyy mm:hh");

	private Main() {
		super();
	}

	public static void main(String[] args) {
		
		BasicConfigurator.configure();
		
		try {
			log.info("Starting \"Mapping Perstistent Classes and Associations\" (task1)");
			sessionFactory = Persistence.createEntityManagerFactory("westbahn");
			entitymanager = sessionFactory.createEntityManager();
			fillDB(entitymanager);
			task01();
			log.info("Starting \"Working with JPA-QL and the Hibernate Criteria API\" (task2)");
			log.setLevel(Level.OFF);
			task02();
			task02a();
			//task02b();
			//task02c();
			log.setLevel(Level.ALL);
			task03(entitymanager);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (entitymanager.getTransaction().isActive())
				entitymanager.getTransaction().rollback();
			entitymanager.close();
			sessionFactory.close();
		}
	}

	public static void fillDB(EntityManager em) throws ParseException {
		em.getTransaction().begin();
		List<Bahnhof> list = new ArrayList<Bahnhof>();
		list.add(new Bahnhof("WienHbf", 0, 0, 0, true));
		list.add(new Bahnhof("SalzburgHbf", 20, 60, 120, true));
		list.add(new Bahnhof("Amstetten", 40, 124, 169, false));
		list.add(new Bahnhof("Linz-Ost", 140, 320, 250, false));
		list.add(new Bahnhof("Huetteldorf", 3, 5, 19, false));
		list.add(new Bahnhof("Wels-Zentrum", 102, 400, 250, true));
		for (Bahnhof b : list)
			em.persist(b);
		em.flush();



		List<Benutzer> list2 = new ArrayList<Benutzer>();

		list2.add(new Benutzer("Fabian","Traxler","ftraxler@student.tgm.ac.at","1234"));
		list2.add(new Benutzer("David","Kostroun","dkostroun@student.tgm.ac.at","1234"));
		list2.add(new Benutzer("Karim","Omar","komar@student.tgm.ac.at","1234"));
		list2.add(new Benutzer("Said","Gagajew","sgagajew@student.tgm.ac.at","1234"));
		list2.add(new Benutzer("Shai","Dzindzihashvili","sdzindzihashvi@student.tgm.ac.at","1234"));

		for(Benutzer b:list2)
			em.persist(b);
		em.flush();


		List<Zug> list3 = new ArrayList<Zug>();

		list3.add(new Zug(new Date(),150,10,5,list.get(0),list.get(1)));
		list3.add(new Zug(new Date(),200,15,10,list.get(1),list.get(2)));
		list3.add(new Zug(new Date(),80,6,3,list.get(1),list.get(0)));
		list3.add(new Zug(new Date(),500,25,20,list.get(3),list.get(1)));
		list3.add(new Zug(new Date(),800,30,25,list.get(0),list.get(1)));
		list3.add(new Zug(new Date(),50,10,5,list.get(0),list.get(1)));

		for(Zug z:list3)
			em.persist(z);
		em.flush();



		Strecke strecke = new Strecke();
		Zahlung zahlung = new Maestro();


		Reservierung rv = new Reservierung(new Date(),null,null,null,list2.get(0),zahlung);
		em.persist(rv);
		em.flush();

		em.getTransaction().commit();
	}

	public static void task01() throws ParseException, InterruptedException {
	}

	public static <T> void task02() throws ParseException {
		Query q = entitymanager.createNamedQuery("Bahnhof.getAll");

		List<?> l = q.getResultList();

		for (Object b : l) {
			Bahnhof bhf = null;
			if (b instanceof Bahnhof) {
				bhf = (Bahnhof) b;
				System.out.println("Bahnhof: " + bhf.getName()+" ID:"+bhf.getID());
			}
		}


	}

	public static void task02a() throws ParseException {
		Query q = entitymanager.createNamedQuery("Benutzer.getAll");

		List<?> l = q.getResultList();

		for (Object b: l) {
			Benutzer ben=null;
			if(b instanceof Benutzer) {
				ben = (Benutzer) b;
				System.out.println(ben.toString());
			}

		}

		List<?> l2;
		Query q2 = entitymanager.createNamedQuery("Reservierung.getAll");
		l2 = q2.getResultList();
		for (Object r: l2) {
			Reservierung reservierung=null;
			if(r instanceof Reservierung){
				reservierung= (Reservierung) r;
				System.out.println(reservierung.toString());

			}

		}
	}

	public static void task02b() throws ParseException {
	}

	public static void task02c() throws ParseException {
	}

	public static void task03(EntityManager em) {
	}

	public static void validate(Object obj) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Object>> violations = validator.validate(obj);
		for (ConstraintViolation<Object> violation : violations) {
			log.error(violation.getMessage());
			System.out.println(violation.getMessage());
		}
	}
}
