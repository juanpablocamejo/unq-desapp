package model;

public class Outing {

	private ConcretOuting concret_outing;
	private Suggestions suggestions;

	public Outing(ConcretOuting concret_outing, Suggestions suggestions) {
		this.concret_outing = concret_outing;
		this.suggestions = suggestions;
	}

	public ConcretOuting getConcret_outing() {
		return concret_outing;
	}

	public void setConcret_outing(ConcretOuting concret_outing) {
		this.concret_outing = concret_outing;
	}

	public Suggestions getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(Suggestions suggestions) {
		this.suggestions = suggestions;
	}

}
