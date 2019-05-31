package model;


import javax.persistence.*;

@Entity
@NamedQueries({@NamedQuery(name="Einzelticket.getAll",query="SELECT b from Einzelticket b")})
public class Einzelticket extends Ticket {


	@Enumerated
	private TicketOption ticketOption;


	public Einzelticket() {

	}
	public Einzelticket(Strecke strecke, Zahlung zahlung,TicketOption ticketOption){
		super(strecke,zahlung);
		this.ticketOption=ticketOption;
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

	@Override
	public String toString() {
		return "Einzelticket{" +
				"ticketOption=" + ticketOption +
				", ID=" + ID +
				", strecke=" + strecke +
				", zahlung=" + zahlung +
				'}';
	}
}
