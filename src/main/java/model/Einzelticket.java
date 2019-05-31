package model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({@NamedQuery(name="Einzelticket.getAll",query="SELECT b from Einzelticket b")})
public class Einzelticket extends Ticket {


	@Column(name="ticketOption")
	private TicketOption ticketOption;


	public Einzelticket() {

	}

	public Einzelticket(TicketOption ticketOption) {
		this.ticketOption = ticketOption;
	}

	public TicketOption getTicketOption() {
		return ticketOption;
	}

	public void setTicketOption(TicketOption ticketOption) {
		this.ticketOption = ticketOption;
	}
}
