package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;

import it.polito.tdp.bar.model.Event.enumEvento;

public class Simulator {
	Queue<Event> queue = new PriorityQueue<Event>();

	private Map<Integer, Tavolo> tavoli = new HashMap<Integer, Tavolo>(); // lista tavoli
	private final LocalTime oraApertura = LocalTime.of(8, 00);
	private final LocalTime oraChiusura = LocalTime.of(20, 00);

	private int clienti;
	private int insoddisfatti;
	private int soddisfatti;
	private int alTavolo;
	private int alBancone;

	public int getClientiTot() {
		return clienti;
	}

	public int getInsoddisfatti() {
		return insoddisfatti;
	}

	public int getSoddisfatti() {
		return soddisfatti;
	}

	public int getAlTavolo() {
		return alTavolo;
	}

	public int getAlBancone() {
		return alBancone;
	}

	// SIMULAZIONE
	public void run() {
		generaTavoli();
		this.clienti = 0;
		this.insoddisfatti = 0;
		this.soddisfatti = 0;
		this.alBancone = 0;
		this.alTavolo = 0;
		this.queue.clear();

		LocalTime oraArrivoCliente = this.oraApertura;
		for (int i = 0; i < 200; i++) {
			queue.add(new Event(enumEvento.ARRIVO_CLIENTI, (int) (Math.random() * 10) + 1, oraArrivoCliente));
			oraArrivoCliente = oraArrivoCliente.plus(Duration.of((int) (Math.random() * 10) + 1, ChronoUnit.MINUTES));
		}
		;

		while (!this.queue.isEmpty()) {
			Event e = this.queue.poll();
			// System.out.println(e);
			processEvent(e);
		}
	}

	private void generaTavoli() {
		tavoli.clear();
		tavoli.put(1, new Tavolo(10, 1));
		tavoli.put(2, new Tavolo(10, 2));
		tavoli.put(3, new Tavolo(8, 3));
		tavoli.put(4, new Tavolo(8, 4));
		tavoli.put(5, new Tavolo(8, 5));
		tavoli.put(6, new Tavolo(8, 6));
		tavoli.put(7, new Tavolo(6, 7));
		tavoli.put(8, new Tavolo(6, 8));
		tavoli.put(9, new Tavolo(6, 9));
		tavoli.put(10, new Tavolo(6, 10));
		tavoli.put(11, new Tavolo(4, 11));
		tavoli.put(12, new Tavolo(4, 12));
		tavoli.put(13, new Tavolo(4, 13));
		tavoli.put(14, new Tavolo(4, 14));
		tavoli.put(15, new Tavolo(4, 15));
	}

	private void processEvent(Event e) {
		switch (e.getTipo()) {
		case ARRIVO_CLIENTI:
			clienti++;

//			if (e.getOrario().isAfter(oraChiusura)) {
//				insoddisfatti++;
//				break;
//			}

			Optional<Tavolo> tavoloDisponibile = tavoli.values().stream()
					.filter(t -> t.getPosti() >= e.getUtenti() && t.isLibero())
					.min(Comparator.comparingInt(Tavolo::getPosti));

			if (tavoloDisponibile.isPresent() && (tavoloDisponibile.get().getPosti() / 2) < e.getUtenti()) {
				Tavolo t = tavoloDisponibile.get();
				tavoli.get(t.getCodice()).setLibero(false);
				this.soddisfatti++;
				this.alTavolo++;
				e.setOrario(e.getOrario().plus(Duration.of((int) (Math.random() * 60) + 60, ChronoUnit.MINUTES)));
				e.setTipo(enumEvento.USCITA_CLIENTI);
				e.setPosizione(t.getCodice());
				queue.add(e);
			} else {
				if (Math.random() > 0.1) {
					this.soddisfatti++;
					this.alBancone++;
					e.setOrario(e.getOrario().plus(Duration.of((int) (Math.random() * 15) + 1, ChronoUnit.MINUTES)));
					e.setTipo(enumEvento.USCITA_CLIENTI);
					queue.add(e);
				} else {
					this.insoddisfatti++;
				}
			}

			break;
		case USCITA_CLIENTI:
			if (e.getPosizione() != -1) {
				tavoli.get(e.getPosizione()).setLibero(true);
			}
			break;
		}
	}

	public String output() {
		return String.format("Clienti: %d - soddisfatti %d [alTavolo %d / alBancone %d] - insoddisfatti %d", clienti,
				soddisfatti, alTavolo, alBancone, insoddisfatti);
	}

}
