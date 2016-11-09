package model.builders;

import model.locations.Address;
import model.locations.Coord;

public class AddressBuilder {

    private Coord coord = CoordBuilder.anyCoord().build();
    private String location = "Buenos Aires";

    public static AddressBuilder anyAddress() {
        return new AddressBuilder();
    }

    public Address build() {
        return new Address(coord, location);
    }

    public AddressBuilder withCoord(Coord c) {
        coord = c;
        return this;
    }

    public AddressBuilder withCoords(Double lat, Double lon) {
        coord.setLatitude(lat);
        coord.setLongitude(lon);
        return this;
    }

    public AddressBuilder withLocation(String l) {
        location = l;
        return this;
    }

}
