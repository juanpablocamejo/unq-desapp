package model;

import java.util.ArrayList;

public class Suggestions {

	private ArrayList<ConcretOuting> concretOuting;

	public Suggestions(ArrayList<ConcretOuting> concretOuting) {
		this.concretOuting = concretOuting;
	}

	public ArrayList<ConcretOuting> getConcretOuting() {
		return concretOuting;
	}

	public void setConcretOuting(ArrayList<ConcretOuting> concretOuting) {
		this.concretOuting = concretOuting;
	}

}
