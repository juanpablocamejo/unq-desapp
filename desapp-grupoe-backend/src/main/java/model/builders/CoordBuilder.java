package model.builders;


import model.locations.Coord;

public class CoordBuilder {

    private Double latitude = 123.4;
    private Double longitude = 43.21;

    public static CoordBuilder anyCoord() {
        return new CoordBuilder();
    }

    public Coord build() {
        return new Coord(latitude, longitude);
    }

    public CoordBuilder withLatitude(Double lat) {
        latitude = lat;
        return this;
    }

    public CoordBuilder withLongitude(Double lon) {
        longitude = lon;
        return this;
    }


}
