package model;


import javax.persistence.*;

@Entity
@NamedQueries({@NamedQuery(name="Strecke.getAll",query="SELECT b from Strecke b")})
public class Strecke {


	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long ID;

	@Column(name="start")
	private Bahnhof start;

	@OneToOne
	@Column(name ="bahnhof")
	private Bahnhof bahnhof;

	@OneToOne
	@Column(name="ende")
	private Bahnhof ende;

}
