package model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({@NamedQuery(name="Benutzer.getAll",query="SELECT b from Benutzer b")})
public class Benutzer {


	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long ID;

	@Size(min=2,max=15)
	@Column(name="vorName",unique=true)
	private String vorName;

	@Size(min=2,max=15)
	@Column(name="nachName",unique=true)
	private String nachName;


	@Column(name="eMail")
	private String eMail;


	@Column(name="passwort")
	private String passwort;



	@Column(name="smsNummer")
	private String smsNummer;



	@Column(name="verbuchtePramienMeilen")
	private Long verbuchtePraemienMeilen;


	@Column(name="tickets")
	private Ticket tickets;

	@Column(name="reservierungen")
	private Reservierung[] reservierungen;

}
