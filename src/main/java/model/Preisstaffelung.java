package model;

import javax.persistence.*;

@Entity
@NamedQueries({@NamedQuery(name="Bahnhof.getAll",query="SELECT b from Preisstaffelung b")})
public class Preisstaffelung {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="serialVersionUID")
	private static Long serialVersionUID;

	@Column(name="grossGepaeck")
	private float grossGepaeck = 1.02F;

	@Column(name="fahrrad")
	private float fahrrad = 1.05F;

	@Column (name="zeitkarteWoche")
	private int zeitkarteWoche = 8;

	@Column(name="zeitkarteMonat")
	private int zeitkarteMonat = 25;

	@Column(name = "zeitkarteJahr")
	private int zeitkarteJahr = 250;


	private static Preisstaffelung instance;

	public static Preisstaffelung getInstance() {
		return null;
	}

	private Preisstaffelung() {

	}

}
