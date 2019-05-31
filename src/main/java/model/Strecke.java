package model;


import javax.persistence.*;

@Entity
@NamedQueries({@NamedQuery(name="Strecke.getAll",query="SELECT b from Strecke b")})
public class Strecke {


	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long ID;

	@OneToOne
	private Bahnhof start;

	@OneToOne
	private Bahnhof bahnhof;

	@OneToOne
	private Bahnhof ende;

	public Strecke(){}

	public Strecke(Bahnhof start, Bahnhof bahnhof, Bahnhof ende) {
		this.start = start;
		this.bahnhof = bahnhof;
		this.ende = ende;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long ID) {
		this.ID = ID;
	}

	public Bahnhof getStart() {
		return start;
	}

	public void setStart(Bahnhof start) {
		this.start = start;
	}

	public Bahnhof getBahnhof() {
		return bahnhof;
	}

	public void setBahnhof(Bahnhof bahnhof) {
		this.bahnhof = bahnhof;
	}

	public Bahnhof getEnde() {
		return ende;
	}

	public void setEnde(Bahnhof ende) {
		this.ende = ende;
	}
}
