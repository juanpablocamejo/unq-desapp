package model.builders;


import model.locations.Coord;

public class CoordBuilder {

    private int latitude = 1234;
    private int longitude = 4321;

    public static CoordBuilder anyCoord() {
        return new CoordBuilder();
    }

    public Coord build() {
        return new Coord(latitude, longitude);
    }

    public CoordBuilder withLatitude(int lat) {
        latitude = lat;
        return this;
    }

    public CoordBuilder withLongitude(int lon) {
        longitude = lon;
        return this;
    }


}
