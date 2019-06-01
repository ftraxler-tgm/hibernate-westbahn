package model;

import javax.persistence.*;

@Entity
@NamedQueries({@NamedQuery(name="Ticket.getAll",query="SELECT b from Ticket b"),@NamedQuery(name="Ticket.getReservation",query = "select b from Ticket b left join Reservierung r on r.strecke=b.strecke where b.strecke.start.name=:start and b.strecke.ende.name=:ende")})

public abstract class Ticket {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	protected Long ID;

	@OneToOne
	protected Strecke strecke;

	@Transient
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
