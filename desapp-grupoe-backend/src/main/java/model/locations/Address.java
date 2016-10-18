package model.locations;

import model.Entity;

public class Address extends Entity {

    private Coord coord;
    private String location;

    private Address() {
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
}