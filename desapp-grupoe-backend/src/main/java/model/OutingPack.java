package model;

import java.util.ArrayList;

public class OutingPack extends Outing {

	private ArrayList<ConcretOuting> concretOutings;

	public OutingPack(ArrayList<ConcretOuting> concretOutings) {
		super();
		this.concretOutings = concretOutings;
	}

	public ArrayList<ConcretOuting> getConcretOuting() {
		return concretOutings;
	}

	public void setConcretOuting(ArrayList<ConcretOuting> concretOuting) {
		this.concretOutings = concretOuting;
	}

	public double getPrice() {

		double finalPrice = 0;
		for (ConcretOuting co : concretOutings) {
			finalPrice += co.getPrice();
		}
		return finalPrice;
	}

	public void addConcretOuting(ConcretOuting outing) {
		getConcretOuting().add(outing);
	}

}
