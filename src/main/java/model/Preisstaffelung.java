package model;


public class Preisstaffelung {

	private static Long serialVersionUID;

	private float grossGepaeck = 1.02F;

	private float fahrrad = 1.05F;

	private int zeitkarteWoche = 8;

	private int zeitkarteMonat = 25;

	private int zeitkarteJahr = 250;

	private static Preisstaffelung instance;

	public static Preisstaffelung getInstance() {
		return null;
	}

	private Preisstaffelung() {

	}

}