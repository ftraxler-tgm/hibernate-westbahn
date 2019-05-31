package model;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Zug {

	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ID;

	private Date startZeit;

	private int sitzPlaetze = 500;


	private int fahrradStellplaetze = 50;


	private int rollStuhlPlaetze = 10;


	@OneToOne
	private Bahnhof start;

	@OneToOne
	private Bahnhof ende;

	public Zug(){

	}

	public Zug(Date startZeit, int sitzPlaetze, int fahrradStellplaetze, int rollStuhlPlaetze, Bahnhof start, Bahnhof ende) {
		this.startZeit = startZeit;
		this.sitzPlaetze = sitzPlaetze;
		this.fahrradStellplaetze = fahrradStellplaetze;
		this.rollStuhlPlaetze = rollStuhlPlaetze;
		this.start = start;
		this.ende = ende;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long ID) {
		this.ID = ID;
	}

	public Date getStartZeit() {
		return startZeit;
	}

	public void setStartZeit(Date startZeit) {
		this.startZeit = startZeit;
	}

	public int getSitzPlaetze() {
		return sitzPlaetze;
	}

	public void setSitzPlaetze(int sitzPlaetze) {
		this.sitzPlaetze = sitzPlaetze;
	}

	public int getFahrradStellplaetze() {
		return fahrradStellplaetze;
	}

	public void setFahrradStellplaetze(int fahrradStellplaetze) {
		this.fahrradStellplaetze = fahrradStellplaetze;
	}

	public int getRollStuhlPlaetze() {
		return rollStuhlPlaetze;
	}

	public void setRollStuhlPlaetze(int rollStuhlPlaetze) {
		this.rollStuhlPlaetze = rollStuhlPlaetze;
	}

	public Bahnhof getStart() {
		return start;
	}

	public void setStart(Bahnhof start) {
		this.start = start;
	}

	public Bahnhof getEnde() {
		return ende;
	}

	public void setEnde(Bahnhof ende) {
		this.ende = ende;
	}


	@Override
	public String toString() {
		return "Zug{" +
				"ID=" + ID +
				", startZeit=" + startZeit +
				", sitzPlaetze=" + sitzPlaetze +
				", fahrradStellplaetze=" + fahrradStellplaetze +
				", rollStuhlPlaetze=" + rollStuhlPlaetze +
				", start=" + start +
				", ende=" + ende +
				'}';
	}
}
