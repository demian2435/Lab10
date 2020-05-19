package it.polito.tdp.bar.model;

import java.time.LocalTime;

public class Event implements Comparable<Event> {
	public enum enumEvento {
		ARRIVO_CLIENTI, USCITA_CLIENTI
	}

	private enumEvento tipo;
	private int utenti;
	private LocalTime orario;
	private int posizione;

	public Event(enumEvento tipo, int utenti, LocalTime orario) {
		this.tipo = tipo;
		this.utenti = utenti;
		this.orario = orario;
		this.posizione = -1;
	}

	/**
	 * @return the tipo
	 */
	public enumEvento getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(enumEvento tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the utenti
	 */
	public int getUtenti() {
		return utenti;
	}

	/**
	 * @param utenti the utenti to set
	 */
	public void setUtenti(int utenti) {
		this.utenti = utenti;
	}

	/**
	 * @return the orario
	 */
	public LocalTime getOrario() {
		return orario;
	}

	/**
	 * @param orario the orario to set
	 */
	public void setOrario(LocalTime orario) {
		this.orario = orario;
	}

	/**
	 * @return the posizione
	 */
	public int getPosizione() {
		return posizione;
	}

	/**
	 * @param posizione the posizione to set
	 */
	public void setPosizione(int posizione) {
		this.posizione = posizione;
	}

	@Override
	public String toString() {
		return "Event [tipo=" + tipo + ", utenti=" + utenti + ", orario=" + orario + ", posizione=" + posizione + "]";
	}

	@Override
	public int compareTo(Event o) {
		return this.orario.compareTo(o.orario);
	}

}
