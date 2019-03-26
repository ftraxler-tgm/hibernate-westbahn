package model;


import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQueries({@NamedQuery(name="Sonderangebot.getAll",query="SELECT b from Sonderangebot b")})
public class Sonderangebot {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long ID;

	@Column(name="kontingent")
	private int kontingent = 999;

	@Column(name="startZeit")
	private Date startZeit;

	private int dauer = 12;

	private float preisNachlass = 0.5F;

	private Ticket tickets;

}
