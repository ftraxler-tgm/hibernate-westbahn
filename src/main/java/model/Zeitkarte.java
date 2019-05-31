
package model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.Date;

@Entity
@NamedQueries({@NamedQuery(name="Zeitkarte.getAll",query="SELECT b from Zeitkarte b")})
public class Zeitkarte extends Ticket {

	@Column(name="gueltigAb")
	private Date gueltigAb;

	@Column(name="typ")
	private ZeitkartenTyp typ;


	public Zeitkarte(){
		super();
	}

	public Zeitkarte(Date gueltigAb, ZeitkartenTyp typ) {
		super();
		this.gueltigAb = gueltigAb;
		this.typ = typ;
	}

	public Date getGueltigAb() {
		return gueltigAb;
	}

	public void setGueltigAb(Date gueltigAb) {
		this.gueltigAb = gueltigAb;
	}

	public ZeitkartenTyp getTyp() {
		return typ;
	}

	public void setTyp(ZeitkartenTyp typ) {
		this.typ = typ;
	}
}
