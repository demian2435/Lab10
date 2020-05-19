package it.polito.tdp.bar.model;

public class Tavolo {
	private int posti;
	private boolean libero;
	private int codice;

	public Tavolo(int posti, int codice) {
		this.posti = posti;
		this.codice = codice;
		this.libero = true;
	}

	/**
	 * @return the codice
	 */
	public int getCodice() {
		return codice;
	}

	/**
	 * @param codice the codice to set
	 */
	public void setCodice(int codice) {
		this.codice = codice;
	}

	/**
	 * @return the posti
	 */
	public int getPosti() {
		return posti;
	}

	/**
	 * @param posti the posti to set
	 */
	public void setPosti(int posti) {
		this.posti = posti;
	}

	/**
	 * @return the libero
	 */
	public boolean isLibero() {
		return libero;
	}

	/**
	 * @param libero the libero to set
	 */
	public void setLibero(boolean libero) {
		this.libero = libero;
	}

	@Override
	public String toString() {
		return "Tavolo [posti=" + posti + ", libero=" + libero + "]";
	}
}
