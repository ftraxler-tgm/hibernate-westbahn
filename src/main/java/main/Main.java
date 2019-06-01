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
			log.setLevel(Level.INFO);
			//task02();
			task02a();
			task02b();
			task02c();
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
		List<Bahnhof> bahnhoefe = new ArrayList<Bahnhof>();
		bahnhoefe.add(new Bahnhof("WienHbf", 0, 0, 0, true));
		bahnhoefe.add(new Bahnhof("SalzburgHbf", 20, 60, 120, true));
		bahnhoefe.add(new Bahnhof("Amstetten", 40, 124, 169, false));
		bahnhoefe.add(new Bahnhof("Linz-Ost", 140, 320, 250, false));
		bahnhoefe.add(new Bahnhof("Huetteldorf", 3, 5, 19, false));
		bahnhoefe.add(new Bahnhof("Wels-Zentrum", 102, 400, 250, true));
		for (Bahnhof b : bahnhoefe)
			em.persist(b);
		em.flush();





		List<Benutzer> benutzer = new ArrayList<Benutzer>();

		benutzer.add(new Benutzer("Fabian","Traxler","ftraxler@student.tgm.ac.at","1234","4321",12L,null,null));
		benutzer.add(new Benutzer("David","Kostroun","dkostroun@student.tgm.ac.at","1234","4321",12L,null,null));
		benutzer.add(new Benutzer("Karim","Omar","komar@student.tgm.ac.at","1234","4321",12L,null,null));
		benutzer.add(new Benutzer("Said","Gagajew","sgagajew@student.tgm.ac.at","1234","4321",12L,null,null));
		benutzer.add(new Benutzer("Shai","Dzindzihashvili","sdzindzihashvi@student.tgm.ac.at","1234","4321",12L,null,null));

		for(Benutzer b:benutzer)
			em.persist(b);
		em.flush();


		List<Zug> zuege = new ArrayList<Zug>();

		zuege.add(new Zug(new Date(),150,10,5,bahnhoefe.get(0),bahnhoefe.get(1)));
		zuege.add(new Zug(new Date(),200,15,10,bahnhoefe.get(1),bahnhoefe.get(2)));
		zuege.add(new Zug(new Date(),80,6,3,bahnhoefe.get(1),bahnhoefe.get(0)));
		zuege.add(new Zug(new Date(),500,25,20,bahnhoefe.get(3),bahnhoefe.get(1)));
		zuege.add(new Zug(new Date(),800,30,25,bahnhoefe.get(0),bahnhoefe.get(1)));
		zuege.add(new Zug(new Date(),50,10,5,bahnhoefe.get(4),bahnhoefe.get(0)));

		for(Zug z:zuege)
			em.persist(z);
		em.flush();

		List<Strecke> strecken = new ArrayList<Strecke>();

		strecken.add(new Strecke(bahnhoefe.get(0),null,bahnhoefe.get(1)));//WienHbf-SalzburgHbf
		strecken.add(new Strecke(bahnhoefe.get(1),null,bahnhoefe.get(0)));//SalzburgHbf-WienHbf
		strecken.add(new Strecke(bahnhoefe.get(0),bahnhoefe.get(2),bahnhoefe.get(3)));//WienHbf-Linz-Ost
		strecken.add(new Strecke(bahnhoefe.get(4),null,bahnhoefe.get(0)));

		for(Strecke str:strecken)
			em.persist(str);
		em.flush();

		ZeitkartenTyp[] ztyp = ZeitkartenTyp.values();


		List<Ticket> tickets = new ArrayList<Ticket>();
		tickets.add(new Einzelticket(strecken.get(2),new Maestro(),null));
		tickets.add(new Einzelticket(strecken.get(1),new Kreditkarte(),null));
		tickets.add(new Einzelticket(strecken.get(3),new Kreditkarte(),null));
		tickets.add(new Einzelticket(strecken.get(1),new Maestro(),null));
		for (Ticket t: tickets)
			em.persist(t);

		em.flush();



		List<Reservierung> reservierungen = new ArrayList<Reservierung>();

		reservierungen.add(new Reservierung(new Date(),null,zuege.get(0),strecken.get(0),benutzer.get(0),new Maestro()));
		reservierungen.add(new Reservierung(new Date(),null,zuege.get(2),strecken.get(1),benutzer.get(1),new Kreditkarte()));
		reservierungen.add(new Reservierung(new Date(),null,zuege.get(5),strecken.get(0),benutzer.get(3),new Maestro()));
		reservierungen.add(new Reservierung(new Date(),null,zuege.get(0),strecken.get(0),benutzer.get(2),new Kreditkarte()));
		reservierungen.add(new Reservierung(new Date(),null,zuege.get(2),strecken.get(1),benutzer.get(4),new Maestro()));


		for (Reservierung r: reservierungen) {
			em.persist(r);

		}
		em.flush();

		em.getTransaction().commit();
	}

	public static void task01() throws ParseException, InterruptedException {

		Query queryBenutzerAll = entitymanager.createNamedQuery("Benutzer.getAll");


		//Alle Benutzer werden aus der DB geholt
		List<?> benutzerA = queryBenutzerAll.getResultList();

		for (Object b: benutzerA) {
			Benutzer ben=null;
			if(b instanceof Benutzer) {
				ben = (Benutzer) b;
				log.info(ben.toString());
			}

		}


		//Alle Reservierungen werden aus der DB geholt
		List<?> reservierungA;
		Query queryReservierungAll = entitymanager.createNamedQuery("Reservierung.getAll");
		reservierungA = queryReservierungAll.getResultList();
		for (Object r: reservierungA) {
			Reservierung reservierung=null;
			if(r instanceof Reservierung){
				reservierung= (Reservierung) r;
				log.info(reservierung.toString());

			}

		}



		//Alle Tickets werden aus der DB geholt
		List<?> ticketsA;
		Query queryTicketsAll = entitymanager.createNamedQuery("Ticket.getAll");
		ticketsA = queryTicketsAll.getResultList();
		for (Object r: ticketsA) {
			Ticket ticket=null;
			if(r instanceof Ticket){
				ticket= (Ticket) r;
				log.info(ticket.toString());

			}

		}

	}

	public static <T> void task02() throws ParseException {
		Query q = entitymanager.createNamedQuery("Bahnhof.getAll");

		List<?> l = q.getResultList();

		for (Object b : l) {
			Bahnhof bhf = null;
			if (b instanceof Bahnhof) {
				bhf = (Bahnhof) b;
				log.info("Bahnhof: " + bhf.getName()+" ID:"+bhf.getID());
			}
		}


	}

	public static void task02a() throws ParseException {

		List<?> l;
		Query q = entitymanager.createNamedQuery("Reservierung.getBenutzer");
		q.setParameter("eMail","ftraxler@student.tgm.ac.at");
		l = q.getResultList();
		System.out.println(l.size());
		//System.out.println(l3.toString());
		for (Object r: l) {
			Reservierung reservierung=null;
			if(r instanceof Reservierung){
				reservierung= (Reservierung) r;
				log.info(reservierung.toString());

			}

		}
	}

	public static void task02b() throws ParseException {



	}

	public static void task02c() throws ParseException {


		List<?> l;
		Query q = entitymanager.createNamedQuery("Ticket.getReservation");
		q.setParameter("start","Huetteldorf");
		q.setParameter("ende","WienHbf");
		l = q.getResultList();
		System.out.println(l.size());
		//System.out.println(l3.toString());
		for (Object r: l) {
			Ticket ticket=null;
			if(r instanceof Ticket){
				ticket= (Ticket) r;
				log.info(ticket.toString());

			}

		}
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
