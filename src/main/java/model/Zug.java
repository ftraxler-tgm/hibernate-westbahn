package model;


import javax.persistence.*;
import java.util.Date;

public class Zug {

	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long ID;

	@Column(name="startZeit")
	private Date startZeit;

	@Column(name="sitzPlaetze")
	private int sitzPlaetze = 500;


	@Column(name="fahrradStellplaetze")
	private int fahrradStellplaetze = 50;


	@Column(name="rollStuhlPlaetze")
	private int rollStuhlPlaetze = 10;


	@OneToOne
	@Column(name="start")
	private Bahnhof start;

	@OneToOne
	@Column(name="ende")
	private Bahnhof ende;

}
