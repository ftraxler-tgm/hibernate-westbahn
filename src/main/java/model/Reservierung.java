
package model;


import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQueries({@NamedQuery(name="Bahnhof.getAll",query="SELECT b from Preisstaffelung b")})
public class Reservierung {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long ID;

	@Column(name="datum")
	private Date datum;

	@Column(name="praemienMeilenBonus")
	private int praemienMeilenBonus = 15;

	@Column(name="preis")
	private int preis = 150;

	@Column(name="status")
	private StatusInfo status;

	@Column(name="zug")
	private Zug zug;

	@Column(name="strecke")
	private Strecke strecke;


	@Column(name = "benutzer")
	private Benutzer benutzer;

	@Column(name="zahlung")
	private Zahlung zahlung;

}
