package model;

public class Coord {

	private int lat;
	private int lon;

	public Coord(int lat, int lon) {
		this.lat = lat;
		this.lon = lon;
	}

	public int getLat() {
		return lat;
	}

	public void setLat(int lat) {
		this.lat = lat;
	}

	public int getLon() {
		return lon;
	}

	public void setLon(int lon) {
		this.lon = lon;
	}

}
