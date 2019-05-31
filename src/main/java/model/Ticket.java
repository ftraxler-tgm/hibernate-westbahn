package model;

import javax.persistence.*;

@Entity
@NamedQueries({@NamedQuery(name="Ticket.getAll",query="SELECT b from Ticket b")})
public abstract class Ticket {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	protected Long ID;

	@OneToMany
	@Column(name = "strecke")
	protected Strecke strecke;

	@Transient
	@Column(name="zahlung")
	protected Zahlung zahlung;


	public Ticket() {

	}

	public Ticket(Strecke strecke, Zahlung zahlung) {
		this.strecke = strecke;
		this.zahlung = zahlung;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long ID) {
		this.ID = ID;
	}

	public Strecke getStrecke() {
		return strecke;
	}

	public void setStrecke(Strecke strecke) {
		this.strecke = strecke;
	}

	public Zahlung getZahlung() {
		return zahlung;
	}

	public void setZahlung(Zahlung zahlung) {
		this.zahlung = zahlung;
	}
}
