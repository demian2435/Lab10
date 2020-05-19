package it.polito.tdp.bar.model;

public class Model {
	Simulator s = new Simulator();

	public String run() {
		s.run();
		return s.output();
	}

}
