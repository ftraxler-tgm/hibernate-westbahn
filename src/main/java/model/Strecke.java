package model;


import javax.persistence.*;

@Entity
@NamedQueries({@NamedQuery(name="Strecke.getAll",query="SELECT b from Strecke b")})
public class Strecke {


	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long ID;

	@Column(name="start")
	private Bahnhof start;

	@OneToOne
	@Column(name ="bahnhof")
	private Bahnhof bahnhof;

	@OneToOne
	@Column(name="ende")
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
