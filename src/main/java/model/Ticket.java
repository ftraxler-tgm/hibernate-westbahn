package model;

import javax.persistence.*;

@Entity
@NamedQueries({@NamedQuery(name="Ticket.getAll",query="SELECT b from Ticket b")})
public abstract class Ticket {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	protected Long ID;

	@Column(name = "strecke")
	protected Strecke strecke;

	@Column(name="zahlung")
	protected Zahlung zahlung;

}
