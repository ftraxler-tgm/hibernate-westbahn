package model;


import javax.persistence.Enumerated;

public enum ZeitkartenTyp {

	;

	public int WOCHENKARTE=0;

	public int MONATSKARTE=1;

	public int JAHRESKARTE=2;

	public int getWOCHENKARTE() {
		return WOCHENKARTE;
	}


	public int getMONATSKARTE() {
		return MONATSKARTE;
	}


	public int getJAHRESKARTE() {
		return JAHRESKARTE;
	}


}
