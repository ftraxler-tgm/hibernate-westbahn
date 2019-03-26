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

	@Column(name ="bahnhof")
	private Bahnhof bahnhof;

	@Column(name="ende")
	private Bahnhof ende;

}
