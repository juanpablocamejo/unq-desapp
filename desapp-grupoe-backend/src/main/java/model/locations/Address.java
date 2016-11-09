package model.locations;

import model.Entity;

public class Address extends Entity {

    private Coord coord;
    private String location;

    public Address() {
    }

    public Address(Coord coord, String location) {
        this.coord = coord;
        this.location = location;
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

    public String[] toArray() {
        String lat = coord.getLatitude().toString();
        String lon = coord.getLongitude().toString();
        return (new String[]{lat,lon,getLocation()});
    }

    public static Address fromArray(String[] a) {
        return new Address(new Coord(Double.parseDouble(a[0]), Double.parseDouble(a[1])), a[2]);
    }
}