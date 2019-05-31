package model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Arrays;

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
	@Email
	private String eMail;


	@Column(name="passwort")
	private String passwort;



	@Column(name="smsNummer")
	private String smsNummer;



	@Column(name="verbuchtePramienMeilen")
	private Long verbuchtePraemienMeilen;


	@Transient
	private Ticket tickets;

	@OneToMany
	@OrderColumn
	private Reservierung[] reservierungen;


	public Benutzer(){

	}

	public Benutzer(@Size(min = 2, max = 15) String vorName, @Size(min = 2, max = 15) String nachName, @Email String eMail, String passwort, String smsNummer, Long verbuchtePraemienMeilen, Ticket tickets, Reservierung[] reservierungen) {
		this.vorName = vorName;
		this.nachName = nachName;
		this.eMail = eMail;
		this.passwort = passwort;
		this.smsNummer = smsNummer;
		this.verbuchtePraemienMeilen = verbuchtePraemienMeilen;
		this.tickets = tickets;
		this.reservierungen = reservierungen;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long ID) {
		this.ID = ID;
	}

	public String getVorName() {
		return vorName;
	}

	public void setVorName(String vorName) {
		this.vorName = vorName;
	}

	public String getNachName() {
		return nachName;
	}

	public void setNachName(String nachName) {
		this.nachName = nachName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getSmsNummer() {
		return smsNummer;
	}

	public void setSmsNummer(String smsNummer) {
		this.smsNummer = smsNummer;
	}

	public Long getVerbuchtePraemienMeilen() {
		return verbuchtePraemienMeilen;
	}

	public void setVerbuchtePraemienMeilen(Long verbuchtePraemienMeilen) {
		this.verbuchtePraemienMeilen = verbuchtePraemienMeilen;
	}

	public Ticket getTickets() {
		return tickets;
	}

	public void setTickets(Ticket tickets) {
		this.tickets = tickets;
	}

	public Reservierung[] getReservierungen() {
		return reservierungen;
	}

	public void setReservierungen(Reservierung[] reservierungen) {
		this.reservierungen = reservierungen;
	}

	@Override
	public String toString() {
		return "Benutzer{" +
				"ID=" + ID +
				", vorName='" + vorName + '\'' +
				", nachName='" + nachName + '\'' +
				", eMail='" + eMail + '\'' +
				", passwort='" + passwort + '\''+"}";
	}
}
