package model.locations;

public class Address {

	private Coord coord;
	private String location;

	public Address(Coord coord) {
		this.coord = coord;
	}

	public Coord getCoord() {
		return coord;
	}

	public void setCoord(Coord coord) {
		this.coord = coord;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
